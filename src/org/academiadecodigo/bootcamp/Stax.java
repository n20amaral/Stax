package org.academiadecodigo.bootcamp;

public class Stax {

    public static final int MAX_COLS = 5;

    public static void main(String[] args) {

        Game stax = new Game(MAX_COLS);

        stax.init();
        stax.start();
    }
}
