package org.academiadecodigo.bootcamp.gameobjects.brick;


import org.academiadecodigo.simplegraphics.graphics.Color;

public enum BrickColor {

    RED(Color.RED, "/resources/red.wav"),
    PINK(Color.PINK, "/resources/pink.wav"),
    BLUE(Color.BLUE, "/resources/blue.wav"),
    YELLOW(Color.YELLOW, "/resources/yellow.wav"),
    GREEN(Color.GREEN, "/resources/green.wav"),
    CYAN(Color.CYAN, "/resources/cyan.wav"),
    ORANGE(Color.ORANGE, "/resources/orange.wav");


    Color gfxColor;
    String audioPath;

    BrickColor(Color color, String audioPath) {
        this.gfxColor = color;
        this.audioPath = audioPath;
    }

    public Color getColor() {
        return gfxColor;
    }

    public String getAudioPath() {
        return audioPath;
    }
}
