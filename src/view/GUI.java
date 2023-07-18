package view;

import model.Maze;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GUI extends JFrame implements PropertyChangeListener {

    private JMenu myFile;
    private JMenuItem mySave;
    private JMenuItem myLoad;
    private JMenuItem myExit;
    private JMenu myHelp;
    private JMenuItem myAbout;
    private JMenuItem myInstructions;


//    myFrame() {
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(500, 500);
//        this.setLocationRelativeTo(null);
//        this.setVisible(true);
//    }

    public static void main (String [] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Trivia Maze");



        frame.setBounds(0, 0, 900, 900);
        frame.setBackground(Color.GRAY);


        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(Color.BLACK);

        MapPanel playingField = new MapPanel();
        //playingField.setBackground(Color.LIGHT_GRAY);
        playingField.setBounds(50, 47, 421, 396);
        frame.getContentPane().add(playingField);

        RoomPanel roomPanel = new RoomPanel();
        //roomPanel.setBounds(100, 100, 400, 400);
        frame.getContentPane().add(roomPanel);
      //  frame.getContentPane().add(new DoorPickingPanel());

//        GameMapGrid playingField = new GameMapGrid();
//        JPanel playingFieldPanel = new JPanel();
//        playingFieldPanel.setBounds(50, 47, 421, 396);
//        playingFieldPanel.add(playingField);
//        frame.add(playingFieldPanel);
        // frame.add(playingField);
    }

    public void setFrame() {

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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
