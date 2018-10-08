package org.academiadecodigo.bootcamp.gameobjects;

import org.academiadecodigo.bootcamp.Game;
import org.academiadecodigo.bootcamp.gameobjects.grid.*;
import org.academiadecodigo.bootcamp.gameobjects.brick.*;
import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.keyboard.*;

public class Carrier implements KeyboardHandler {

    private CarrierGrid grid;
 //   private Rectangle carrier;
    private Brick brick;
    private int col;
    private int row;
    private Keyboard keyboard;

    public Carrier(CarrierGrid grid) {
        this.col = 1;
        this.row = 1;
        this.grid = grid;
  /*      this.carrier = new Rectangle(grid.getX() + col * Game.BRICK_WIDTH,grid.getY() +  row * Game.BRICK_HEIGHT, Game.BRICK_WIDTH,Game.BRICK_HEIGHT);
        carrier.setColor(Color.CYAN);
        this.keyboard = new Keyboard(this);
        show(); */
    }

    public int getCol() {
        return col;
    }

  /*  private void show() {
        carrier.fill();
        if (brick != null){
            //brick.fill();
        }
    }

    private void hide() {
        carrier.delete();
    } */

    public void addBrick(Brick brick) {
        if (brick.getCol() == col && this.brick != null) {
            this.brick = brick;
            brick.setRow(row - 1);

        }
    }

    private void releaseBrick() { // implement buffer here
        if (brick != null) {
            grid.addReleasedBrick(brick);
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
            //hide();
            col--;
            if (brick != null) {
                brick.setCol(col);
            }
            //show();
           // carrier.translate(0 - Game.BRICK_WIDTH, 0);
        }

    }

    public void moveRight() {
        if (col < grid.getCols()-1) {
            //hide();
            col++;
            if (brick != null) {
                brick.setCol(col);
            }
           // carrier.translate(Game.BRICK_WIDTH, 0);
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
}
