package org.academiadecodigo.bootcamp.gameobjects.brick;

import org.academiadecodigo.bootcamp.Game;
import org.academiadecodigo.bootcamp.Stax;

public class BrickFactory {

    public static Brick getNewBrick() {


        int pickedColor = (int)(Math.random() * BrickColor.values().length);
        int pickedColumn = (int)(Math.random() * Stax.MAX_COLS);

        Brick brick = new Brick(pickedColumn, BrickColor.values()[pickedColor]);
        return brick;
    }
}
