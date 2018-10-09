package org.academiadecodigo.bootcamp.gameobjects.brick;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Brick {

    private int col;
    private int row = 0;
    private BrickColor color;

    public Brick(int col, BrickColor color) {
        this.col = col;
        this.color = color;
    }

    public BrickColor getColor() {
        return color;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void moveDown() {
        row++;
    }
}
