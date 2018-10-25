package org.academiadecodigo.bootcamp.gameobjects.grid;

import org.academiadecodigo.bootcamp.Music;
import org.academiadecodigo.bootcamp.Resources;
import org.academiadecodigo.bootcamp.gameobjects.brick.Brick;

public class StackGrid extends Grid {

    private int comboScore;
    private final Brick[][] stackBricks;
    private boolean clearedBlock = false;

    public StackGrid(int cols, int rows) {
        super(cols, rows, 0);

        this.comboScore = 0;
        this.stackBricks = new Brick[cols][];

    }

    public void init() {

        for (int i = 0; i < stackBricks.length; i++) {

            this.stackBricks[i] = new Brick[rows];
        }
    }

    public boolean canReceiveBricks(Brick[] bricks) {

        //Add brick on StackGrid
        for (Brick brick : bricks) {
            clearedBlock = false;

            if (brick == null) {
                break;
            }

            int brickRow;
            int brickCol = brick.getCol();

            //Check Game Over
            if (stackBricks[brickCol][rows - 1] != null) {
                return false;
            }

            //Put block on Grid
            brickRow = placeBlock(brick);

            brick.show(rectangle.getX(), rectangle.getY());

            for (int rowsMoved = 0; rowsMoved <= rows - brickRow; rowsMoved++) {
                brick.moveDown();
            }

            checkCombination(brickCol, brickRow);
        }

        return true; //Keep going the game;
    }

    private int placeBlock(Brick brick) {

        int brickRow = 0;
        int col = brick.getCol();

        for (int row = 0; row < stackBricks.length; row++) {
            if ((stackBricks[col][row] == null)) {
                stackBricks[col][row] = brick;
                brickRow = row;
                break;
            }
        }

        return brickRow;
    }

    public int processComboScore() {

        int pointToReturn = comboScore;
        comboScore = 0;

        if (pointToReturn > 0) {
            new Music(Resources.SCORE, false).startMusic();
        }

        return pointToReturn;
    }

    private void checkCombination(int col, int row) {

        clearedBlock = false;

        if (row > 1) {
            checkComboBelow(col, row);
        }

        //Check for equals blocks up and down of this block
        if (row + 1 < this.cols && row - 1 >= 0) {
            if (stackBricks[col][row + 1] != null &&
                    stackBricks[col][row - 1] != null) {

                checkVerticalComboMiddle(col, row);
            }
        }

        //Check for 2 equals blocks on the left of this block
        if (col - 1 >= 0 && col - 2 >= 0) {
            if (stackBricks[col - 1][row] != null &&
                    stackBricks[col - 2][row] != null) {

                checkComboLeft(col, row);
            }
        }

        //Check for 2 equals blocks on the right of this block
        if (col + 1 < this.cols && col + 2 < this.cols) {
            if (stackBricks[col + 1][row] != null &&
                    stackBricks[col + 2][row] != null) {

                checkComboRight(col, row);
            }
        }

        //check for equals blocks on the right and left of this block
        if (col + 1 < this.cols && col - 1 >= 0) {
            if (stackBricks[col + 1][row] != null &&
                    stackBricks[col - 1][row] != null) {

                checkHorizontalComboMiddle(col, row);
            }
        }

        if (clearedBlock && stackBricks[col][row] != null) {
            stackBricks[col][row].hide();
            stackBricks[col][row] = null;
        }
    }

    private void checkComboBelow(int col, int row) {

        Brick first = stackBricks[col][row - 1];
        Brick second = stackBricks[col][row - 2];
        Brick current = stackBricks[col][row];

        if (current.isSameColor(first, second)) {
            deleteBrick(first);
            deleteBrick(second);
            scoreUp();
            clearedBlock = true;
        }
    }

    private void checkVerticalComboMiddle(int col, int row) {

        Brick over = stackBricks[col][row - 1];
        Brick under = stackBricks[col][row + 1];
        Brick current = stackBricks[col][row];

        if (current.isSameColor(over, under)) {
            deleteBrick(over, under);
            scoreUp();
            clearedBlock = true;
        }
    }

    private void checkComboLeft(int col, int row) {

        Brick first = stackBricks[col - 1][row];
        Brick second = stackBricks[col - 2][row];
        Brick current = stackBricks[col][row];

        if (current.isSameColor(first, second)) {
            deleteBrick(first, second);
            resetStack(col - 1, row);
            resetStack(col - 2, row);
            scoreUp();
            clearedBlock = true;
        }
    }

    private void checkComboRight(int col, int row) {

        Brick first = stackBricks[col + 1][row];
        Brick second = stackBricks[col + 2][row];
        Brick current = stackBricks[col][row];

        if (current.isSameColor(first, second)) {
            deleteBrick(first, second);
            resetStack(col + 1, row);
            resetStack(col + 2, row);
            scoreUp();
            clearedBlock = true;
        }
    }

    private void checkHorizontalComboMiddle(int col, int row) {

        Brick left = stackBricks[col - 1][row];
        Brick right = stackBricks[col + 1][row];
        Brick current = stackBricks[col][row];

        if (current.isSameColor(left, right)) {
            deleteBrick(left, right);
            resetStack(col + 1, row);
            resetStack(col - 1, row);
            scoreUp();
            clearedBlock = true;
        }
    }

    private void resetStack(int col, int row) {

        for (int currentRow = row + 1; currentRow < this.rows; currentRow++) {

            if (stackBricks[col][currentRow] == null) {
                continue;
            }

            stackBricks[col][currentRow - 1] = stackBricks[col][currentRow];

            stackBricks[col][currentRow].hide();
            stackBricks[col][currentRow] = null;

            Brick brick = stackBricks[col][currentRow - 1];
            brick.show(rectangle.getX(), rectangle.getY());

            brick.moveDown();

            checkCombination(col, currentRow - 1);
        }
    }

    @Override
    public void reset() {
        for (int i = 0; i < stackBricks.length; i++) {
            for (int j = 0; j < stackBricks.length; j++) {
                if (stackBricks[i][j] != null) {
                    stackBricks[i][j].hide();
                    stackBricks[i][j] = null;
                }
            }
        }
    }

    private void deleteBrick(Brick... bricks) {
        for (Brick brick : bricks) {
            int col = brick.getCol();
            int row = rows + 1 - brick.getRow();

            stackBricks[col][row].hide();
            stackBricks[col][row] = null;
        }
    }

    private void scoreUp() {
        comboScore += 50;
    }
}
