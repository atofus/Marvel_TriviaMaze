package view;

import extraFiles.MapPanel;
import extraFiles.RoomPanel;
import model.Maze;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//class will be used for the frame.
public class TriviaMazeView extends JFrame {

    private JMenuBar myJMenuBar;
    private JMenu myFile;
    private JMenuItem mySave;
    private JMenuItem myLoad;
    private JMenuItem myExit;
    private JMenu myHelp;
    private JMenuItem myAbout;
    private JMenuItem myInstructions;

    private JMenu myScoreMenu;

    private JMenuItem myScore;
    private JMenuItem myLeaderBoard;

    private JMenu myHintMenuItem;
    private JMenuItem myHint;

    private JMenuItem myPotion;

    private Display panel;
   // TestingDisplay panel = new TestingDisplay();

    private Maze myMaze = Maze.getMyInstance();


//    myFrame() {
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(500, 500);
//        this.setLocationRelativeTo(null);
//        this.setVisible(true);
//    }

    public TriviaMazeView() {
        //myMaze = Maze.getMyInstance();
//        MapPanel playingField = new MapPanel();
//        //playingField.setBackground(Color.LIGHT_GRAY);
//        playingField.setBounds(50, 47, 421, 396);
//        playingField.setBackground(Color.BLACK);
//        //playingField.setPreferredSize(new Dimension(421, 396));
//        playingField.setLayout(null);
//        add(getContentPane().add(playingField));
//
//
//        RoomPanel roomPanel = new RoomPanel();
//        roomPanel.setBounds(540, 137, 200, 200);
//        roomPanel.setBackground(Color.BLACK);
//       // roomPanel.setPreferredSize(new Dimension(400, 400));
//        roomPanel.setLayout(null);
//        add(getContentPane().add(roomPanel));

        panel = new Display();
        panel.setBackground(Color.BLACK);
        panel.setLayout(null);
        panel.setBounds(50, 47, 780, 785);
        //panel.setBounds(0,0, 900, 900);

        panel.setBackground(Color.BLACK);
        panel.setLayout(null);
        panel.setBounds(50, 47, 780, 785);

        this.getContentPane().setLayout(null); //to move panel bounds.



        //adding panel to frame
        add(getContentPane().add(panel));


        //adding menu bar to frame.
        this.setJMenuBar(createMenu());
        this.setVisible(true);


//        GameMapGrid playingField = new GameMapGrid();
//        JPanel playingFieldPanel = new JPanel();
//        playingFieldPanel.setBounds(50, 47, 421, 396);
//        playingFieldPanel.add(playingField);
//        frame.add(playingFieldPanel);
        // frame.add(playingField);
    }

    public void actionListeners() {

        myExit.addActionListener(e -> {
            System.exit(0);
        });

        mySave.addActionListener(e -> {
            String numGame = "saveGame1";
            String[] options = {"Game 1", "Game 2", "Game 3"};
            int choice = JOptionPane.showOptionDialog(null, "Which game would you like to save?",
                    "Save Game", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, null);

            if (choice == 0) {
                numGame = "saveGame1";
            } else if (choice == 1) {
                numGame = "saveGame2";
            } else if (choice == 2) {
                numGame = "saveGame3";
            }

            panel.serialize(numGame);


        });

        myLoad.addActionListener(e -> {

            String numGame = "saveGame1";
            String[] options = {"Game 1", "Game 2", "Game 3"};
            int choice = JOptionPane.showOptionDialog(null, "Which game would you like to load?",
                    "Load Game", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, null);

            if (choice == 0) {
                numGame = "saveGame1";
            } else if (choice == 1) {
                numGame = "saveGame2";
            } else if (choice == 2) {
                numGame = "saveGame3";
            }


            //numGame = "saveGame1";

            try {
                panel.deserialize(numGame);
            } catch (RuntimeException re) {
                JOptionPane.showMessageDialog(null, "You can't load an empty game.");
            }



            revalidate();
            repaint();




        });


        myScore.addActionListener(e -> {

            try{
                panel.getTimer().stop();
                String nameAndScore = "Your current score is " + myMaze.getScore() + " points.";
                JOptionPane.showMessageDialog(null, nameAndScore);
                panel.getTimer().start();
            } catch (NullPointerException npe) {
                String nameAndScore = "Your current score is " + myMaze.getScore() + " points.";
                JOptionPane.showMessageDialog(null, nameAndScore);
            }


        });

        myLeaderBoard.addActionListener(e -> {
            try {
                readInLeader();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });


        myHint.addActionListener(e -> {

            try {

                panel.getTimer().stop();
                panel.setHint(true);

                panel.provideHint(panel.getQuestion());

                panel.revalidate();
                panel.repaint();

                panel.getTimer().start();
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "Can't use a hint with no question.");
            }




        });

        myPotion.addActionListener(e -> {

            //TODO please don't try to add potions until you get a question
            // After one question, it gives you potions

            //TODO fix timer

            String havePotions = "You have " + myMaze.getNumPotions() + " potion(s).\nWould you like to use one and gain 5 seconds?";
            String noPotions = "You have 0 potions.";

            //System.out.println(myMaze.getNumPotions());

            try {
                panel.getTimer().stop();
                if (myMaze.getNumPotions() > 0) {
                    //panel.getTimer().stop();
                    if (JOptionPane.showConfirmDialog(null, havePotions, "Potions!", JOptionPane.YES_NO_OPTION)
                            == JOptionPane.YES_OPTION) {
                        myMaze.setNumPotions(myMaze.getNumPotions() - 1);
                        panel.addTime();
                    }
                    //panel.getTimer().start();
                } else {
                    //panel.getTimer().stop();
                    JOptionPane.showMessageDialog(null, noPotions);
                    //panel.getTimer().start();
                }
                panel.getTimer().start();
            } catch (NullPointerException nullPointer) {
                JOptionPane.showMessageDialog(null, "You have " + myMaze.getNumPotions() + " potion(s).");
            }

//            if (panel.getNumPotions() > 0) {
//                panel.getTimer().stop();
//                if (JOptionPane.showConfirmDialog(null, havePotions, "Potions!", JOptionPane.YES_NO_OPTION)
//                        == JOptionPane.YES_OPTION) {
//                    panel.setNumPotions(panel.getNumPotions() - 1);
//                    panel.addTime();
//                }
//                panel.getTimer().start();
//            } else {
//                panel.getTimer().stop();
//                JOptionPane.showMessageDialog(null, noPotions);
//                panel.getTimer().start();
//            }





        });
    }

    public void readInLeader() throws FileNotFoundException {
        int numLeaders = 1;

        //TreeMap<Integer, String> leader = new TreeMap<>(Collections.reverseOrder());

        String filename = "Leaderboard.txt";
        Scanner scanner = new Scanner(new File(filename));

        StringBuilder sb = new StringBuilder();
        //scanner.useDelimiter(" ");

        while (scanner.hasNext()) {
            Integer inScore = scanner.nextInt();
            String inName = scanner.nextLine();
            inName = inName.trim();

            if (inScore <= 9) {
                sb.append(numLeaders + ". Score: " + inScore + "   Name: " + inName + "\n");
            } else {
                sb.append(numLeaders + ". Score: " + inScore + " Name: " + inName + "\n");
            }



            numLeaders++;

            //System.out.println("Name:" + inName);

            //leader.put(inScore, inName);
        }

        try {
            panel.getTimer().stop();
            JOptionPane.showMessageDialog(null, sb.toString());
            panel.getTimer().start();
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(null, sb.toString());
        }



        scanner.close();
    }



    public JMenuBar createMenu() {
        myJMenuBar = new JMenuBar();
        myFile = new JMenu("File");
        myJMenuBar.add(myFile);

        mySave = new JMenuItem("Save");
        myLoad = new JMenuItem("Load");
        myExit = new JMenuItem("Exit");
        myFile.add(mySave);
        myFile.add(myLoad);
        myFile.add(myExit);
        myHelp = new JMenu("Help");
        myAbout = new JMenuItem("About");
        myHelp.add(myAbout);
        myInstructions = new JMenuItem("Instructions");
        myHelp.add(myInstructions);
        myJMenuBar.add(myHelp);

        myScoreMenu = new JMenu("Score");
        myScore = new JMenuItem("Score");
        myLeaderBoard = new JMenuItem("Leader Board");

        myScoreMenu.add(myScore);
        myScoreMenu.add(myLeaderBoard);

        myHintMenuItem = new JMenu("Hint");
        myHint = new JMenuItem("Hint");
        myPotion = new JMenuItem("Potions");
        myHintMenuItem.add(myHint);
        myHintMenuItem.add(myPotion);

        //myHint = new JMenu("Hint");

        myJMenuBar.add(myScoreMenu);
        //myJMenuBar.add(myHint);
        myJMenuBar.add(myHintMenuItem);

        actionListeners();

        return myJMenuBar;
    }


}
