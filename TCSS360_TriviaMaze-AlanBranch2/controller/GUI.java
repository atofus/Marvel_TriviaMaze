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

/**
 * The GUI class sets up the main application frame and handles the initialization of the Trivia Maze game.
 */

public class GUI {


    /**
     * Main method to start the Trivia Maze application.
     *
     * @param args Command-line arguments (not used).
     */

    private Clip backgroundMusic;

    /**
     * Main method to start the Trivia Maze application.
     *
     * @param args Command-line arguments (not used).
     */

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

    /**
     * The GUI constructor initializes the Trivia Maze game and creates an instance of the Maze class.
     */
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
