package view;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
import model.Maze;

/**
 * Represents the main graphical user interface for the Trivia Maze game.
 * This class sets up the frame, menu bar, and various UI components for the game.
 * @author Alan To
 * @author Jordan Williams
 * @author Aimee Tollett
 * @version Summer 2023
 */
public class TriviaMazeView extends JFrame {

    /** JMenu item to save that goes under JMenu file. */
    private static JMenuItem mySave;

    /** JMenu item to load that goes under JMenu file. */
    private static JMenuItem myLoad;

    /** The JMenuBar in the frame. */
    private JMenuBar myJMenuBar;

    /** JMenu option that goes under JMenuBar. */
    private JMenu myFile;


    /** JMenu item that loads a new game under JMenu file. */
    private JMenuItem myNewGame;

    /** JMenu item that exits the game under JMenu file. */
    private JMenuItem myExit;
    /** JMenu option to help the user that goes under JMenuBar. */
    private JMenu myHelp;
    /** JMenu item that tells about the creators of the game. */
    private JMenuItem myAbout;
    /** JMenu item that tells the instructions of the game under JMenu Help. */
    private JMenuItem myInstructions;
    /** JMenu option to show the user score. */
    private JMenu myScoreMenu;
    /** Used to show what the user score is. */
    private JMenuItem myScore;
    /** Used to show the leaderboard of previous scores. */
    private JMenuItem myLeaderBoard;
    /** JMenu hint option to help user answer a question. */
    private JMenu myHintMenuItem;
    /** JMenu item to delete an option. */
    private JMenuItem myHint;
    /** JMenu item to increase the time on each question. */
    private JMenuItem myPotion;
    /** The panel that's being put into this frame. */
    private Display myPanel;
    /** The maze object that's being used. */
    private Maze myMaze;

    /**
     * Constructors that sets everything and puts everything.
     */
    public TriviaMazeView() {
        myPanel = new Display();
        myPanel.setBackground(Color.BLACK);
        myPanel.setLayout(null);
        myPanel.setBounds(50, 47, 780, 785);

        myMaze = Maze.getMyInstance();

        myPanel.setBackground(Color.BLACK);
        myPanel.setLayout(null);
        myPanel.setBounds(50, 47, 780, 785);

        this.getContentPane().setLayout(null); //to move panel bounds.



        //adding panel to frame
        add(getContentPane().add(myPanel));


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

    /**
     * Used to turn off save JMenuItem.
     */
    public static void turnOffSave() {
        mySave.setVisible(false);
    }

    /**
     * Sets up the action listeners for the various menu items in the Trivia Maze game.
     * Each menu item's action is defined within this method.
     */
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
                myPanel.serialize(filename);
            } catch (final RuntimeException re) {
                JOptionPane.showMessageDialog(null, "Make sure you save this game somewhere.");
            }
        });

        myLoad.addActionListener(e -> {
            try {
                SoundPanel.stopMusic();
            } catch (final NullPointerException npe) {

            }
            mySave.setVisible(true);

            String filename = "";
            final String[] options = {"Game 1", "Game 2", "Game 3"};
            final int choice = JOptionPane.showOptionDialog(null,
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
                myPanel.deserialize(filename);
            } catch (final RuntimeException re) {
                JOptionPane.showMessageDialog(null, "You can't load an empty game.");
            }
        });



        myNewGame.addActionListener(e -> {

            //TODO don't forget
            //myMaze = Maze.getMyInstance();
            //at the beginning of charSelect

            try {
                SoundPanel.stopMusic();
            } catch (final NullPointerException npe) {
                System.out.println("This is null: " + npe);
            }

            if (JOptionPane.showConfirmDialog(null, "Would you like to start a new game?",
                    "New Game!", JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION) {

                mySave.setVisible(true);

                myPanel.setButtonInvis(false);

                myPanel.characterSelect();
                myPanel.getDiffLevel();

                final int diffLevel = myMaze.getDiffLevel();


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
                    myPanel.deserialize("backup/blackwidow.ser");
                } else if (myMaze.getCharName().equals("Captain America")) {
                    //System.out.println("Cap America");
                    myPanel.deserialize("backup/capamerica.ser");
                } else if (myMaze.getCharName().equals("Loki")) {
                    //System.out.println("Loki");
                    myPanel.deserialize("backup/loki.ser");
                } else if (myMaze.getCharName().equals("Spiderman")) {
                    //System.out.println("Spiderman");
                    myPanel.deserialize("backup/spiderman.ser");
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
                    myPanel.randomLocks();
                }

                //System.out.println(myMaze.getDiffLevel());

                getContentPane().revalidate();
                getContentPane().repaint();




            }


        });


        myScore.addActionListener(e -> {

            try {
                myPanel.getTimer().stop();
                String nameAndScore = "Your current score is " + myMaze.getScore()
                        + " points.";
                JOptionPane.showMessageDialog(null, nameAndScore);
                myPanel.getTimer().start();
            } catch (NullPointerException npe) {
                final String nameAndScore = "Your current score is "
                        + myMaze.getScore() + " points.";
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
                myPanel.getTimer().stop();
                myPanel.setHint(true);

                myPanel.provideHint(myPanel.getQuestion());

                myPanel.revalidate();
                myPanel.repaint();
                myPanel.getTimer().start();
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
                myPanel.getTimer().stop();
                if (myMaze.getPotions() > 0) {

                    if (JOptionPane.showConfirmDialog(null, havePotions, "Potions!", JOptionPane.YES_NO_OPTION)
                            == JOptionPane.YES_OPTION) {
                        myMaze.setPotions(myMaze.getPotions() - 1);
                        myPanel.addTime();
                    }

                } else {

                    JOptionPane.showMessageDialog(null, noPotions);

                }
                myPanel.getTimer().start();
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
                "Created by: \n"
                        + "-  Alan To \n"
                        + "-  Aimee Tollett\n"
                        + "-  Jordan Williams\n"
                        + "Java Version: JavaSE-17 \n"
                        + "Created in: August, 2023", "About", JOptionPane.INFORMATION_MESSAGE));
    }

    /**
     * Reads the leaderboard data from a file and displays it to the user.
     *
     * @throws FileNotFoundException If the leaderboard file is not found.
     */
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
            myPanel.getTimer().stop();
            JOptionPane.showMessageDialog(null, sb.toString());
            myPanel.getTimer().start();
        } catch (final NullPointerException npe) {
            JOptionPane.showMessageDialog(null, sb.toString());
        }



        scanner.close();
    }


    /**
     * Creates and configures the menu bar for the Trivia Maze game.
     *
     * @return The configured JMenuBar instance.
     */
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
