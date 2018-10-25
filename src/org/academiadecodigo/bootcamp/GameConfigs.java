package org.academiadecodigo.bootcamp;

public class GameConfigs {

    public static final int MAX_COLS = 5;
    public static final int LIVES = 5;
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;
    public static final int PADDING = 10;

    public static final int BRICK_WIDTH = 70;
    public static final int BRICK_HEIGHT = 25;

    public static final int GRID_PADDING = (CANVAS_WIDTH - MAX_COLS * BRICK_WIDTH) / 2 + PADDING;
    public static final int MID_CANVAS_HEIGHT = CANVAS_HEIGHT / 2;
    public static final int MID_CANVAS_WIDTH = CANVAS_WIDTH / 2;
    public static final int QUARTER_CANVAS_HEIGHT = CANVAS_HEIGHT / 4;
    public static final int CARRIER_MAX_BRICKS = 5;
}
