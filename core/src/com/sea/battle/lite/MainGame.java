package com.sea.battle.lite;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sea.battle.lite.artificialIntelligence.Main_artificial_intelligence;
import com.sea.battle.lite.playing_field.CELL_TYPE;
import com.sea.battle.lite.playing_field.Cell;
import com.sea.battle.lite.playing_field.Field;

import java.awt.Image;

public class MainGame extends ApplicationAdapter {
    Game_match game_match;

    SpriteBatch batch;
    //Texture img = new Texture(Gdx.files.internal("1.png"));;
    Texture texture;
    Texture textureb;
    Texture open_free;
    Texture open_death;
    Texture death;
    BitmapFont font; //or use alex answer to use custom font
    private static int km, kop, pause;

    Main_artificial_intelligence  mainArtificialIntelligence = new Main_artificial_intelligence();

    boolean o = true;

    @Override
    public void create() {
        km = 0;
        kop = 0;
        pause = 0;
        font = new BitmapFont();
        game_match = new Game_match();
        texture = new Texture(Gdx.files.internal("a.png"));
        textureb = new Texture(Gdx.files.internal("b.png"));
        open_free = new Texture(Gdx.files.internal("OPEN_FREE.png"));
        open_death = new Texture(Gdx.files.internal("OPEN_DEATH.png"));
        death = new Texture(Gdx.files.internal("death.png"));
        batch = new SpriteBatch();
    }

    @Override
    public void render() {


        game_match.mainParticles.movedParticles();
        if(MathUtils.randomBoolean(.04f))game_match.mainParticles.addPartical(40,40,CELL_TYPE.OPEN_DEATH);


        // game_match.player_playin_field.auto_completion_the_card();
        ScreenUtils.clear(.5f, .5f, .5f, 1);
        batch.begin();

        batch.setColor(1, 1, 1, 1);


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = game_match.player_playin_field.getCellmatrix(i, j);
                byte t = cell.getTip();

                if ((t == CELL_TYPE.UNOPENED) || (t == CELL_TYPE.UNOPENED_EMPTY) || (t == CELL_TYPE.UNOPENED_OCCUPIED)) {
                    batch.draw(open_free, 50 + (j * 22), 300 - (i * 22), 20, 20);
                }

                if (cell.getTip() == CELL_TYPE.OPEN_WOUND) {
                    batch.draw(texture, 50 + (j * 22), 300 - (i * 22), 20, 20);
                    batch.draw(textureb, 50 + (j * 22), 300 - (i * 22), 20, 20);
                }

                if (cell.getTip() == CELL_TYPE.OPEN_FREE) {
                    batch.draw(open_free, 50 + (j * 22), 300 - (i * 22), 20, 20);
                    batch.draw(textureb, 50 + (j * 22), 300 - (i * 22), 20, 20);
                }

                if (cell.getTip() == CELL_TYPE.OPEN_DEATH) {
                    batch.draw(open_death, 50 + (j * 22), 300 - (i * 22), 20, 20);
                    batch.draw(textureb, 50 + (j * 22), 300 - (i * 22), 20, 20);
                }


            }
            batch.setColor(1, 1, 1, 1);

        }


        batch.setColor(1, 1, 1, 1);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = game_match.opponent_playing_field.getCellmatrix(i, j);
                byte t = cell.getTip();

                if ((t == CELL_TYPE.UNOPENED) || (t == CELL_TYPE.UNOPENED_EMPTY) || (t == CELL_TYPE.UNOPENED_OCCUPIED)) {
                    batch.draw(open_free, 350 + (j * 22), 300 - (i * 22), 20, 20);
                }

                if (cell.getTip() == CELL_TYPE.OPEN_WOUND) {
                    batch.draw(texture, 350 + (j * 22), 300 - (i * 22), 20, 20);
                    batch.draw(textureb, 350 + (j * 22), 300 - (i * 22), 20, 20);
                }

                if (cell.getTip() == CELL_TYPE.OPEN_FREE) {
                    batch.draw(open_free, 350 + (j * 22), 300 - (i * 22), 20, 20);
                    batch.draw(textureb, 350 + (j * 22), 300 - (i * 22), 20, 20);
                }

                if (cell.getTip() == CELL_TYPE.OPEN_DEATH) {
                    batch.draw(open_death, 350 + (j * 22), 300 - (i * 22), 20, 20);
                    batch.draw(textureb, 350 + (j * 22), 300 - (i * 22), 20, 20);
                }


            }
            batch.setColor(1, 1, 1, 1);

        }
        float i = Interpolation.circleOut.apply(MathUtils.map(0,120,1,0,pause));
        batch.setColor(1, 1, 1, i);
        if(game_match.opponent_playing_field.isFailure()){
            batch.draw(death, 350, 130  + i * 120, 220, 220);
        }
        if(game_match.player_playin_field.isFailure()){
            batch.draw(death, 50, 130  + i * 120, 220, 220);
        }

        batch.setColor(1, 1, 1, 1);
        game_match.mainParticles.randerPartical(batch,texture);
        font.draw(batch, "SCORE :: " + km + " : " + kop, 200, 400);
        batch.end();




        if (pause > 0) {
            pause--;
            if(pause ==1 )restart();
            return;
        }

        if (o) {
            boolean r;
            do {
                Coordinates attac = mainArtificialIntelligence.decide(game_match.opponent_playing_field.getField_matrix());
                r = game_match.opponent_playing_field.take_attac(attac.x,attac.y);
            } while (!r);
            o = false;
        } else {

            boolean r;
            do {
                r = game_match.player_playin_field.take_attac(MathUtils.random(0, 9), MathUtils.random(0, 9));
            } while (!r);
            o = true;
        }


        if (game_match.opponent_playing_field.isFailure()) {
            km++;
            pause = 120;

        }

        if (game_match.player_playin_field.isFailure()) {
            kop++;
            pause = 120;
        }


        //  System.out.println("SCORE :: " +km + " : " + kop);System.out.println("SCORE :: " +km + " : " + kop);
    }

    public void restart() {
        game_match.opponent_playing_field.clear_field();
        game_match.opponent_playing_field.auto_ras();
        game_match.player_playin_field.clear_field();
        game_match.player_playin_field.auto_ras();
        pause = 0;
    }

    @Override
    public void dispose() {

    }

}

