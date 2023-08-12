package extraFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Maze;


/**
 * The RoomPanel class displays the room's doors and handles door interactions.
 */
public class RoomPanel extends JPanel {

    //maybe just connect panels
    private JButton myNorthDoor;
    private JButton myWestDoor;
    private JButton mySouthDoor;
    private JButton myEastDoor;
    private ImageIcon doorIcon;
    //private Maze myMaze = new Maze(); //MAYBE NOT CONNECTED TO MAP PANEL
   // private MapPanel mapPanel = MapPanel.getMyInstance();
    private Maze myMaze = Maze.getMyInstance();


    public RoomPanel() {
       // myMaze = new Maze();
    }


    /**
     * The paintComponent class draws the box representing the room.
     */
    @Override
    public void paintComponent (Graphics g) {
//        //Graphics2D g2d = (Graphics2D) g;
//        g.setColor(Color.YELLOW);
//        g.drawRect(593, 137, 200, 200);
        super.paintComponent(g);
        drawRectangles(g);
    }

    /**
     * Draws the room's doors and buttons.
     *
     * @param g The graphics context to use for drawing.
     */
    void drawRectangles(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.YELLOW);
//        Stroke stroke = new BasicStroke(6f);
//        g2d.setStroke(stroke);
        g2d.drawRect(0, 0, 150, 150);
        createDoorButtons();
    }

    /**
     * Creates door buttons and sets up their listeners.
     */
    public void createDoorButtons() {
        doorIcon = new ImageIcon("src/images/door.png");

        //scaling image down.
        Image image = doorIcon.getImage();
        Image newing = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        doorIcon = new ImageIcon(newing);

        myNorthDoor = new JButton(doorIcon);
        myNorthDoor.setBounds(58, 0, 35, 35);
        myNorthDoor.setBackground(Color.RED);
        myWestDoor = new JButton(doorIcon);
        myWestDoor.setBounds(0, 60 , 35, 35);
        myWestDoor.setBackground(Color.RED);
        mySouthDoor = new JButton(doorIcon);
        mySouthDoor.setBounds(58, 116, 35, 35);
        mySouthDoor.setBackground(Color.RED);
        myEastDoor = new JButton(doorIcon);
        myEastDoor.setBackground(Color.RED);
        myEastDoor.setBounds(116, 60, 35, 35);

//        if (myMaze.getY() == 0) {  //&& myMaze.getDoor()) {
//            myNorthDoor.setBackground(Color.GRAY);
//            myNorthDoor.setEnabled(false);
//            System.out.println("calling");
//        }
//        if (myMaze.getX() == 0) {  //&& myMaze.getDoor()) {
//            myWestDoor.setBackground(Color.GRAY);
//            myWestDoor.setEnabled(false);
//            System.out.println("calling");
//        }
//        if (myMaze.getY() == myMaze.getMaze().length - 1) {  //&& myMaze.getDoor()) {
//            //mySouthDoor.setBackground(Color.GRAY);
//            mySouthDoor.setEnabled(false);
//            System.out.println("calling");
//        }
//        if (myMaze.getX() == myMaze.getMaze().length - 1) {  //&& myMaze.getDoor()) {
//            myEastDoor.setBackground(Color.GRAY);
//            myEastDoor.setEnabled(false);
//            System.out.println("calling");
//        }


        add(myNorthDoor);
        add(myWestDoor);
        add(mySouthDoor);
        add(myEastDoor);

        addListeners();
    }

    /**
     * Adds action listeners to door buttons to handle player movement.
     */

    public void addListeners() {
        int size = myMaze.getMaze().length; //to make sure door is disabled if we're at edge
        MapPanel mapPanel = new MapPanel();
        myNorthDoor.setEnabled(false);

        myNorthDoor.addActionListener(e -> {
            myMaze.setY(myMaze.getY() - 1);
           // mapPanel.setMapMazeY(mapPanel.getMapMazeY() - 1);
         //   mapPanel.repaintPanel();
            mySouthDoor.setEnabled(true);
            mySouthDoor.setBackground(Color.RED);
           // System.out.println(getY());
        });

        myEastDoor.addActionListener(e -> {
            myMaze.setX(myMaze.getX() + 1);
          //  mapPanel.setMapMazeX(mapPanel.getMapMazeX() + 1);
           // mapPanel.repaintPanel();
            myWestDoor.setEnabled(true);
            System.out.println(myMaze.getX());
        });

        mySouthDoor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myMaze.setY(myMaze.getY() + 1);
                mySouthDoor.setEnabled(true);
                myNorthDoor.setBackground(Color.RED);
            }
        });

//        mySouthDoor.addActionListener(e -> {
//            myMaze.setY(myMaze.getY() + 1);
//            mySouthDoor.setEnabled(false);
//            myNorthDoor.setBackground(Color.RED);
//            System.out.println(myMaze.getY());
//        });

        myWestDoor.addActionListener(e -> {
            myMaze.setX(myMaze.getX() - 1);
            //mapPanel.setMapMazeX(mapPanel.getMapMazeX() - 1);
        //    mapPanel.repaint();
            myEastDoor.setEnabled(true);
            System.out.println(myMaze.getX());
        });

    }


    //action listeners for buttons.
    //if (myWestDoor is pressed)
    //myMaze.setX(myMaze.getX() - 1);


}
