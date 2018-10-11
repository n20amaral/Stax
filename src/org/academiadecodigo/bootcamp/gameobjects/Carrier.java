package org.academiadecodigo.bootcamp.gameobjects;

import org.academiadecodigo.bootcamp.Game;
import org.academiadecodigo.bootcamp.Music;
import org.academiadecodigo.bootcamp.gameobjects.grid.*;
import org.academiadecodigo.bootcamp.gameobjects.brick.*;
import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.keyboard.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Carrier implements Displayable, KeyboardHandler {

    private CarrierGrid grid;
    private Rectangle carrier;
    private Picture carrierImg;
    private Color color = Color.WHITE;
    private Brick brick;
    private int col;
    private int row;
    private Keyboard keyboard;
    private boolean stop = false;

    public Carrier(CarrierGrid grid) {
        this.grid = grid;
        this.col = 0;
        this.row = grid.getRows() - 1;
        this.carrier = new Rectangle(Game.GRID_PADDING + col * Game.BRICK_WIDTH,
                grid.getY() + row * Game.BRICK_HEIGHT,
                Game.BRICK_WIDTH,
                Game.BRICK_HEIGHT);
        this.carrierImg = new Picture(Game.GRID_PADDING + col * Game.BRICK_WIDTH,grid.getY() + row * Game.BRICK_HEIGHT, "resources/jvm.png" );

    }

    public void setStop(boolean toStop) {
        stop = toStop;
    }

    public boolean addBrick(Brick brick) {
        if (brick.getCol() == col && this.brick == null) {
            this.brick = brick;
            Music music = new Music(brick.getColor().getAudioPath(),false);
            music.startMusic();
            brick.setRow(row - 1);
            return true;
        }
        return false;
    }

    private void releaseBrick() {

        if (brick != null) {
            grid.addReleasedBrick(brick);
            this.brick = null;
        }
    }

    public void init() {

        this.keyboard = new Keyboard(this);

        KeyboardEvent left = new KeyboardEvent();
        KeyboardEvent right = new KeyboardEvent();
        KeyboardEvent space = new KeyboardEvent();
        KeyboardEvent r = new KeyboardEvent();

        left.setKey(KeyboardEvent.KEY_LEFT);
        right.setKey(KeyboardEvent.KEY_RIGHT);
        space.setKey(KeyboardEvent.KEY_SPACE);
        r.setKey(KeyboardEvent.KEY_R);

        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        r.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(left);
        keyboard.addEventListener(right);
        keyboard.addEventListener(space);
        keyboard.addEventListener(r);

        carrier.setColor(color);
        carrier.fill();
        carrierImg.draw();
    }

    public void moveLeft() {

        if (col > 0) {
            col--;
            carrierImg.translate(0 - Game.BRICK_WIDTH, 0);
            carrier.translate(0 - Game.BRICK_WIDTH, 0);
            if (brick != null) {
                brick.moveLeft();
            }
        }

    }

    public void moveRight() {

        if (col < grid.getCols() - 1) {
            col++;
            carrierImg.translate(Game.BRICK_WIDTH, 0);
            carrier.translate(Game.BRICK_WIDTH, 0);
            if (brick != null) {
                brick.moveRight();
            }
        }

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (stop) {
            if (keyboardEvent.getKey() == KeyboardEvent.KEY_R) {
                Game.setReset();
            }
            return;
        }
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                moveLeft();
                break;
            case KeyboardEvent.KEY_RIGHT:
                moveRight();
                break;
            case KeyboardEvent.KEY_SPACE:
                releaseBrick();
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
        carrierImg.delete();
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

    public void reset() {
        if (brick != null) {
            brick.hide();
            brick = null;
        }
    }

}
