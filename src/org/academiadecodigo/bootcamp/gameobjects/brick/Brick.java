package org.academiadecodigo.bootcamp.gameobjects.brick;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Brick {

    private int col;
    private int row;
    private BrickColor color;
    private Rectangle brick;

    public Rectangle getBrick() {
        return brick;
    }

    public void setBrick(Rectangle brick) {
        this.brick = brick;
    }

    public BrickColor getColor() {
        return color;
    }

    public void setColor(BrickColor color) {
        this.color = color;
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

    public boolean moveDown() {
        return true;
    }

    public void show() {
        brick.fill();
    }

    public void hide() {
        brick.delete();
    }
}
