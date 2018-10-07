package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.gameobjects.Carrier;
import org.academiadecodigo.bootcamp.gameobjects.grid.Grid;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Game {
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;
    public static final int PADDING = 10;
    public static final int BRICK_WIDTH = 70;
    public static final int BRICK_HEIGHT = 25;

    private int cols;
    private Rectangle canvas = new Rectangle(PADDING, PADDING, CANVAS_WIDTH, CANVAS_HEIGHT);
    private Grid beltGrid;
    private Grid carrierGrid;
    private Grid stackGrid;
     

    public Game(int cols) {
        this.cols = cols;
    }

    public void init() {
        beltGrid = new Grid(cols, 10, Color.LIGHT_GRAY);
        carrierGrid = new Grid(cols, 2, Color.BLACK);
        stackGrid = new Grid(cols, 5, Color.LIGHT_GRAY);

        beltGrid.moveDown(0);
        carrierGrid.moveDown(beltGrid.getHeight());
        stackGrid.moveDown(beltGrid.getHeight() + carrierGrid.getHeight());

        canvas.draw();
        beltGrid.show();
        carrierGrid.show();
        stackGrid.show();
    }

    public void start() {
        Carrier carrier = new Carrier(carrierGrid);
        carrier.init();
    }
}
