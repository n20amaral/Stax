package org.academiadecodigo.bootcamp.gameobjects.grid;

import org.academiadecodigo.bootcamp.gameobjects.brick.Brick;
import org.academiadecodigo.bootcamp.gameobjects.brick.BrickColor;
import org.academiadecodigo.simplegraphics.graphics.Color;

import java.util.ListIterator;

public class StackGrid extends Grid {

    private int pointsScore;

    private Brick[][] stackBrickRow;
    private Brick brickReceive;

    private int rows;
    private int cols;


    public StackGrid(int cols, int rows, int maxBricks) {
        super(cols, rows, maxBricks);

        this.pointsScore = 0;

        this.stackBrickRow = new Brick[rows][];

        this.rows = rows;

        this.cols = cols;

        createCol();
    }

    private void createCol() {

        for (int i = 0; i < stackBrickRow.length; i++) {

            this.stackBrickRow[i] = new Brick[cols];
        }

    }


    public boolean ReceiveBrick(Brick[] brick) {

        int brickCol;
        int brickRow = 0;


        //Add brick on StackGrid
        for (int i = 0; i < brick.length; i++) {


            this.brickReceive = brick[i];

            brickCol = brickReceive.getCol();


            if (brick[i].getRow() <= rows) {
                return false; //Game Over
            }


            for (int e = 0; e < stackBrickRow.length; e++) {

                if (!stackBrickRow[e][brickCol].equals(null)) {

                    continue;
                }

                stackBrickRow[e][brickCol] = brick[i];
                brickRow = e;
                break;

            }


            //Check for score in Columns

            if (!(brickCol == 0 || brickCol == 1)) {

                if ((stackBrickRow[brickRow][brickCol].getColor()).equals((stackBrickRow[brickRow][brickCol - 1].getColor())) &&
                        (stackBrickRow[brickRow][brickCol].getColor()).equals(stackBrickRow[brickRow][brickCol - 2].getColor())) {

                    pointsScore += 50;

                    stackBrickRow[brickRow][brickCol] = null;
                    stackBrickRow[brickRow][brickCol - 1] = null;
                    stackBrickRow[brickRow][brickCol - 2] = null;


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
