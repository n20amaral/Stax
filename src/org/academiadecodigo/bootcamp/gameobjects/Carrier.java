package org.academiadecodigo.bootcamp.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.*;

public class Carrier implements KeyboardHandler {

    private Rectangle carrier;
    private Brick brick;
    private int col;
    private int row;
    private Keyboard keyboard;
    private KeyboardEvent left;
    private KeyboardEvent right;

    public Carrier() {
        this.col = 1;
        this.row = 1;
        this.carrier = new Rectangle(col,row,100,50);
        carrier.setColor(Color.CYAN);
        this.keyboard = new Keyboard(this);
        show();
    }

    private void show() {
        carrier.fill();
    }

    private void hide() {
        carrier.delete();
    }

    public void init() {

        left = new KeyboardEvent();
        right = new KeyboardEvent();

        left.setKey(KeyboardEvent.KEY_LEFT);
        right.setKey(KeyboardEvent.KEY_RIGHT);

        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(left);
        keyboard.addEventListener(right);

    }

    public void moveLeft() {
        if (col > 0) {
            hide();
            col--;
            show();
        }

    }

    public void moveRight() {
        if (col < 5) {
            hide();
            col++;
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
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
