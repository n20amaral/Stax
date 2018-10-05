package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.gameobjects.Grid;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Game {
    public static final int PADDING = 10;
    public static final int BRICK_WIDTH = 50;
    public static final int BRICK_HEIGHT = 50;

    private Rectangle canvas = new Rectangle(PADDING, PADDING, 800, 600);
    private Grid beltGrid;
    private Grid carrierGrid;
    private Grid stackGrid;
     

    public Game(int cols, int rows) {

    }

    public void init() {
        canvas.draw();
    }

    public void start() {

    }
}
