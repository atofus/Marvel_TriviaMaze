package view;

import extraFiles.MapPanel;
import extraFiles.RoomPanel;

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

    private JMenu mySoundMenu;
    private JMenuItem myAdjustVolume;
    

    private Display panel;
   // TestingDisplay panel = new TestingDisplay();


//    myFrame() {
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(500, 500);
//        this.setLocationRelativeTo(null);
//        this.setVisible(true);
//    }

    public TriviaMazeView() {
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
        myScore.addActionListener(e -> {
            String nameAndScore = "Your current score is " + panel.getScoreVal() + " points.";
            JOptionPane.showMessageDialog(null, nameAndScore);
        });

        myLeaderBoard.addActionListener(e -> {
            try {
                readInLeader();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        myHint.addActionListener(e -> {

            panel.setHint(true);
            repaint();
            //panel.provideHint(0);
        });

        myAdjustVolume.addActionListener(e -> adjustVolume());

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

        JOptionPane.showMessageDialog(null, sb.toString());

        scanner.close();
    }

    private void adjustVolume() {
        String volumeStr = JOptionPane.showInputDialog(this, "Enter volume level (0 to 100):");

        try {
            int volume = Integer.parseInt(volumeStr);

            if (volume < 0 || volume > 100) {
                JOptionPane.showMessageDialog(this, "Volume level must be between 0 and 100.");
                return;
            }

            float gain = (float) (volume / 100.0);
            if (panel.backgroundMusic != null && panel.backgroundMusic.isOpen()) {
                FloatControl gainControl = (FloatControl) panel.backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(20f * (float) Math.log10(gain));
            }
        } catch (NumberFormatException | NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number between 0 and 100.");
        }
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
        myHintMenuItem.add(myHint);

        //myHint = new JMenu("Hint");

        myJMenuBar.add(myScoreMenu);
        //myJMenuBar.add(myHint);
        myJMenuBar.add(myHintMenuItem);

        mySoundMenu = new JMenu("Sound");
        myAdjustVolume = new JMenuItem("Adjust Volume");
        mySoundMenu.add(myAdjustVolume);
        myJMenuBar.add(mySoundMenu);

        actionListeners();

        return myJMenuBar;
    }


}
