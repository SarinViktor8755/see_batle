package com.sea.battle.lite.playing_field.particles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.sea.battle.lite.playing_field.CELL_TYPE;

import java.util.ArrayList;

public class MainParticles {
    ArrayList<Particl> particls;


    public MainParticles() {
        this.particls = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            particls.add(new Particl());
        }

    }

    public void movedParticles() {
        for (int i = 0; i < particls.size(); i++) {
            particls.get(i).move();
        }


    }

    public void addPartical(float xp, float yp, byte tip) {
        Particl p = particls.get(particls.size() - 1);
        p.setPartical(MathUtils.random(260, 340), 300, CELL_TYPE.OPEN_DEATH);
        particls.add(p);
        particls.remove(particls.size() - 1);
        System.out.println(particls);
    }

    public void randerPartical(SpriteBatch spriteBatch, Texture texture) {
        for (int i = 0; i < particls.size(); i++) {
            //     spriteBatch.draw(texture, particls.get(i).getPosition().x, particls.get(i).getPosition().y, 20, 20);
        }

    }
}
