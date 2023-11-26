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
        this.gravity = new Vector2(0,-1);
    }

    public void move() {
        velocity.add(gravity);
        position.add(velocity.scl(gravity));
        delta -= Gdx.graphics.getBackBufferHeight();
    }

    public void setPartical(float xp, float yp, byte tip) {
        this.position.set(xp, yp);
        this.gravity.set(0, -0.00001f);
        this.delta = MathUtils.random(0, .7f);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
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
