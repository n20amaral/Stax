package org.academiadecodigo.bootcamp;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class Music {

    private boolean loop;
    private final String audioPath;

    public Music(String audioPath, boolean loop) {
        this.audioPath = audioPath;
        this.loop = loop;
    }

    public void startMusic() {
        String pathStr = audioPath;
        URL soundURL;
        AudioInputStream audioInputStream = null;
        try {
            soundURL = Stax.class.getResource(pathStr);
            if (soundURL == null) {
                pathStr = pathStr.substring(1);
                File file = new File(pathStr);
                soundURL = file.toURI().toURL();
            }
            audioInputStream = AudioSystem.getAudioInputStream(soundURL);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            if (loop) {
                clip.loop(clip.LOOP_CONTINUOUSLY);
            }

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

