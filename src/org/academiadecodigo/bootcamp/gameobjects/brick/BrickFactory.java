package org.academiadecodigo.bootcamp.gameobjects.brick;

import org.academiadecodigo.bootcamp.GameConfigs;

public class BrickFactory {

    public static Brick getNewBrick() {

        int pickedColor = (int) (Math.random() * BrickColor.values().length);
        int pickedColumn = (int) (Math.random() * GameConfigs.MAX_COLS);

        return new Brick(pickedColumn, BrickColor.values()[pickedColor]);
    }
}
