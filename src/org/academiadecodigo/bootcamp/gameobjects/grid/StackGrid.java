package org.academiadecodigo.bootcamp.gameobjects.grid;

import org.academiadecodigo.bootcamp.gameobjects.brick.Brick;

public class StackGrid extends Grid {

    private int pointsScore;

    private Brick[][] stackBrickCol;
    private Brick brickReceive;

    private int rows;
    private int cols;


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


            this.brickReceive = brick[i];

            if (this.brickReceive == null) {
                break;
            }


            brickCol = brickReceive.getCol();

            //Check Game Over
            if (stackBrickCol[brickCol][rows-1] != null) {
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
                int moveDown = myBrick.getRow();
                myBrick.setRow(0);

                for (int j = 0; j <= rows - brickRow; j++) {
                    myBrick.moveDown();
                }
            }

            //Check for score in Rows

            if ((brickRow != 0 && brickRow != 1)) {

                if ((stackBrickCol[brickCol][brickRow].getColor()) == (stackBrickCol[brickCol][brickRow - 1].getColor()) &&
                        (stackBrickCol[brickCol][brickRow].getColor()) == (stackBrickCol[brickCol][brickRow - 2].getColor())) {

                    pointsScore += 50;

                    stackBrickCol[brickCol][brickRow].hide();
                    stackBrickCol[brickCol][brickRow - 1].hide();
                    stackBrickCol[brickCol][brickRow - 2].hide();

                    stackBrickCol[brickCol][brickRow] = null;
                    stackBrickCol[brickCol][brickRow - 1] = null;
                    stackBrickCol[brickCol][brickRow - 2] = null;


                }
            }




        }


        return true; //Keep going the game;
    }


    public int resetPointsScore() {

        int pointToReturn = pointsScore;
        pointsScore = 0;

        return pointToReturn;


    }

    @Override
    public void reset() {
        super.reset();
        for (int i = 0; i < stackBrickCol.length; i++) {
            for (int j = 0; j < stackBrickCol.length; j++) {
                if (stackBrickCol[i][j] != null) {
                    stackBrickCol[i][j].hide();
                    stackBrickCol[i][j] = null;
                }
            }
        }
    }


}
