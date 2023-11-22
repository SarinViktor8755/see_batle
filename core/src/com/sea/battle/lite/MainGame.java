package com.sea.battle.lite;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
        game_match.player_playin_field.auto_completion_the_card();
        ScreenUtils.clear(.5f, .5f, .5f, 1);
        batch.begin();

        batch.setColor(1, 1, 1, 1);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = game_match.player_playin_field.getCellmatrix(i, j);
                if (cell.getTip() == CELL_TYPE.UNOPENED) batch.setColor(0, 1, 1, 1);
                if (cell.getTip() == CELL_TYPE.UNOPENED_EMPTY) batch.setColor(0, 1, 1, 1);
                if (cell.getTip() == CELL_TYPE.UNOPENED_OCCUPIED) batch.setColor(0, 1, 1, 1);

                /////////////////

                if (cell.getTip() == CELL_TYPE.OPEN_DEATH) batch.setColor(Color.RED);

                if (cell.getTip() == CELL_TYPE.OPEN_WOUND) batch.setColor(.5f, .5f, 0, 1);

                if (cell.getTip() == CELL_TYPE.OPEN_FREE) batch.setColor(0, 1, 1, 1);

                batch.draw(texture, 50 + (j * 22), 300 - (i * 22), 20, 20);



                if (cell.getTip() == CELL_TYPE.OPEN_FREE) {
                    batch.setColor(Color.BROWN);
                    batch.draw(textureb, 50 + (j * 22), 300 - (i * 22), 20, 20);}

            }
            batch.setColor(1,1,1,1);

        }

//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                Cell cell = game_match.opponent_playing_field.getCellmatrix(i, j);
//
//                if (cell.getTip() == CELL_TYPE.UNOPENED) batch.setColor(0, 1, 1, 1);
//                if (cell.getTip() == CELL_TYPE.OPEN_DEATH) batch.setColor(1, 0, 1, 1);
//
//                batch.draw(texture, 350 + (j * 22), 300 - (i * 22), 20, 20);
//
//            }
//
//        }


        batch.end();


//
//        if (MathUtils.randomBoolean(.03f))
//            if (MathUtils.randomBoolean())
//                game_match.player_playin_field.auto_ras();
//            else
//                game_match.opponent_playing_field.auto_ras();
//        System.out.println(game_match.player_playin_field.isFeil());

       // game_match.player_playin_field.take_attac(MathUtils.random(0, 9), MathUtils.random(0, 9));
        //while (!game_match.player_playin_field.take_attac(MathUtils.random(0,9),MathUtils.random(0,9)))

        //for (;;) {
           // System.out.println(game_match.player_playin_field.isFeil());
        boolean r;
        do {
            r = game_match.player_playin_field.take_attac(MathUtils.random(0, 9), MathUtils.random(0, 9));
        }while (!r);




           if(game_match.player_playin_field.isFeil()){
               game_match.player_playin_field.clear_field();
               game_match.player_playin_field.auto_ras();

           }
        //    if(!game_match.player_playin_field.isFeil()) break;


      //  }
    }

    @Override
    public void dispose() {

    }

}

