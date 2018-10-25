package org.academiadecodigo.bootcamp.gameobjects.grid;

import org.academiadecodigo.bootcamp.gameobjects.brick.Brick;

public class BeltGrid extends Grid {

    public BeltGrid(int cols, int rows) {
        super(cols, rows, rows);
    }

    public boolean addNewBrick(Brick brick) {

        for (int i = 0; i < bricks.length; i++) {
            if (bricks[i] == null) {
                bricks[i] = brick;
                return true;
            }
        }
        return false;
    }

    public void moveAllBricks() {
        for (Brick brick : bricks) {

            if (brick == null) {
                continue;
            }

            brick.moveDown();
        }
    }

    public Brick getFallingBrick() {

        Brick fallingBrick = null;

        for (int i = 0; i < bricks.length; i++) {

            if (bricks[i] == null) {
                continue;
            }

            if (bricks[i].getRow() == getRows()) {
                fallingBrick = bricks[i];
                bricks[i] = null;
            }
        }
        return fallingBrick;
    }

}
