package com.sea.battle.lite.playing_field;

import com.badlogic.gdx.math.MathUtils;

public class Field {

    private Cell[][] field_matrix;
    public static int size = 0;

    public Field() {
        create_playing_field();

        auto_ras();

        print_playing_field();
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

    }

    public void auto_ras() {
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
        //  System.out.println("iteration"+iteration);

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
                //     System.out.println(i + "  " + ship.y);
                field_matrix[i][ship.y].tip = CELL_TYPE.OPEN_DEATH;

            }
            return true;
        } else {
            for (int i = ship.y; i < ship.y + ship.size; i++) {
                field_matrix[ship.x][i].tip = CELL_TYPE.OPEN_DEATH;
                //     System.out.println(ship.x + "  " + i);

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


    public void auto_completion_the_card() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell c = field_matrix[i][j];
                if (c.getTip() == CELL_TYPE.OPEN_DEATH) {


                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            try {
                                if (field_matrix[i + k][j + l].getTip() != CELL_TYPE.OPEN_DEATH)
                                    field_matrix[i + k][j + l].setTip(CELL_TYPE.OPEN_WOUND);
                            } catch (ArrayIndexOutOfBoundsException e) {
                                continue;
                            }
                        }

                    }


                }

            }
        }
    }


}
