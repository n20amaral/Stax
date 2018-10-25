package org.academiadecodigo.bootcamp.gameobjects.grid;

import org.academiadecodigo.bootcamp.GameConfigs;
import org.academiadecodigo.bootcamp.gameobjects.Displayable;
import org.academiadecodigo.bootcamp.gameobjects.brick.Brick;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

public abstract class Grid implements Displayable {
    protected final int cols;
    protected final int rows;
    protected final Brick[] bricks;
    protected Rectangle rectangle;
    private final Color gridColor = Color.BLACK;
    private Text gameMessage;

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
        gameMessage = new Text(GameConfigs.MID_CANVAS_WIDTH, GameConfigs.QUARTER_CANVAS_HEIGHT, gameOver);
        gameMessage.grow(100, 100);
        gameMessage.setColor(Color.RED);
        gameMessage.draw();
    }

    @Override
    public void show(int x, int y) {
        if (rectangle == null) {
            rectangle = new Rectangle(x, y, cols * GameConfigs.BRICK_WIDTH, rows * GameConfigs.BRICK_HEIGHT);
        }

        rectangle.setColor(gridColor);
        rectangle.draw();

    }

    @Override
    public void hide() {
        rectangle.delete();
    }


    @Override
    public int getY() {
        return rectangle.getY();
    }

    @Override
    public int getHeight() {
        return rectangle.getHeight();
    }

    public void hideMessage() {
        gameMessage.delete();
    }

    public void reset() {
        for (int i = 0; i < bricks.length; i++) {
            if (bricks[i] != null) {
                bricks[i].hide();
                bricks[i] = null;
            }
        }
    }
}
