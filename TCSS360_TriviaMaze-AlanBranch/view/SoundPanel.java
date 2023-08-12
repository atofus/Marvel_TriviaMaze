package view;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPanel {

    public static Clip backgroundMusic;
    public static Clip correctAnswerSound;
    public static Clip lockSound;

    public static void playBackgroundMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            backgroundMusic = (Clip) AudioSystem.getLine(info); // Store the Clip instance
            backgroundMusic.open(audioStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);

            FloatControl gainControl = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
            //gainControl.setValue(-75.0f);

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

//    public static void adjustVolume(float volume) {
//        if (backgroundMusic != null && backgroundMusic.isOpen()) {
//            FloatControl gainControl = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
//            //gainControl.setValue(-20.0f * (float) Math.log10(volume));
//            gainControl.setValue(-20.0f * volume);
//        }
//    }

    public static void playCorrectAnswerSound() {
        playSound("CorrectAnswer.wav");
    }

    public static void playLockSound() {
        playSound("LockSound.wav");
    }

    public static void playWinSound() {
        playSound("Winner.wav");
    }

    public static void playLoseSound() {
        playSound("Loser.wav");
    }



    private static void playSound(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip soundClip = (Clip) AudioSystem.getLine(info); // Store the Clip instance
            soundClip.open(audioStream);
            soundClip.start(); // Play the sound once
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
        }
    }
}
