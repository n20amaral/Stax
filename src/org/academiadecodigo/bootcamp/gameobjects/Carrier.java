package org.academiadecodigo.bootcamp.gameobjects;

import jdk.jshell.spi.ExecutionControl;
import org.academiadecodigo.bootcamp.Game;
import org.academiadecodigo.bootcamp.gameobjects.grid.*;
import org.academiadecodigo.bootcamp.gameobjects.brick.*;
import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.keyboard.*;

public class Carrier implements Displayable, KeyboardHandler {

    private CarrierGrid grid;
    private Rectangle carrier;
    private Color color = Color.DARK_GRAY;
    private Brick brick;
    private int col;
    private int row;
    private Keyboard keyboard;

    public Carrier(CarrierGrid grid) {
        this.grid = grid;
        this.col = 0;
        this.row = grid.getRows() - 1;
        this.carrier = new Rectangle(Game.PADDING + col * Game.BRICK_WIDTH,
                grid.getY() + row * Game.BRICK_HEIGHT,
                Game.BRICK_WIDTH,
                Game.BRICK_HEIGHT);
    }

    public int getCol() {
        return col;
    }

    public void addBrick(Brick brick) {
        if (brick.getCol() == col && this.brick != null) {
            this.brick = brick;
            brick.setRow(row - 1);

        }
    }

    private void releaseBrick() {

        if (brick != null) {
            grid.addReleasedBrick(brick);
            brick = null;
        }
    }

    public void init() {

        this.keyboard = new Keyboard(this);

        KeyboardEvent left = new KeyboardEvent();
        KeyboardEvent right = new KeyboardEvent();
        KeyboardEvent space = new KeyboardEvent();

        left.setKey(KeyboardEvent.KEY_LEFT);
        right.setKey(KeyboardEvent.KEY_RIGHT);
        space.setKey(KeyboardEvent.KEY_SPACE);

        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(left);
        keyboard.addEventListener(right);
        keyboard.addEventListener(space);

        carrier.setColor(color);
        carrier.fill();
    }

    public void moveLeft() {
        if (col > 0) {
            col--;
            if (brick != null) {
                brick.setCol(col);
            }
            //show();
            carrier.translate(0 - Game.BRICK_WIDTH, 0);
        }

    }

    public void moveRight() {
        if (col < grid.getCols() - 1) {
            col++;
            if (brick != null) {
                brick.setCol(col);
            }
            carrier.translate(Game.BRICK_WIDTH, 0);
            //show();
        }

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                moveLeft();
                System.out.println("LEFT");
                break;
            case KeyboardEvent.KEY_RIGHT:
                moveRight();
                System.out.println("RIGHT");
                break;
            case KeyboardEvent.KEY_SPACE:
                releaseBrick();
                System.out.println("SPACE");
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void show(int x, int y) {
    }

    @Override
    public void hide() {
        carrier.delete();
    }

    @Override
    public int getX() {
        return carrier.getX();
    }

    @Override
    public int getY() {
        return carrier.getY();
    }

    @Override
    public int getWidth() {
        return carrier.getWidth();
    }

    @Override
    public int getHeight() {
        return carrier.getHeight();
    }
}
