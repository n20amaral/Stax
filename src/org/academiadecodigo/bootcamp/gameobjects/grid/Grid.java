package org.academiadecodigo.bootcamp.gameobjects.grid;

import org.academiadecodigo.bootcamp.Game;
import org.academiadecodigo.bootcamp.gameobjects.Displayable;
import org.academiadecodigo.bootcamp.gameobjects.brick.Brick;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

public abstract class Grid implements Displayable {
    private int cols;
    private int rows;
    protected Brick[] bricks;
    protected Rectangle rectangle;
    private final Color gridColor = Color.BLACK;

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

    public void endgameMessage(String gameOver) {
        Text text = new Text(Game.CANVAS_WIDTH / 2, Game.CANVAS_HEIGHT / 4, gameOver);
        text.grow(100,100);
        text.setColor(Color.RED);
        text.draw();
    }

    @Override
    public void show(int x, int y) {
        if (rectangle == null) {
            rectangle = new Rectangle(x, y, cols * Game.BRICK_WIDTH, rows * Game.BRICK_HEIGHT);
        }

        rectangle.setColor(gridColor);
        rectangle.draw();

    }

    @Override
    public void hide() {
        rectangle.delete();
    }

    @Override
    public int getX() {
        return rectangle.getX();
    }

    @Override
    public int getY() {
        return rectangle.getY();
    }

    @Override
    public int getWidth() {
        return rectangle.getWidth();
    }

    @Override
    public int getHeight() {
        return rectangle.getHeight();
    }
}
