package org.academiadecodigo.bootcamp.gameobjects.brick;

import org.academiadecodigo.bootcamp.Resources;
import org.academiadecodigo.simplegraphics.graphics.Color;

public enum BrickColor {

    RED(Color.RED, Resources.RED),
    PINK(Color.PINK, Resources.PINK),
    BLUE(Color.BLUE, Resources.BLUE),
    YELLOW(Color.YELLOW, Resources.YELLOW),
    GREEN(Color.GREEN, Resources.GREEN),
    CYAN(Color.CYAN, Resources.CYAN),
    ORANGE(Color.ORANGE, Resources.ORANGE);

    final Color gfxColor;
    final String audioPath;

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
