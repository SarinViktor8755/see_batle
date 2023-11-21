package com.sea.battle.lite.playing_field;

public class Ship {
    public byte x, y;
    public byte size;
    public boolean orientation_horizontal;


    public boolean point_in_ship_zone(byte x, byte y) {
//        if (!ship.orientation_horizontal) {
//            for (int i = ship.x; i < ship.x + ship.size; i++) {
//                field_matrix[i][ship.y].tip = CELL_TYPE.OPEN_BUSY;
//            }
//        } else {
//
//            for (int i = ship.y; i < ship.y + ship.size; i++) {
//                field_matrix[ship.x][i].tip = CELL_TYPE.OPEN_BUSY;
//            }
//
//        }
        if (!orientation_horizontal) {
            for (int i = x; i < x + size; i++) {
                //field_matrix[i][ship.y].tip

                

            }


        }


        return false;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "x=" + x +
                ", y=" + y +
                ", size=" + size +
                ", orientation_horizontal=" + orientation_horizontal +
                '}';
    }
}
