package view;

import extraFiles.MapPanel;
import extraFiles.RoomPanel;

import javax.swing.*;
import java.awt.*;

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

        Display panel = new Display();
        panel.setBackground(Color.BLACK);
        panel.setLayout(null);
        panel.setBounds(50, 47, 780, 785);

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

        return myJMenuBar;
    }


}
