package com.sea.battle.lite.playing_field.particles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.sea.battle.lite.playing_field.CELL_TYPE;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class MainParticles {
    ArrayDeque<Particl> particls;

    public MainParticles() {
        this.particls = new ArrayDeque<>();
        for (int i = 0; i < 700; i++) {
            particls.add(new Particl());
        }
    }

    public void movedParticles() {
//        for (int i = 0; i < particls.size(); i++) {
//            particls.get(i).move();
//        }
    }

    public void addPartical(float xp, float yp, byte tip) {
        Particl particl = this.particls.pollLast();

        particl.setPartical(xp, yp, CELL_TYPE.OPEN_DEATH);
        //particl.setPosition(xp,yp);
        particls.offerFirst(particl);
    }

    public void randerPartical(SpriteBatch spriteBatch, Texture texture) {
        for (Particl p : particls) {

            if (!p.isLive()) return;
         //   if (p.getPosition().y < 0) return;
           // System.out.println(p.getPosition());

            p.move();
            spriteBatch.setColor(1,1,1,p.getDelta()+ .3f);
            spriteBatch.draw(texture, p.getPosition().x, p.getPosition().y, 10, 10);
        }


    }
}
