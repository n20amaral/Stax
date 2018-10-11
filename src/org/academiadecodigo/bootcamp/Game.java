package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.gameobjects.Carrier;
import org.academiadecodigo.bootcamp.gameobjects.brick.Brick;
import org.academiadecodigo.bootcamp.gameobjects.brick.BrickFactory;
import org.academiadecodigo.bootcamp.gameobjects.grid.*;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;
    public static final int PADDING = 10;
    public static final int BRICK_WIDTH = 70;
    public static final int BRICK_HEIGHT = 25;
    public static final int GRID_PADDING = (CANVAS_WIDTH - Stax.MAX_COLS * BRICK_WIDTH) / 2 + PADDING;
    private static boolean resetStatus = false;

    private int lives = 5;
    private int cols;
    private int score = 0;
    private Rectangle canvas = new Rectangle(PADDING, PADDING, CANVAS_WIDTH, CANVAS_HEIGHT);
    private BeltGrid beltGrid;
    private CarrierGrid carrierGrid;
    private StackGrid stackGrid;
    private Carrier carrier;
    private boolean gameOver;
    private int step = 5;
    private Text textScore = new Text(30,20,"Score: " + score);
    private Text textLives = new Text(30, 60,"Lives: " + lives);
    private Text textPress = new Text(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2, "Press R");
    private Picture backGroundImage = new Picture(PADDING, PADDING, "resources/background.jpg" );

    public Game(int cols) {
        this.cols = cols;
    }

    public void init() {
        beltGrid = new BeltGrid(cols, 10);
        carrierGrid = new CarrierGrid(cols, 2);
        stackGrid = new StackGrid(cols, 5);



        canvas.draw();
        backGroundImage.draw();
        textScore.grow(20,20);
        textScore.setColor(Color.WHITE);
        textScore.draw();
        textLives.grow(20,20);
        textLives.setColor(Color.WHITE);
        textLives.draw();
        beltGrid.show(GRID_PADDING,PADDING);
        carrierGrid.show(GRID_PADDING,PADDING + beltGrid.getHeight());
        stackGrid.show(GRID_PADDING,PADDING + beltGrid.getHeight() + carrierGrid.getHeight());
        textPress.grow(50,50);
        textPress.setColor(Color.RED);

        carrier = new Carrier(carrierGrid);
        carrier.init();
    }

    public void start() throws InterruptedException {

        Music music = new Music("/resources/music.wav", true);
        music.startMusic();

        while(!isGameOver()) {

            if (step == 5) {
                createBricks();
                step = 0;
            } else {
                step++;
            }

            Thread.sleep(250);

            moveBricks();
            finalRowCheck();
            droppedbricks();
            addPoints();
        }

        while (true) {
            Thread.sleep(100);
            if (resetStatus == true) {
                reset();
                break;
            }
        }

        start();
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
            this.lives--;
            textLives.setText("Lives: " + lives);
            textLives.draw();

            if (lives == 0) {
                endgame();
            }
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
        textScore.setText("Score: " + score);
        textScore.draw();

    }

    private void endgame() {
        carrier.setStop(true);
        Music gameOver = new Music("/resources/gameover.wav", false);
        gameOver.startMusic();
        beltGrid.endgameMessage("Game Over");
        textPress.draw();
        setGameOver();
    }

    private boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver() {
        this.gameOver = true;
    }

    public void reset() {
        beltGrid.reset();
        carrierGrid.reset();
        stackGrid.reset();
        carrier.reset();
        carrier.setStop(false);
        beltGrid.hideMessage();
        textPress.delete();
        lives = 5;
        textLives.setText("Lives: " + lives);
        textLives.draw();
        score = 0;
        textScore.setText("Score: " + score);
        textScore.draw();
        gameOver = false;
        resetStatus = false;
    }

    public static void setReset() {
        resetStatus = true;
    }
}
