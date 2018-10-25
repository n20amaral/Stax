package org.academiadecodigo.bootcamp.gameobjects;

import org.academiadecodigo.bootcamp.Game;
import org.academiadecodigo.bootcamp.GameConfigs;
import org.academiadecodigo.bootcamp.Music;
import org.academiadecodigo.bootcamp.Resources;
import org.academiadecodigo.bootcamp.gameobjects.grid.*;
import org.academiadecodigo.bootcamp.gameobjects.brick.*;
import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.keyboard.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Carrier implements Displayable, KeyboardHandler {

    private final CarrierGrid grid;
    private final Picture carrierImg;
    private Brick brick;
    private int col;
    private final int row;
    private boolean stop = false;

    public Carrier(CarrierGrid grid) {
        this.grid = grid;
        this.col = 0;
        this.row = grid.getRows() - 1;

        int carrierX = GameConfigs.GRID_PADDING + col * GameConfigs.BRICK_WIDTH;
        int carrierY = grid.getY() + row * GameConfigs.BRICK_HEIGHT;

        this.carrierImg = new Picture(carrierX, carrierY, Resources.CARRIER);
    }

    public void setStop(boolean toStop) {
        stop = toStop;
    }

    public boolean addBrick(Brick brick) {
        if (brick.getCol() == col && this.brick == null) {
            this.brick = brick;
            new Music(brick.getColor().getAudioPath(), false).startMusic();
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

        Keyboard keyboard = new Keyboard(this);

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

        carrierImg.draw();
    }

    private void moveLeft() {

        if (col > 0) {
            col--;
            carrierImg.translate(0 - GameConfigs.BRICK_WIDTH, 0);
            if (brick != null) {
                brick.moveLeft();
            }
        }

    }

    private void moveRight() {

        if (col < grid.getCols() - 1) {
            col++;
            carrierImg.translate(GameConfigs.BRICK_WIDTH, 0);
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
        // TODO: empty method body
    }

    @Override
    public void hide() {
        carrierImg.delete();
    }

    @Override
    public int getY() {
        return carrierImg.getY();
    }

    @Override
    public int getHeight() {
        return carrierImg.getHeight();
    }

    public void reset() {
        if (brick != null) {
            brick.hide();
            brick = null;
        }
    }
}
