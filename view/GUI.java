package view;

import model.Maze;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GUI {

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
            }
        });
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
