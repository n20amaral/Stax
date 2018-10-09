package org.academiadecodigo.bootcamp.gameobjects.brick;

import org.academiadecodigo.bootcamp.Game;
import org.academiadecodigo.bootcamp.gameobjects.Displayable;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Brick implements Displayable {

    private int col;
    private int row = 0;
    private BrickColor color;
    private Rectangle rectangle;

    public Brick(int col, BrickColor color) {
        this.col = col;
        this.color = color;
        rectangle = new Rectangle(col * Game.BRICK_WIDTH + Game.PADDING,
                row * Game.BRICK_HEIGHT +  Game.PADDING,
                Game.BRICK_WIDTH,
                Game.BRICK_HEIGHT);
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
        rectangle.translate(0, Game.BRICK_HEIGHT);
    }

    @Override
    public void show(int x, int y) {
        rectangle.setColor(Color.RED);
        rectangle.fill();
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
