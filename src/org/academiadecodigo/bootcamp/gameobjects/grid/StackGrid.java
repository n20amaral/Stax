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

        int brickCol;
        int brickRow;


        //Add brick on StackGrid

        for (int i = 0; i < brick.length; i++) {


            this.brickReceive = brick[i];

            if (this.brickReceive == null) {
                break;
            }


            brickCol = brickReceive.getCol();
            brickRow = 0;

            if (brickRow >= rows) {
                return false; //Game Over
            }


            for (int e = 0; e < stackBrickCol.length; e++) {
                if (!(stackBrickCol[brickCol][e] == null)) {

                    continue;
                }

                stackBrickCol[brickCol][e] = this.brickReceive;
                brickRow = e;

                break;

            }


            //Check for score in Columns

            if ((brickRow != 0 && brickRow != 1)) {

                if ((stackBrickCol[brickCol][brickRow].getColor()) == (stackBrickCol[brickCol][brickRow - 1].getColor()) &&
                        (stackBrickCol[brickCol][brickRow].getColor()) == (stackBrickCol[brickCol][brickRow - 2].getColor())) {

                    pointsScore += 50;

                    stackBrickCol[brickCol][brickRow].hide();
                    stackBrickCol[brickCol][brickRow - 1].hide();
                    stackBrickCol[brickCol][brickRow - 2].hide();


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


}
