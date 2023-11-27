package com.sea.battle.lite.playing_field;

import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.Iterator;

public class Field {

    private Cell[][] field_matrix;
    public static int size = 0;

    private ArrayList<Ship> shipArrayList;

    public Field() {
        shipArrayList = new ArrayList<>();
        create_playing_field();

        // auto_ras();
        auto_ras();
        //print_playing_field();
    }

    private void create_playing_field() {
        field_matrix = new Cell[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell с = new Cell();
                field_matrix[i][j] = с;
                с.setTip(CELL_TYPE.UNOPENED);
            }
        }

    }

    private void create_random_ship(int size) {
        Ship ship = new Ship();
        ship.x = (byte) MathUtils.random(0, 9);
        ship.y = (byte) MathUtils.random(0, 9);
        ship.size = (byte) size;
        ship.orientation_horizontal = MathUtils.randomBoolean();
        boolean s = add_ship_on_field(ship);

        if (s) Field.size++;
        if (s) this.shipArrayList.add(ship);

    }


    public void auto_ras() {
        shipArrayList.clear();
        Field.size = 0;
        int iteration = 0;
        clear_field();


        while (Field.size < 4) {
            create_random_ship(1);
            iteration++;
            if (iteration > 350) {
                clear_field();
                auto_ras();
            }
        }
        Field.size = 0;

        while (Field.size < 3) {
            create_random_ship(2);
            iteration++;
            if (iteration > 350) {
                clear_field();
                auto_ras();
            }
        }
        Field.size = 0;

        while (Field.size < 2) {
            create_random_ship(3);
            iteration++;
            if (iteration > 350) {
                clear_field();
                auto_ras();
            }
        }
        Field.size = 0;

        while (Field.size < 1) {
            create_random_ship(4);
            iteration++;
            if (iteration > 350) {
                clear_field();
                auto_ras();
            }
        }
//        //  System.out.println("iteration"+iteration);

    }


    private void print_playing_field() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(field_matrix[i][j].tip + " ");
            }
            System.out.println();
        }
        System.out.println("---------");

    }

    public boolean add_ship_on_field(Ship ship) {

        if (!checking_setting_of_the_ship(ship)) return false;
        if (!ship.orientation_horizontal) {
            for (int i = ship.x; i < ship.x + ship.size; i++) {
                field_matrix[i][ship.y].tip = CELL_TYPE.UNOPENED_OCCUPIED;
            }
            return true;
        } else {
            for (int i = ship.y; i < ship.y + ship.size; i++) {
                field_matrix[ship.x][i].tip = CELL_TYPE.UNOPENED_OCCUPIED;
            }
            return true;
        }

    }

    private boolean checking_setting_of_the_ship(Ship ship) { // Проверка - можно ли ставить
        if (!ship.orientation_horizontal) {
            if (ship.x + ship.size > 10) return false;
            if (ship.x < 0) return false;
            for (int i = (ship.x - 1); i <= (ship.x + ship.size + 1); i++) {
                if (return_tip_cell(i, ship.y) != CELL_TYPE.UNOPENED) return false;
                if (return_tip_cell(i, ship.y + 1) != CELL_TYPE.UNOPENED) return false;
                if (return_tip_cell(i, ship.y - 1) != CELL_TYPE.UNOPENED) return false;


            }
        } else {
            if (ship.y + ship.size > 10) return false;
            if (ship.y < 0) return false;
            for (int i = (ship.y - 1); i <= (ship.y + ship.size + 1); i++) {
                if (return_tip_cell(ship.x, i) != CELL_TYPE.UNOPENED) return false;

                if (return_tip_cell(ship.x + 1, i) != CELL_TYPE.UNOPENED) return false;
                if (return_tip_cell(ship.x - 1, i) != CELL_TYPE.UNOPENED) return false;

            }
        }
        return true;
    }


    private int return_tip_cell(byte x, byte y) {
        try {
            return field_matrix[x][y].tip;
        } catch (ArrayIndexOutOfBoundsException e) {
            return CELL_TYPE.UNOPENED;
        }

    }

    private int return_tip_cell(int x, int y) {
        return return_tip_cell((byte) x, (byte) y);

    }

    public Cell getCellmatrix(int x, int y) {
        return this.field_matrix[x][y];
    }


    public void clear_field() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field_matrix[i][j].setTip(CELL_TYPE.UNOPENED);
            }
        }
    }

    public Cell[][] getField_matrix() {
        return field_matrix;
    }

    public boolean isFailure() { //проверка проиграл или нет
        int s = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell c = field_matrix[i][j];
                if (c.getTip() == CELL_TYPE.OPEN_DEATH) s++;
            }

        }

        if (s == 20) {
            //print_playing_field();
            return true;
        } else return false;
    }

    public void auto_completion_the_card() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell c = field_matrix[i][j];
                if (c.getTip() == CELL_TYPE.OPEN_DEATH) { // Если мертвый
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            try {
                                if (field_matrix[i + k][j + l].getTip() != CELL_TYPE.OPEN_DEATH)
                                    field_matrix[i + k][j + l].setTip(CELL_TYPE.OPEN_FREE);
                            } catch (ArrayIndexOutOfBoundsException e) {
                                continue;
                            }
                        }
                    }
                } else if (c.getTip() == CELL_TYPE.OPEN_WOUND) {  // Если ранен
                    try {

                        if (field_matrix[i - 1][j - 1].getTip() != CELL_TYPE.OPEN_DEATH)
                            field_matrix[i - 1][j - 1].setTip(CELL_TYPE.OPEN_FREE);
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        if (field_matrix[i + 1][j + 1].getTip() != CELL_TYPE.OPEN_DEATH)
                            field_matrix[i + 1][j + 1].setTip(CELL_TYPE.OPEN_FREE);
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        if (field_matrix[i - 1][j + 1].getTip() != CELL_TYPE.OPEN_DEATH)
                            field_matrix[i - 1][j + 1].setTip(CELL_TYPE.OPEN_FREE);
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        if (field_matrix[i + 1][j - 1].getTip() != CELL_TYPE.OPEN_DEATH)
                            field_matrix[i + 1][j - 1].setTip(CELL_TYPE.OPEN_FREE);
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }

                }

            }
        }


///////////////////////////////////////////////////////////////////////
//        boolean e = true;
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                Cell c = field_matrix[i][j];
//
//                e = c.getTip() == CELL_TYPE.OPEN_WOUND;
//                if(CELL_TYPE.isOCCUPIED(field_matrix[i-1][j])) e = true;
//                if(CELL_TYPE.isOCCUPIED(field_matrix[i+1][j])) e = true;
//                if(CELL_TYPE.isOCCUPIED(field_matrix[i][j-1])) e = true;
//                if(CELL_TYPE.isOCCUPIED(field_matrix[i][j+1])) e = true;
//                if(e) c.setTip(CELL_TYPE.OPEN_DEATH);
//            }}


    }


    public boolean take_attac(int x, int y) {
        Cell c = field_matrix[x][y];


        if (c.getTip() == CELL_TYPE.OPEN_FREE) return false;
        if (c.getTip() == CELL_TYPE.OPEN_WOUND) return false;
        if (c.getTip() == CELL_TYPE.OPEN_DEATH) return false;


        if (c.getTip() == CELL_TYPE.UNOPENED) {
            c.setTip(CELL_TYPE.OPEN_FREE);
        }

        if (c.getTip() == CELL_TYPE.UNOPENED_OCCUPIED) {
            c.setTip(CELL_TYPE.OPEN_WOUND);


        }


        //  System.ouret.println("adttac" + x);
        // c.setTip(CELL_TYPE.OPEN_DEATH);

//        for (int i = 0; i < shipArrayList.size(); i++) {
//         //   field_matrix[shipArrayList.get(i).x][shipArrayList.get(i).y].setTip(CELL_TYPE.OPEN_DEATH);
//            System.out.println(shipArrayList.get(i).x + "  " + shipArrayList.get(i).y);
//        }
//        System.out.println("+++++++++++++++++++");
        search_dead_ships();
        auto_completion_the_card();

        return true;

    }

    public void search_dead_ships() {  ///поиск мертвых кораблей
        boolean r;
        Iterator<Ship> itr = shipArrayList.iterator();
        while (itr.hasNext()) {
            Ship s = itr.next();
            if (!s.orientation_horizontal) {
                r = true;
                for (int m = s.x; m < s.x + s.size; m++) {

                    if (field_matrix[m][s.y].getTip() != CELL_TYPE.OPEN_WOUND) r = false;
                }
                if (r)
                    for (int j = s.x; j < s.x + s.size; j++) {
                        field_matrix[j][s.y].setTip(CELL_TYPE.OPEN_DEATH);
                    }
            }

            if (s.orientation_horizontal) {
                r = true;
                for (int i = s.y; i < s.y + s.size; i++) {
                    if (field_matrix[s.x][i].getTip() != CELL_TYPE.OPEN_WOUND) r = false;

                }
                if (r)
                    for (int k = s.y; k < s.y + s.size; k++) {
                        field_matrix[s.x][k].setTip(CELL_TYPE.OPEN_DEATH);
                    }
            }
        }
    }


}




