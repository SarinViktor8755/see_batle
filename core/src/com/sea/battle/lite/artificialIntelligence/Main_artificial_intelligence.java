package com.sea.battle.lite.artificialIntelligence;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.sea.battle.lite.Coordinates;
import com.sea.battle.lite.playing_field.CELL_TYPE;
import com.sea.battle.lite.playing_field.Cell;

import java.util.ArrayList;
import java.util.Vector;

import jdk.javadoc.internal.doclets.toolkit.util.SimpleDocletException;

public class Main_artificial_intelligence {
    Coordinates coordinat;
    ArrayList<Coordinates> temp_result = new ArrayList<>();

    static byte LEFT_SIDE = 0;
    static byte RIGHT_SIDE = 1;
    static byte TOP_SIDE = 2;
    static byte BOTTOM_SIDE = 3;

    public Main_artificial_intelligence() {
        this.coordinat = new Coordinates();
        coordinat.setXY((byte) MathUtils.random(0, 9), (byte) MathUtils.random(0, 9));
        temp_result = new ArrayList<>();
    }

    public Coordinates decide() {
        coordinat.setXY((byte) MathUtils.random(0, 9), (byte) MathUtils.random(0, 9));
        return coordinat;
    }

    public Coordinates decide(Cell[][] field_matrix) {
        decide(field_matrix, MathUtils.random(1f));
        return coordinat;
    }

    public Coordinates decide(Cell[][] field_matrix, float chans) {
        setRandomHit();
        // алгоритм добития раненого
        if (isThereCellOfType(CELL_TYPE.OPEN_WOUND, field_matrix)) {
            blowToWoundedShip(field_matrix,.5f);
            return coordinat;
        }
        // алгоритм случайного удара
        if (MathUtils.randomBoolean(chans)) setTargetStrike(CELL_TYPE.UNOPENED, field_matrix);
        else setTargetStrike(CELL_TYPE.UNOPENED_OCCUPIED, field_matrix);
         setRandomHit();
        return coordinat;
    }

    private void setRandomHit() {
        coordinat.setXY((byte) MathUtils.random(0, 9), (byte) MathUtils.random(0, 9));
    }


    private void setTargetStrike(byte cell_tip, Cell[][] field_matrix) {
        if (isThereCellOfType(cell_tip, field_matrix)) {
            //////////////
            boolean r;
            do {
                coordinat.setXY((byte) MathUtils.random(0, 9), (byte) MathUtils.random(0, 9));
                if (field_matrix[coordinat.getX()][coordinat.getY()].getTip() == cell_tip)
                    r = true;
                else r = false;
                //   System.out.println(field_matrix[coordinat.getX()][coordinat.getY()].getTip());
            } while (!r);

        }

    }


    private boolean isThereCellOfType(byte cell_type, Cell[][] field_matrix) {
        temp_result.clear();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (field_matrix[i][j].getTip() == cell_type) return true;

            }
        }
        return false;
    }

    private void blowToWoundedShip(Cell[][] field_matrix, float chanc) {  //метод добивания раненого
        //    System.out.println(typeOfNeighboringCell(LEFT_SIDE, (byte) 0, (byte) 0,field_matrix));

        boolean r;
        do {
            coordinat.setXY((byte) MathUtils.random(0, 9), (byte) MathUtils.random(0, 9));
            if (field_matrix[coordinat.getX()][coordinat.getY()].getTip() == CELL_TYPE.OPEN_WOUND)
                r = true;
            else r = false;
        } while (!r);

        if(MathUtils.randomBoolean(chanc)) {
            if (typeOfNeighboringCell(LEFT_SIDE, coordinat.getX(), coordinat.getY(), field_matrix) == CELL_TYPE.UNOPENED_OCCUPIED)
                coordinat.setY((byte) (coordinat.getY() + 1));
            if (typeOfNeighboringCell(RIGHT_SIDE, coordinat.getX(), coordinat.getY(), field_matrix) == CELL_TYPE.UNOPENED_OCCUPIED)
                coordinat.setY((byte) (coordinat.getY() - 1));
            if (typeOfNeighboringCell(TOP_SIDE, coordinat.getX(), coordinat.getY(), field_matrix) == CELL_TYPE.UNOPENED_OCCUPIED)
                coordinat.setX((byte) (coordinat.getX() + 1));
            if (typeOfNeighboringCell(BOTTOM_SIDE, coordinat.getX(), coordinat.getY(), field_matrix) == CELL_TYPE.UNOPENED_OCCUPIED)
                coordinat.setX((byte) (coordinat.getX() - 1));
        }else {
            if (typeOfNeighboringCell(LEFT_SIDE, coordinat.getX(), coordinat.getY(), field_matrix) == CELL_TYPE.UNOPENED)
                coordinat.setY((byte) (coordinat.getY() + 1));
            if (typeOfNeighboringCell(RIGHT_SIDE, coordinat.getX(), coordinat.getY(), field_matrix) == CELL_TYPE.UNOPENED)
                coordinat.setY((byte) (coordinat.getY() - 1));
            if (typeOfNeighboringCell(TOP_SIDE, coordinat.getX(), coordinat.getY(), field_matrix) == CELL_TYPE.UNOPENED)
                coordinat.setX((byte) (coordinat.getX() + 1));
            if (typeOfNeighboringCell(BOTTOM_SIDE, coordinat.getX(), coordinat.getY(), field_matrix) == CELL_TYPE.UNOPENED)
                coordinat.setX((byte) (coordinat.getX() - 1));
        }

//        System.out.println(typeOfNeighboringCell(LEFT_SIDE, coordinat.getX(), coordinat.getY(), field_matrix));
//        System.out.println(typeOfNeighboringCell(RIGHT_SIDE, coordinat.getX(), coordinat.getY(), field_matrix));
//        System.out.println(typeOfNeighboringCell(TOP_SIDE, coordinat.getX(), coordinat.getY(), field_matrix));
//        System.out.println(typeOfNeighboringCell(BOTTOM_SIDE, coordinat.getX(), coordinat.getY(), field_matrix));
//        System.out.println("------------------");


//        try {
//            if (side == LEFT_SIDE) return field_matrix[x][y - 1].getTip();
//            if (side == RIGHT_SIDE) return field_matrix[x][y + 1].getTip();
//            if (side == TOP_SIDE) return field_matrix[x + 1][y].getTip();
//            if (side == BOTTOM_SIDE) return field_matrix[x - 1][y - 1].getTip();
//        }catch (ArrayIndexOutOfBoundsException e ){return CELL_TYPE.AIOBE;}

        try {
//            if (side == LEFT_SIDE) return field_matrix[x][y - 1].getTip();
//            if (side == RIGHT_SIDE) return field_matrix[x][y + 1].getTip();
//            if (side == TOP_SIDE) return field_matrix[x + 1][y].getTip();
//            if (side == BOTTOM_SIDE) return field_matrix[x - 1][y - 1].getTip();


        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }


    }

    private byte typeOfNeighboringCell(byte side, byte x, byte y, Cell[][] field_matrix) {
        try {
            if (side == LEFT_SIDE) return field_matrix[x][y + 1].getTip();
            if (side == RIGHT_SIDE) return field_matrix[x][y - 1].getTip();
            if (side == TOP_SIDE) return field_matrix[x + 1][y].getTip();
            if (side == BOTTOM_SIDE) return field_matrix[x - 1][y].getTip();
        } catch (ArrayIndexOutOfBoundsException e) {
            return CELL_TYPE.AIOBE;
        }


        return field_matrix[x][y].getTip();
    }


}








