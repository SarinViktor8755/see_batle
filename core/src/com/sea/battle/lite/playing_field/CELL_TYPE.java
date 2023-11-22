package com.sea.battle.lite.playing_field;

public class CELL_TYPE {
    public static byte UNOPENED = 1; // НЕОТКРЫТАЯ
    public static byte UNOPENED_EMPTY = 2; // НЕОТКРЫТАЯ_ПУСТАЯ
    public static byte UNOPENED_OCCUPIED = 3; // НЕОТКРЫТАЯ_ЗАНЯТАЯ
    ///////////////

    public static byte OPEN_FREE = 4; // ОТКРЫТАЯ_ПУСТАЯ
    public static byte OPEN_WOUND = 5; // ОТКРЫТАЯ РАНЕНИЕ_________
    public static byte OPEN_DEATH = 6; // ОТКРЫТАЯ_УБИТ

    static boolean isOCCUPIED(Cell c) {
        try {
            if (c.getTip() == UNOPENED_OCCUPIED) return true;
            if (c.getTip() == OPEN_WOUND) return true;
            if (c.getTip() == OPEN_DEATH) return true;
        }catch (ArrayIndexOutOfBoundsException e){ return true;}

        return false;
    }

}
