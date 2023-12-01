package com.sea.battle.lite.playing_field.particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

public class Particl {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 gravity;

    private float delta;

    public Particl() {
        this.position = new Vector2();
        this.velocity = new Vector2(0,5);
        this.gravity = new Vector2(0,-10);
        this.delta = -1;
    }

    public void move() {

        position.add(velocity.cpy().scl(Gdx.graphics.getDeltaTime()));
        velocity.add(gravity.cpy().scl(Gdx.graphics.getDeltaTime()));
        delta = delta - Gdx.graphics.getDeltaTime();
    }

    public void setPartical(float xp, float yp, byte tip) {
        this.position.set(xp, yp);
        this.gravity.set(0, -4500f/2);
        this.delta = MathUtils.random(.2f,.7f);
        this.velocity = new Vector2(MathUtils.random(-250,250),MathUtils.random(100,350));
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setPosition(float x, float y) {
        this.position.set(x,y);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getGravity() {
        return gravity;
    }

    public void setGravity(Vector2 gravity) {
        this.gravity = gravity;
    }

    public float getDelta() {
        return delta;
    }

    public void setDelta(float delta) {
        this.delta = delta;
    }


    public boolean isLive(){
        if(delta > 0) return true;
        return false;
    }
    @Override
    public String toString() {
        return "Particl{" +
                "position=" + position +
                ", velocity=" + velocity +
                ", gravity=" + gravity +
                ", delta=" + delta +
                '}';
    }
}
