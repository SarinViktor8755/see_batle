package com.sea.battle.lite;

import com.sea.battle.lite.playing_field.Field;
import com.sea.battle.lite.playing_field.particles.MainParticles;

public class Game_match {
    Field player_playin_field;
    Field opponent_playing_field;
    MainParticles mainParticles;

    public Game_match() {
        mainParticles = new MainParticles();
        player_playin_field = new Field();
        opponent_playing_field = new Field();
    }


}
