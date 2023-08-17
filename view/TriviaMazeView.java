package view;

import controller.GUI;
import model.Maze;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    private static JMenuItem mySave;
    private static JMenuItem myLoad;

    private JMenuItem myNewGame;
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
    private Maze myMaze;


    public TriviaMazeView() {
        panel = new Display();
        panel.setBackground(Color.BLACK);
        panel.setLayout(null);
        panel.setBounds(50, 47, 780, 785);

        myMaze = Maze.getMyInstance();

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

    public static void turnOffSave() {
        mySave.setVisible(false);
    }

    public void actionListeners() {
        mySave.addActionListener(e -> {

            String filename = "";
            String[] options = {"Game 1" , "Game 2", "Game 3"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Where would you like to save?",  "Save Game",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, null);

            if (choice == 0) {
                filename = "saveGame1.ser";
            } else if (choice == 1) {
                filename = "saveGame2.ser";
            } else if (choice == 2) {
                filename = "saveGame3.ser";
            }

            try {
                panel.serialize(filename);
            } catch (RuntimeException re) {
                JOptionPane.showMessageDialog(null, "Make sure you save this game somewhere.");
            }
        });

        myLoad.addActionListener(e -> {
            try {
                SoundPanel.stopMusic();
            } catch (NullPointerException npe) {

            }
            mySave.setVisible(true);

            String filename = "";
            String[] options = {"Game 1", "Game 2", "Game 3"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Which game would you like to load?", "Load Game",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, null);

            if (choice == 0) {
                filename = "saveGame1.ser";
            } else if (choice == 1) {
                filename = "saveGame2.ser";
            } else if (choice == 2) {
                filename = "saveGame3.ser";
            }

            try {
                panel.deserialize(filename);
            } catch (RuntimeException re) {
                JOptionPane.showMessageDialog(null, "You can't load an empty game.");
            }
        });



        myNewGame.addActionListener(e -> {

            //TODO don't forget
            //myMaze = Maze.getMyInstance();
            //at the beginning of charSelect

            try {
                SoundPanel.stopMusic();
            } catch (NullPointerException npe) {
                System.out.println("This is null: " + npe);
            }

            if (JOptionPane.showConfirmDialog(null, "Would you like to start a new game?",
                    "New Game!", JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION) {

                mySave.setVisible(true);

                panel.setButtonInvis(false);

                panel.characterSelect();
                panel.getDiffLevel();

                int diffLevel = myMaze.getDiffLevel();


                //getContentPane().revalidate();
                //getContentPane().repaint();

                //System.out.println(myMaze.getCharName());

                //panel.removeLocks();

                //myMaze.setX(0);
                //myMaze.setY(0);
                //panel.characterSelect();
                //panel.getDiffLevel();

                if (myMaze.getCharName().equals("Black Widow")) {
                    //System.out.println("Black Widow");
                    panel.deserialize("backup/blackwidow.ser");
                } else if (myMaze.getCharName().equals("Captain America")) {
                    //System.out.println("Cap America");
                    panel.deserialize("backup/capamerica.ser");
                } else if (myMaze.getCharName().equals("Loki")) {
                    //System.out.println("Loki");
                    panel.deserialize("backup/loki.ser");
                } else if (myMaze.getCharName().equals("Spiderman")) {
                    //System.out.println("Spiderman");
                    panel.deserialize("backup/spiderman.ser");
                }

                if (diffLevel == 1) {
                    myMaze.setDiffLevel(1);
                } else if (diffLevel == 2) {
                    myMaze.setDiffLevel(2);
                } else if (diffLevel == 3) {
                    myMaze.setDiffLevel(3);
                }

                //panel.removeLocks();

                if (myMaze.getDiffLevel() == 2 || myMaze.getDiffLevel() == 3) {
                    panel.randomLocks();
                }

                //System.out.println(myMaze.getDiffLevel());

                getContentPane().revalidate();
                getContentPane().repaint();




            }


        });


        myScore.addActionListener(e -> {

            try {
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

            String havePotions = "You have " + myMaze.getPotions() + " potion(s).\nWould you like to use one and gain 5 seconds?";
            String noPotions = "You have 0 potions.";

            //System.out.println(myMaze.getNumPotions());

            try {
                panel.getTimer().stop();
                if (myMaze.getPotions() > 0) {

                    if (JOptionPane.showConfirmDialog(null, havePotions, "Potions!", JOptionPane.YES_NO_OPTION)
                            == JOptionPane.YES_OPTION) {
                        myMaze.setPotions(myMaze.getPotions() - 1);
                        panel.addTime();
                    }

                } else {

                    JOptionPane.showMessageDialog(null, noPotions);

                }
                panel.getTimer().start();
            } catch (NullPointerException nullPointer) {
                JOptionPane.showMessageDialog(null, "You have " + myMaze.getPotions() + " potion(s).");
            }






        });

        myExit.addActionListener(e -> {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave?",
                    "Exit", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        myInstructions.addActionListener(e -> JOptionPane.showMessageDialog(null,
                """
                        If the user is unable to answer a question, that door is then locked permanently. \s
                        If the user is unable to make it from the entrance to the exit (due to locked doors),\s
                        the game is lost.""", "Rules", JOptionPane.INFORMATION_MESSAGE));

        myAbout.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "Created by: \n" +
                        "-  Alan To \n" +
                        "-  Aimee Tollett\n" +
                        "-  Jordan Williams\n" +
                        "Java Version: JavaSE-17 \n" +
                        "Created in: August, 2023", "About", JOptionPane.INFORMATION_MESSAGE));
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
        myNewGame = new JMenuItem("New Game");
        myFile.add(myNewGame);
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
        myPotion = new JMenuItem("Potion");
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
