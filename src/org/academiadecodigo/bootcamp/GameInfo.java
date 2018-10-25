package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

import static org.academiadecodigo.bootcamp.GameConfigs.*;
import static org.academiadecodigo.bootcamp.GameConfigs.MID_CANVAS_HEIGHT;

public class GameInfo {

    private final Rectangle canvas;
    private final Text textScore;
    private final Text textLives;
    private final Text textPress;

    public GameInfo() {
        canvas = new Rectangle(PADDING, PADDING, CANVAS_WIDTH, CANVAS_HEIGHT);
        textLives = new Text(30, 60, "Lives: " + GameConfigs.LIVES);
        textScore = new Text(30, 20, "Score: " + 0);
        textPress = new Text(MID_CANVAS_WIDTH, MID_CANVAS_HEIGHT, "Press R");
    }

    public void init() {

        canvas.draw();
        textScore.grow(20, 20);
        textScore.setColor(Color.WHITE);
        textScore.draw();
        textLives.grow(20, 20);
        textLives.setColor(Color.WHITE);
        textLives.draw();
        textPress.grow(50, 50);
        textPress.setColor(Color.RED);
    }

    public Text getTextLives() {
        return textLives;
    }

    public Text getTextPress() {
        return textPress;
    }

    public Text getTextScore() {
        return textScore;
    }
}
