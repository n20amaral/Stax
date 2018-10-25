package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.gameobjects.Carrier;
import org.academiadecodigo.bootcamp.gameobjects.brick.*;
import org.academiadecodigo.bootcamp.gameobjects.grid.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import static org.academiadecodigo.bootcamp.GameConfigs.*;

public class Game {

    private static boolean resetStatus = false;
    private boolean gameOver = false;

    private int lives = GameConfigs.LIVES;
    private int step = 5;
    private int score = 0;

    private BeltGrid beltGrid;
    private CarrierGrid carrierGrid;
    private StackGrid stackGrid;
    private Carrier carrier;
    private GameInfo info;

    public void init() {

        final Picture backGroundImage = new Picture(PADDING, PADDING, Resources.BACKGROUND);
        backGroundImage.draw();

        info = new GameInfo();
        info.init();

        beltGrid = new BeltGrid(GameConfigs.MAX_COLS, 10);
        carrierGrid = new CarrierGrid(GameConfigs.MAX_COLS, 2);
        stackGrid = new StackGrid(GameConfigs.MAX_COLS, 5);
        stackGrid.init();

        beltGrid.show(GRID_PADDING, PADDING);
        carrierGrid.show(GRID_PADDING, PADDING + beltGrid.getHeight());
        stackGrid.show(GRID_PADDING, PADDING + beltGrid.getHeight() + carrierGrid.getHeight());

        carrier = new Carrier(carrierGrid);
        carrier.init();
    }

    public void start() throws InterruptedException {

        new Music(Resources.MUSIC, true).startMusic();

        while (!gameOver) {

            if (step == 5) {
                createBricks();
                step = 0;
            } else {
                step++;
            }

            Thread.sleep(250);

            moveBricks();
            checkMissedBricks();
            droppedBricks();
            addPoints();
        }

        while (true) {
            Thread.sleep(100);
            if (resetStatus) {
                reset();
                break;
            }
        }

        start();
    }

    private void createBricks() {
        Brick brick = BrickFactory.getNewBrick();
        if (beltGrid.addNewBrick(brick)) {
            brick.show(PADDING + brick.getCol() * BRICK_WIDTH, PADDING);
        }
    }

    private void moveBricks() {
        beltGrid.moveAllBricks();
    }

    private void checkMissedBricks() {

        Brick brick = beltGrid.getFallingBrick();

        if (brick == null)
            return;

        if (!carrier.addBrick(brick)) {
            brick.hide();
            this.lives--;
            info.getTextLives().setText("Lives: " + lives);
            info.getTextLives().draw();

            if (lives == 0) {
                endgame();
            }
        }
    }

    private void droppedBricks() {

        Brick[] bricks = carrierGrid.getReleasedBricks();

        if (bricks != null && !stackGrid.canReceiveBricks(bricks)) {
            endgame();
        }
    }

    private void addPoints() {

        score += stackGrid.processComboScore();
        info.getTextScore().setText("Score: " + score);
        info.getTextScore().draw();
    }

    private void endgame() {
        carrier.setStop(true);
        new Music(Resources.GAME_OVER, false).startMusic();
        beltGrid.endgameMessage("Game Over");
        info.getTextPress().draw();
        gameOver = true;
    }

    private void reset() {
        beltGrid.reset();
        carrierGrid.reset();
        stackGrid.reset();
        carrier.reset();
        carrier.setStop(false);
        beltGrid.hideMessage();

        lives = GameConfigs.LIVES;
        score = 0;

        info.getTextPress().delete();
        info.getTextLives().setText("Lives: " + lives);
        info.getTextLives().draw();
        info.getTextScore().setText("Score: " + score);
        info.getTextScore().draw();
        gameOver = false;
        resetStatus = false;
    }

    public static void setReset() {
        resetStatus = true;
    }
}
