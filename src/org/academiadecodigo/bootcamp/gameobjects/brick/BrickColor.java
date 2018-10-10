package org.academiadecodigo.bootcamp.gameobjects.brick;


import org.academiadecodigo.simplegraphics.graphics.Color;

public enum BrickColor {

    RED(Color.RED),
    PINK(Color.PINK),
    BLUE(Color.BLUE),
    YELLOW(Color.YELLOW),
    GREEN(Color.GREEN);

    Color gfxColor;

    BrickColor(Color color) {
        this.gfxColor = color;
    }

    public Color getColor() {
        return gfxColor;
    }
}
