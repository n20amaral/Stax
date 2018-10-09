package org.academiadecodigo.bootcamp.gameobjects;

import org.academiadecodigo.bootcamp.gameobjects.brick.BrickColor;
import org.academiadecodigo.simplegraphics.graphics.Color;

public class SimpleGfxBrickColor {

    private static final Color colors[] = {
            Color.RED,
            Color.PINK,
            Color.BLUE,
            Color.YELLOW,
            Color.GREEN
    };

    public static Color getColor(BrickColor color) {

        Color potatosBricks = null;

        switch (color) {
            case RED:
                potatosBricks = colors[0];
                break;
            case PINK:
                potatosBricks = colors[1];
                break;
            case BLUE:
                potatosBricks = colors[2];
                break;
            case YELLOW:
                potatosBricks = colors[3];
                break;
            case GREEN:
                potatosBricks = colors[4];
            default:
                System.out.println("something went terribly wrong...");
        }

        return potatosBricks;

    }

}
