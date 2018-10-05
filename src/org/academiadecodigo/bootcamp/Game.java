package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Game {
    public static final int PADDING = 10;

    private Rectangle canvas = new Rectangle(PADDING, PADDING, 800, 600);

    public void init() {
        canvas.draw();
    }

    public void start() {

    }
}
