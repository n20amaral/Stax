package org.academiadecodigo.bootcamp.gameobjects.brick;

import org.academiadecodigo.bootcamp.Stax;

public class BrickFactory {

    public static Brick getNewBrick() {

        Brick brick = new Brick();

        int pickedColor = ((int) Math.random() * BrickColor.values().length);
        brick.setColor(BrickColor.values()[pickedColor]);

        int pickedColumn = ((int) Math.random() * Stax.MAX_COLS);
        brick.setCol(pickedColumn);

        return brick;
    }
}
