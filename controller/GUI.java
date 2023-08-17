package controller;

import java.awt.*;
import javax.swing.*;
import view.SoundPanel;
import view.TriviaMazeView;


/**
 * The GUI class sets up the main application frame and
 * handles the initialization of the Trivia Maze game.
 * @author Alan To
 * @author Jordan Williams
 * @author Aimee Tollett
 * @version Summer 2023
 */
public class GUI {

    /** The frame width. */
    private static final int MY_WIDTH = 900;
    /** The frame height length. */
    private static final int MY_HEIGHT = 900;

    /**
     * Main method to start the Trivia Maze application.
     *
     * @param theArgs Command-line arguments (not used).
     */
    public static void main(final String [] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                final TriviaMazeView frame = new TriviaMazeView();
                frame.setTitle("Trivia Maze");

                frame.setBounds(0, 0, MY_WIDTH, MY_HEIGHT);
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

}
