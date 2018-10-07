package org.academiadecodigo.bootcamp.gameobjects.grid;

import org.academiadecodigo.bootcamp.Game;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public abstract class Grid {
    private int cols;
    private int rows;
    Rectangle grid;
    Color color;

    public Grid(int cols, int rows, Color color) {
        this.cols = cols;
        this.rows = rows;
        this.color = color;
        grid = new Rectangle(Game.PADDING, Game.PADDING, cols * Game.BRICK_WIDTH, rows * Game.BRICK_HEIGHT);
    }

    public void show() {
        grid.setColor(color);
        grid.fill();
    }

    public int getHeight() {
        return grid.getHeight();
    }

    public int getX() {
        return grid.getX();
    }

    public int getY() {
        return grid.getY();
    }

    public void moveDown(int y) {
        int center = (Game.CANVAS_WIDTH - grid.getWidth()) / 2;

        grid.translate(center, y);

    }

}
