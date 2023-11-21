package com.sea.battle.lite.playing_field;

public class CELL_TYPE {
    public static byte UNOPENED = 1; // НЕОТКРЫТАЯ
    public static byte UNOPENED_EMPTY = 2; // НЕОТКРЫТАЯ_ПУСТАЯ
    public static byte UNOPENED_OCCUPIED = 3; // НЕОТКРЫТАЯ_ЗАНЯТАЯ
    ///////////////

    public static byte OPEN_FREE = 4; // ОТКРЫТАЯ_ПУСТАЯ
    public static byte OPEN_WOUND = 5; // ОТКРЫТАЯ РАНЕНИЕ_________
    public static byte OPEN_DEATH = 6; // ОТКРЫТАЯ_УБИТ

}
