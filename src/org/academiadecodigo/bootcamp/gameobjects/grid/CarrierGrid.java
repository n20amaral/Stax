package org.academiadecodigo.bootcamp.gameobjects.grid;

import org.academiadecodigo.bootcamp.gameobjects.brick.Brick;
import org.academiadecodigo.simplegraphics.graphics.Color;

public class CarrierGrid extends Grid {

    private int cols;

    public CarrierGrid(int cols, int rows) {
        super(cols, rows, 5);
        this.cols = cols;
    }

    public int getCols() {
        return cols;
    }

    public Brick[] getReleasedBricks() {
        return bricks;
    }

    public void addReleasedBrick(Brick releasedBrick) {

        for (int i=0; i< bricks.length; i++) {

            if (bricks[i] != null) {
                bricks[i] = releasedBrick;
            }
        }
    }
}
