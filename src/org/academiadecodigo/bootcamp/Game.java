package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.gameobjects.Carrier;
import org.academiadecodigo.bootcamp.gameobjects.brick.Brick;
import org.academiadecodigo.bootcamp.gameobjects.brick.BrickFactory;
import org.academiadecodigo.bootcamp.gameobjects.grid.*;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Game {
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;
    public static final int PADDING = 10;
    public static final int BRICK_WIDTH = 70;
    public static final int BRICK_HEIGHT = 25;


    private int cols;
    private int score = 0;
    private Rectangle canvas = new Rectangle(PADDING, PADDING, CANVAS_WIDTH, CANVAS_HEIGHT);
    private BeltGrid beltGrid;
    private CarrierGrid carrierGrid;
    private StackGrid stackGrid;
    private Carrier carrier;
    private Brick brick;
    private boolean gameOver;
    private int step = 2;

    public Game(int cols) {
        this.cols = cols;
    }

    public void init() {
        beltGrid = new BeltGrid(cols, 10);
        carrierGrid = new CarrierGrid(cols, 2);
        stackGrid = new StackGrid(cols, 5);

        canvas.draw();
        beltGrid.show(PADDING,PADDING);
        carrierGrid.show(PADDING,PADDING + beltGrid.getHeight());
        stackGrid.show(PADDING,PADDING + beltGrid.getHeight() + carrierGrid.getHeight());

        carrier = new Carrier(carrierGrid);
        carrier.init();
    }

    public void start() throws InterruptedException {

        while(!isGameOver()) {
            Thread.sleep(500);

            if (step == 2) {
                createBricks();
                step = 0;
            } else {
                step++;
            }

            moveBricks();
            finalRowCheck();
            droppedbricks();
            //addPoints();
        }
        
    }

    private void createBricks() {
        Brick brick = BrickFactory.getNewBrick();
        if(beltGrid.addNewBrick(brick)) {
            brick.show(Game.PADDING + brick.getCol() * Game.BRICK_WIDTH, Game.PADDING);
        }
    }

    private void moveBricks() {
        beltGrid.moveAllBricks();
    }

    private void finalRowCheck() {

        Brick brick = beltGrid.getFallingBrick();

        if (brick == null)
            return;

        if (!carrier.addBrick(brick)){
            brick.hide();
        }
    }

    private void droppedbricks() {

        Brick[] bricks = carrierGrid.getReleasedBricks();

        if (bricks != null && !stackGrid.receiveBrick(bricks)) {
            endgame();

        }

    }

    private void addPoints() {

        score += stackGrid.resetPointsScore();
    }

    private void endgame() {
        System.out.printf("GAME OVER");
       setGameOver();
    }

    private boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver() {
        this.gameOver = true;
    }
}
