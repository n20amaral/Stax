package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Game {
    public static final int PADDING = 10;
    public static final int TILE_SIZE = 50;


    private Rectangle canvas = new Rectangle(PADDING, PADDING, 800, 600);
    private Rectangle tileGrid;

    public Game(int cols, int rows) {
        tileGrid = new Rectangle(PADDING, PADDING, cols * TILE_SIZE, rows * TILE_SIZE);
    }

    public void init() {
        canvas.draw();
        tileGrid.setColor(Color.LIGHT_GRAY);
        tileGrid.fill();
    }

    public void start() {

    }
}
