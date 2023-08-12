package controller;

import model.Maze;

import view.SoundPanel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import view.TriviaMazeView;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class GUI {

    private Clip backgroundMusic;

    public static void main (String [] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                TriviaMazeView frame = new TriviaMazeView();
                frame.setTitle("Trivia Maze");

                frame.setBounds(0, 0, 900, 900);
                // frame.setBackground(Color.GRAY);


                frame.setResizable(false);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().setLayout(null);
                frame.setLocationRelativeTo(null);
                frame.getContentPane().setBackground(Color.BLACK);

                SoundPanel.playBackgroundMusic("Avengers Suite (Theme).wav");
            }
        });
    }

    public static void playBackgroundMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip backgroundMusic = (Clip) AudioSystem.getLine(info);
            backgroundMusic.open(audioStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }


    public GUI() {
        Maze maze = Maze.getMyInstance();
    }

//    public void paintMaze(Graphics g) {
//        int boxWidth = 30;
//        int boxHeight = 30;
//
//        for (int currentX = 0; currentX < )
//    }

//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//
//    }
}
