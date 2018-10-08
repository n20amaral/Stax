package org.academiadecodigo.bootcamp.gameobjects.grid;

import org.academiadecodigo.bootcamp.gameobjects.brick.Brick;
import org.academiadecodigo.simplegraphics.graphics.Color;

public class CarrierGrid extends Grid {

    private Brick[] releasedBricks = new Brick[5];
    public CarrierGrid(int cols, int rows, Color color) {
        super(cols, rows, color);
    }

    public Brick[] getReleasedBricks() {
        return releasedBricks;
    }

    public void addReleasedBrick(Brick releasedBrick) {

        for (int i=0; i<releasedBricks.length; i++) {

            if (releasedBricks[i] != null) {
                releasedBricks[i] = releasedBrick;
            }
        }
    }
}
