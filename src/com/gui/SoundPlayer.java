package com.gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundPlayer {
    private Clip clip; // Store the Clip to control it

    public void playCheerSound() {
        try {
            File soundFile = new File("src/resources/cheer.wav"); // Replace with your cheer sound file path
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the sound indefinitely
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopCheerSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop(); // Stop the sound
        }
    }
}
