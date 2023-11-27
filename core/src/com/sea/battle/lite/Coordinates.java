package com.sea.battle.lite;

public class Coordinates {
    byte x,y;

    public byte getX() {
        return x;
    }

    public void setX(byte x) {
        this.x = x;
    }

    public void setY(byte y) {
        this.y = y;
    }

    public byte getY() {
        return y;
    }

    public void setXY(byte x, byte y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
