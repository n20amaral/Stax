package org.academiadecodigo.bootcamp.gameobjects.grid;

import org.academiadecodigo.bootcamp.Game;
import org.academiadecodigo.bootcamp.gameobjects.brick.Brick;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public abstract class Grid {
    private int cols;
    private int rows;
    private Brick[] bricks;

    public Grid(int cols, int rows, int maxBricks) {
        this.cols = cols;
        this.rows = rows;
        this.bricks = new Brick[maxBricks];
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }


}
