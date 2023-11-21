package com.sea.battle.lite;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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

    @Override
    public void create() {
        game_match = new Game_match();
        texture = new Texture(Gdx.files.internal("a.png"));
        textureb = new Texture(Gdx.files.internal("b.png"));
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        ScreenUtils.clear(.5f, .5f, .5f, 1);
        batch.begin();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = game_match.player_playin_field.getCellmatrix(i, j);

                if (cell.getTip() == CELL_TYPE.UNOPENED) batch.setColor(0, 1, 1, 1);
                if (cell.getTip() == CELL_TYPE.OPEN_DEATH) batch.setColor(1, 0, 1, 1);
                batch.draw(texture, 50 + (j * 22), 300 - (i * 22), 20, 20);

                if (cell.getTip() == CELL_TYPE.OPEN_WOUND) {
                    batch.setColor(1, 1, 1, 1);
                    batch.draw(textureb, 50 + (j * 22), 300 - (i * 22), 20, 20);

                }

            }

        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = game_match.opponent_playing_field.getCellmatrix(i, j);

                if (cell.getTip() == CELL_TYPE.UNOPENED) batch.setColor(0, 1, 1, 1);
                if (cell.getTip() == CELL_TYPE.OPEN_DEATH) batch.setColor(1, 0, 1, 1);

                batch.draw(texture, 350 + (j * 22), 300 - (i * 22), 20, 20);

            }

        }


        batch.end();

        game_match.player_playin_field.auto_completion_the_card();

        if (MathUtils.randomBoolean(.05f))
            if (MathUtils.randomBoolean())
                game_match.player_playin_field.auto_ras();
            else
                game_match.opponent_playing_field.auto_ras();




    }

    @Override
    public void dispose() {

    }
}
