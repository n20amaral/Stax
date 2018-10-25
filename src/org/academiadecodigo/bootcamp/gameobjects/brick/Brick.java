package org.academiadecodigo.bootcamp.gameobjects.brick;

import org.academiadecodigo.bootcamp.GameConfigs;
import org.academiadecodigo.bootcamp.gameobjects.Displayable;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Brick implements Displayable {

    private int col;
    private int row = 0;
    private final BrickColor color;
    private final Rectangle rectangle;

    public Brick(int col, BrickColor color) {
        this.col = col;
        this.color = color;
        rectangle = new Rectangle(col * GameConfigs.BRICK_WIDTH + GameConfigs.GRID_PADDING,
                row * GameConfigs.BRICK_HEIGHT + GameConfigs.PADDING,
                GameConfigs.BRICK_WIDTH,
                GameConfigs.BRICK_HEIGHT);
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

    public void setRow(int row) {
        this.row = row;
    }

    public void moveDown() {
        row++;
        rectangle.translate(0, GameConfigs.BRICK_HEIGHT);
    }

    public void moveLeft() {
        col--;
        rectangle.translate(0 - GameConfigs.BRICK_WIDTH, 0);
    }

    public void moveRight() {
        col++;
        rectangle.translate(GameConfigs.BRICK_WIDTH, 0);
    }

    @Override
    public void show(int x, int y) {
        rectangle.setColor(color.getColor());
        rectangle.fill();
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

    public boolean isSameColor(Brick... bricks) {
        for (Brick brick : bricks) {
            if (color != brick.getColor()) {
                return false;
            }
        }
        return true;
    }
}
