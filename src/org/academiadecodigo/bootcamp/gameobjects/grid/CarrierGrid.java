package org.academiadecodigo.bootcamp.gameobjects.grid;

import org.academiadecodigo.bootcamp.gameobjects.brick.Brick;
import org.academiadecodigo.simplegraphics.graphics.Color;

public class CarrierGrid extends Grid {

    private Brick releasedBrick;
    public CarrierGrid(int cols, int rows, Color color) {
        super(cols, rows, color);
    }

    public Brick getReleasedBrick() {
        return releasedBrick;
    }

    public void setReleasedBrick(Brick releasedBrick) {
        this.releasedBrick = releasedBrick;
    }
}
