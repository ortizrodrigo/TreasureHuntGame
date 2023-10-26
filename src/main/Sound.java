package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] url = new URL[30];
    public static final int BLUE_BOY_ADVENTURE = 0;
    public static final int COIN = 1;
    public static final int POWER_UP = 2;
    public static final int UNLOCK = 3;
    public static final int FANFARE = 4;

    public Sound() {
        url[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        url[1] = getClass().getResource("/sound/coin.wav");
        url[2] = getClass().getResource("/sound/powerup.wav");
        url[3] = getClass().getResource("/sound/unlock.wav");
        url[4] = getClass().getResource("/sound/fanfare.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(url[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

}
