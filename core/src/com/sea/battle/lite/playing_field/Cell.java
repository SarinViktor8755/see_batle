package com.sea.battle.lite.playing_field;

public class Cell {
    byte tip;

    public int getTip() {
        return tip;
    }

    public void setTip(byte tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "tip=" + tip +
                '}';
    }
}
