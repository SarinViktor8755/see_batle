package com.sea.battle.lite;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
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
    BitmapFont font; //or use alex answer to use custom font
    private static  int km,kop;

    boolean o = true;

    @Override
    public void create() {
        km = 0;
        kop = 0 ;
        font= new BitmapFont();
        game_match = new Game_match();
        texture = new Texture(Gdx.files.internal("a.png"));
        textureb = new Texture(Gdx.files.internal("b.png"));
        open_free = new Texture(Gdx.files.internal("OPEN_FREE.png"));
        open_death = new Texture(Gdx.files.internal("OPEN_DEATH.png"));
        batch = new SpriteBatch();
    }

    @Override
    public void render() {

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

        font.draw(batch, "SCORE :: " +km + " : " + kop, 200, 400);
        batch.end();

        if(o){
        boolean r;
        do {
            r = game_match.opponent_playing_field.take_attac(MathUtils.random(0, 9), MathUtils.random(0, 9));
        } while (!r);

        o = false;
        }else {

            boolean r;
            do {
                r = game_match.player_playin_field.take_attac(MathUtils.random(0, 9), MathUtils.random(0, 9));
            } while (!r);

        o = true;
        }


        if (game_match.opponent_playing_field.isFailure()) {
            game_match.opponent_playing_field.clear_field();
            game_match.opponent_playing_field.auto_ras();
            game_match.player_playin_field.clear_field();
            game_match.player_playin_field.auto_ras();
            km++;


        }

        if (game_match.player_playin_field.isFailure()) {
            game_match.opponent_playing_field.clear_field();
            game_match.opponent_playing_field.auto_ras();
            game_match.player_playin_field.clear_field();
            game_match.player_playin_field.auto_ras();
            kop++;

        }
      //  System.out.println("SCORE :: " +km + " : " + kop);System.out.println("SCORE :: " +km + " : " + kop);
    }

    @Override
    public void dispose() {

    }

}

