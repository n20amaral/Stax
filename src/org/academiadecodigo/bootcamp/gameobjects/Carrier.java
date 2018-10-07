package org.academiadecodigo.bootcamp.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.*;

public class Carrier implements KeyboardHandler {

    private Rectangle carrier;
    private Brick brick;
    private Brick buffer;
    private int col;
    private int row;
    private Keyboard keyboard;

    public Carrier() {
        this.col = 1;
        this.row = 1;
        this.carrier = new Rectangle(col,row,100,50);
        carrier.setColor(Color.CYAN);
        this.keyboard = new Keyboard(this);
        show();
    }

    public Brick getBuffer() {
        return buffer;
    }

    public void setBuffer(Brick buffer) {
        this.buffer = buffer;
    }

    private void show() {
        carrier.fill();
    }

    private void hide() {
        carrier.delete();
    }

    public void addBrick(Brick brick) {
        this.brick = brick;
    }

    private void releaseBrick() { // implement buffer here
        if (brick != null) {
            buffer = brick;
            brick = null;
        }
    }

    public void init() {

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

    }

    public void moveLeft() {
        if (col > 0) {
            hide();
            col--;
            if (brick != null) {
                brick.setCol(col);
            }
            show();
        }

    }

    public void moveRight() {
        if (col < 5) {
            hide();
            col++;
            if (brick != null) {
                brick.setCol(col);
            }
            show();
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
}
