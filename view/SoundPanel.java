package view;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * SoundPanel manages and handles all the music and sound effects for the program.
 * @author Alan To
 * @author Aimee Tollett
 * @author Jordan Williams
 * @version Summer 2023
 */
public class SoundPanel {

    /**
     * Sound clips.
     */
    public static Clip backgroundMusic;
    public static Clip correctAnswerSound;
    public static Clip lockSound;

    private static Clip soundClip;


    /**
     * Plays the background music from the specified file path.
     *
     * @param filePath The file path of the background music.
     */
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

    public static void adjustVolume(float volume) {
        if (backgroundMusic != null && backgroundMusic.isOpen()) {
            FloatControl gainControl = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
            //gainControl.setValue(-20.0f * (float) Math.log10(volume));
            gainControl.setValue(-20.0f * volume);
        }
    }

    /**
     * Plays the sound for a correct answer.
     */
    public static void playCorrectAnswerSound() {
        playSound("CorrectAnswer.wav");
    }

    /**
     * Plays the sound for locking/unlocking doors.
     */
    public static void playLockSound() {
        playSound("LockSound.wav");
    }

    /**
     * Plays the sound for winning the game.
     */
    public static void playWinSound() {
        playSound("Winner.wav");
    }

    /**
     * Plays the sound for losing the game.
     */
    public static void playLoseSound() {
        playSound("Loser.wav");
    }

    /**
     * Plays correct/incorrect/winning/losing sound.
     * @param filePath
     */
    private static void playSound(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            soundClip = (Clip) AudioSystem.getLine(info); // Store the Clip instance
            soundClip.open(audioStream);
            soundClip.start(); // Play the sound once
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops correct/incorrect/win/lose music.
     */
    public static void stopMusic() {
        soundClip.stop();
    }

    /**
     * Stops the background music if it's currently playing.
     */
    public static void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
        }
    }

}
