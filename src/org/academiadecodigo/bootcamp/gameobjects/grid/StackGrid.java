package org.academiadecodigo.bootcamp.gameobjects.grid;

import org.academiadecodigo.bootcamp.gameobjects.brick.Brick;

public class StackGrid extends Grid {

    private int pointsScore;

    private Brick[][] stackBrickCol;
    private Brick brickReceive;

    private int rows;
    private int cols;
    private boolean cleanBlock = false;

    public StackGrid(int cols, int rows) {
        super(cols, rows, 0);

        this.pointsScore = 0;

        this.stackBrickCol = new Brick[cols][];

        this.rows = rows;

        this.cols = cols;

        createRows();
    }

    private void createRows() {

        for (int i = 0; i < stackBrickCol.length; i++) {

            this.stackBrickCol[i] = new Brick[rows];
        }

    }


    public boolean receiveBrick(Brick[] brick) {


        int brickCol = 0;
        int brickRow = 0;


        //Add brick on StackGrid

        for (int i = 0; i < brick.length; i++) {
            cleanBlock = false;

            this.brickReceive = brick[i];

            if (this.brickReceive == null) {
                break;
            }


            brickCol = brickReceive.getCol();

            //Check Game Over
            if (stackBrickCol[brickCol][rows - 1] != null) {
                return false;
            }

            //Put block on Grid
            for (int e = 0; e < stackBrickCol.length; e++) {
                if (!(stackBrickCol[brickCol][e] == null)) {

                    continue;
                }

                stackBrickCol[brickCol][e] = this.brickReceive;
                brickRow = e;
                break;
            }

            if (brickReceive != null) {
                Brick myBrick = brickReceive;
                myBrick.show(rectangle.getX(), rectangle.getY());
                myBrick.setRow(0);

                for (int j = 0; j <= rows - brickRow; j++) {
                    myBrick.moveDown();
                }
            }

            itScore(brickCol, brickRow);


        }


        return true; //Keep going the game;
    }


    public int resetPointsScore() {

        int pointToReturn = pointsScore;
        pointsScore = 0;

        return pointToReturn;


    }

    private void itScore(int col, int row) {

        cleanBlock = false;

        //Check for equals blocks down of this block

        if ((row != 0 && row != 1)) {

            if (stackBrickCol[col][row - 1] != null && stackBrickCol[col][row - 2] != null) {

                upColumns(col, row);
            }
        }

        //Check for equals blocks up of this block
        if (row + 1 < this.rows && col + 2 < this.rows) {
            if (stackBrickCol[col][row + 1] != null && stackBrickCol[col][row + 2] != null) {

                downColumns(col, row);

            }
        }

        //Check for equals blocks up and down of this block
        if (row + 1 < this.cols && row - 1 >= 0) {
            if (stackBrickCol[col][row + 1] != null && stackBrickCol[col][row - 1] != null) {

                middleColumns(col, row);

            }
        }


        //Check for 2 equals blocks on the left of this block
        if (col - 1 >= 0 && col - 2 >= 0) {

            if (stackBrickCol[col - 1][row] != null && stackBrickCol[col - 2][row] != null) {

                leftRows(col, row);

            }
        }

        //Check for 2 equals blocks on the right of this block
        if (col + 1 < this.cols && col + 2 < this.cols) {
            if (stackBrickCol[col + 1][row] != null && stackBrickCol[col + 2][row] != null) {

                rightRows(col, row);

            }
        }

        //check for equals blocks on the right and left of this block

        if (col + 1 < this.cols && col - 1 >= 0) {
            if (stackBrickCol[col + 1][row] != null && stackBrickCol[col - 1][row] != null) {

                middleRows(col, row);

            }
        }


        if (cleanBlock) {
            stackBrickCol[col][row].hide();
            stackBrickCol[col][row] = null;
        }
    }


    private void upColumns(int col, int row) {

        if ((stackBrickCol[col][row].getColor()) == (stackBrickCol[col][row - 1].getColor()) &&
                (stackBrickCol[col][row].getColor()) == (stackBrickCol[col][row - 2].getColor())) {

            pointsScore += 50;

            stackBrickCol[col][row - 1].hide();
            stackBrickCol[col][row - 2].hide();


            stackBrickCol[col][row - 1] = null;
            stackBrickCol[col][row - 2] = null;

            cleanBlock = true;
        }
    }

    private void downColumns(int col, int row) {

        if ((stackBrickCol[col][row].getColor()) == (stackBrickCol[col][row + 1].getColor()) &&
                (stackBrickCol[col][row].getColor()) == (stackBrickCol[col][row + 2].getColor())) {

            pointsScore += 50;

            stackBrickCol[col][row + 1].hide();
            stackBrickCol[col][row + 2].hide();


            stackBrickCol[col][row + 1] = null;
            stackBrickCol[col][row + 2] = null;

            cleanBlock = true;
        }
    }


    private void middleColumns(int col, int row) {

        if ((stackBrickCol[col][row].getColor()) == (stackBrickCol[col][row - 1].getColor()) &&
                (stackBrickCol[col][row].getColor()) == (stackBrickCol[col][row + 1].getColor())) {

            pointsScore += 50;

            stackBrickCol[col][row - 1].hide();
            stackBrickCol[col][row + 1].hide();


            stackBrickCol[col][row - 1] = null;
            stackBrickCol[col][row + 1] = null;

            cleanBlock = true;
        }
    }


    private void leftRows(int col, int row) {

        if (stackBrickCol[col][row].getColor() == stackBrickCol[col - 1][row].getColor() &&
                stackBrickCol[col][row].getColor() == stackBrickCol[col - 2][row].getColor()) {

            pointsScore += 50;

            stackBrickCol[col - 1][row].hide();
            stackBrickCol[col - 2][row].hide();

            stackBrickCol[col - 1][row] = null;
            stackBrickCol[col - 2][row] = null;


            resetStack(col - 1, row);
            resetStack(col - 2, row);

            cleanBlock = true;
        }
    }

    private void rightRows(int col, int row) {

        if (stackBrickCol[col][row].getColor() == stackBrickCol[col + 1][row].getColor() &&
                stackBrickCol[col][row].getColor() == stackBrickCol[col + 2][row].getColor()) {

            pointsScore += 50;

            stackBrickCol[col + 1][row].hide();
            stackBrickCol[col + 2][row].hide();

            stackBrickCol[col + 1][row] = null;
            stackBrickCol[col + 2][row] = null;


            resetStack(col + 1, row);
            resetStack(col + 2, row);

            cleanBlock = true;
        }
    }

    private void middleRows(int col, int row) {

        if (stackBrickCol[col][row].getColor() == stackBrickCol[col + 1][row].getColor() &&
                stackBrickCol[col][row].getColor() == stackBrickCol[col - 1][row].getColor()) {

            pointsScore += 50;

            stackBrickCol[col + 1][row].hide();
            stackBrickCol[col - 1][row].hide();

            stackBrickCol[col + 1][row] = null;
            stackBrickCol[col - 1][row] = null;


            resetStack(col + 1, row);
            resetStack(col - 1, row);

            cleanBlock = true;
        }


    }


    private void resetStack(int col, int row) {

        for (int i = row + 1; i < this.rows; i++) {

            if (stackBrickCol[col][i] != null) {

                stackBrickCol[col][i - 1] = stackBrickCol[col][i];


                stackBrickCol[col][i].hide();
                stackBrickCol[col][i] = null;


                Brick myBrick = stackBrickCol[col][i - 1];
                myBrick.show(rectangle.getX(), rectangle.getY());
                myBrick.setRow(0);

                myBrick.moveDown();

                itScore(col, i - 1);


            }

        }


    }


}
