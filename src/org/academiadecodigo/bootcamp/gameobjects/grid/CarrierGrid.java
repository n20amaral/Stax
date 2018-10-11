package org.academiadecodigo.bootcamp.gameobjects.grid;

import org.academiadecodigo.bootcamp.gameobjects.brick.Brick;
import org.academiadecodigo.simplegraphics.graphics.Color;

import java.text.CollationElementIterator;
import java.util.Collections;

public class CarrierGrid extends Grid {

    private int cols;

    public CarrierGrid(int cols, int rows) {
        super(cols, rows, 5);
        this.cols = cols;
    }

    public int getCols() {
        return cols;
    }

    private boolean isAnyReleasedBricks(){
        for (int i = 0; i < bricks.length; i++) {
            if (bricks[i] != null) {
                return true;
            }
        }

        return false;
    }

    public Brick[] getReleasedBricks() {
        if (isAnyReleasedBricks()) {
            Brick[] toRelease = bricks.clone();

            for (int i = 0; i < bricks.length; i++) {
                bricks[i] = null;
            }
            return toRelease;
        }

        return null;
    }

    public void addReleasedBrick(Brick brick) {
        brick.hide();
        for (int i = 0; i < bricks.length; i++) {
            if (bricks[i] == null) {
                bricks[i] = brick;
                break;
            }
        }
    }

    @Override
    public void show(int x, int y) {
        super.show(x,y);
        rectangle.setColor(Color.BLACK);
        rectangle.draw();

    }
}
