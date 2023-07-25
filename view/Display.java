package view;

import model.Direction;
import model.Maze;
import model.Player;
import model.Room;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {

    //maybe just connect panels
    private JButton myNorthDoor;
    private JButton myWestDoor;
    private JButton mySouthDoor;
    private JButton myEastDoor;
    private Maze myMaze = Maze.getMyInstance();
   // private Maze myMaze = new Maze();
    private JButton optionA;
    private JButton optionB;
    private JButton optionC;
    private JButton optionD;
    private ImageIcon doorIcon;
 //   private Player myPlayer = new Player();
    private ImageIcon playerIcon;
    private JTextField textField;
    private JMenu myFile;
    private JMenuItem mySave;
    private JMenuItem myLoad;
    private JMenuItem myExit;
    private JMenu myHelp;
    private JMenuItem myAbout;
    private JMenuItem myInstructions;
    private JMenuBar myJMenuBar;



    public Display() {
        // myMaze = new Maze();
//        createDoorButtons();
//        addListeners();
//        myNorthDoor = new JButton("N");
//        mySouthDoor = new JButton("S");
//        myWestDoor = new JButton("W");
//        myEastDoor = new JButton("E");
        createMenu();
    }

    public void createMenu() {
        myJMenuBar = new JMenuBar();
        myFile = new JMenu("File");
        myJMenuBar.add(myFile);
        mySave = new JMenuItem("Save");
        myLoad = new JMenuItem("Load");

    }

//    @Override
//    public void paint (Graphics g) {
//        //Room [][] mazeMap = myMaze.getMaze();
//       // super.paint(g);
//        drawMap(g);
//
//     //   g.setColor(Color.LIGHT_GRAY);
//        drawRectangles(g);
//        createDoorButtons();
//        addListeners();
//    }


    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Room [][] mazeMap = myMaze.getMaze();
        Graphics2D g2d = (Graphics2D) g;
        playerIcon = new ImageIcon("images/Comic_Books.png");
        //draw border for playing field
        g2d.setColor(Color.YELLOW); //sets the border/line to yellow

        //scales the player icon down a little bit.
        Image image = playerIcon.getImage();
        Image newing = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        playerIcon = new ImageIcon(newing);

        int boxWidth = 70;
        int boxHeight = 70;
        for (int currentX = 0; currentX < mazeMap.length * boxWidth; currentX += boxWidth) {
            for (int currentY = 0; currentY < mazeMap[0].length * boxHeight; currentY += boxHeight) {
                g2d.drawRect(currentX, currentY, boxWidth, boxHeight);

                 if (currentX == myMaze.getX() * boxWidth && currentY == myMaze.getY() * boxHeight) {
              //  if (currentX == theX * boxWidth && currentY == theY * boxHeight) {
                    g2d.drawImage(playerIcon.getImage(), currentX + boxWidth/2 - playerIcon.getIconWidth()/2,
                            currentY + boxHeight/2 - playerIcon.getIconHeight()/2, null);
                }

                //will receive the player room and we'll check every door around the room. If door is foreverlocked, we draw pic to let player know they can't go thru
//                for (int i = 0; i < 4; i++) {
//                    if (myMaze.getRoom().getDoor(i).getForeverLocked()) {
//                        g.drawImage(playerIcon.getImage(), currentX + boxWidth/2 - playerIcon.getIconWidth()/2,
//                                currentY + boxHeight/2 - playerIcon.getIconHeight()/2, null);
//                    }
//                }

            }
        }

        g.setColor(Color.LIGHT_GRAY);
        drawRectangle(g);
    }

    void drawRectangle(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.YELLOW);
//        Stroke stroke = new BasicStroke(6f);
//        g2d.setStroke(stroke);
        g2d.drawRect(488, 104, 150, 150);
       // createDoorButtons();
        myMaze.roomSetup();
        createNorthDoor();
        createWestDoor();
        createSouthDoor();
        createEastDoor();
        addListeners();
        drawQuestionRectangle(g);
    }

    void drawQuestionRectangle(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.CYAN);
        g2d.setStroke(new BasicStroke(6));
        g2d.drawRect(80, 410, 636, 335);
    }

    public void createNorthDoor() {
        doorIcon = new ImageIcon("images/door.png");

        //scaling image down.
        Image image = doorIcon.getImage();
        Image newing = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        doorIcon = new ImageIcon(newing);
        myNorthDoor = new JButton(doorIcon);
        myNorthDoor.setBackground(Color.RED);
        myNorthDoor.setBounds(545, 105, 35, 35);
        if (!myMaze.display(Direction.NORTH)) {  //&& myMaze.getDoor()) {
            myNorthDoor.setBackground(Color.GRAY);
            myNorthDoor.setEnabled(false);
           // System.out.println("calling");
        } else {
            //System.out.println("North door open");
            myNorthDoor.setEnabled(true);
        }
        add(myNorthDoor);
    }

    public void createWestDoor() {
        doorIcon = new ImageIcon("images/door.png");
        //scaling image down.
        Image image = doorIcon.getImage();
        Image newing = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        doorIcon = new ImageIcon(newing);
        myWestDoor = new JButton(doorIcon);
        myWestDoor.setBounds(489, 160, 35, 35);
        myWestDoor.setBackground(Color.RED);
        if (!myMaze.display(Direction.WEST)) {  //&& myMaze.getDoor()) {
            myWestDoor.setBackground(Color.GRAY);
            myWestDoor.setEnabled(false);
        } else {
        //    System.out.println("West door open");
            myWestDoor.setEnabled(true);

        }
        add(myWestDoor);
    }

    public void createSouthDoor() {
        doorIcon = new ImageIcon("images/door.png");

        //scaling image down.
        Image image = doorIcon.getImage();
        Image newing = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        doorIcon = new ImageIcon(newing);

        mySouthDoor = new JButton(doorIcon);
        mySouthDoor.setBackground(Color.RED);
        mySouthDoor.setBounds(545, 219, 35, 35);
        if (!myMaze.display(Direction.SOUTH)) {  //&& myMaze.getDoor()) {
            mySouthDoor.setBackground(Color.GRAY);
            mySouthDoor.setEnabled(false);
        } else {
            mySouthDoor.setEnabled(true);
        }
        add(mySouthDoor);
    }

    public void createEastDoor() {
        doorIcon = new ImageIcon("images/door.png");

        //scaling image down.
        Image image = doorIcon.getImage();
        Image newing = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        doorIcon = new ImageIcon(newing);
        myEastDoor = new JButton(doorIcon);
        myEastDoor.setBackground(Color.RED);
        myEastDoor.setBounds(605, 160, 35, 35);
        if (!myMaze.display(Direction.EAST)) {  //if there's a display of a door east.
            myEastDoor.setBackground(Color.GRAY);
            myEastDoor.setEnabled(false);
        } else {
            myEastDoor.setEnabled(true);
        }
        add(myEastDoor);
    }

    //action listeners for buttons.
    //if (myWestDoor is pressed)
    //myMaze.setX(myMaze.getX() - 1);
//    public void createDoorButtons() {
//        doorIcon = new ImageIcon("src/images/door.png");
//
//        //scaling image down.
//        Image image = doorIcon.getImage();
//        Image newing = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
//        doorIcon = new ImageIcon(newing);
//
//        myNorthDoor = new JButton(doorIcon);
//        myNorthDoor.setBackground(Color.RED);
//        myNorthDoor.setBounds(545, 105, 35, 35);
//        myWestDoor = new JButton(doorIcon);
//        myWestDoor.setBounds(489, 160, 35, 35);
//        myWestDoor.setBackground(Color.RED);
//        mySouthDoor = new JButton(doorIcon);
//        mySouthDoor.setBackground(Color.RED);
//        mySouthDoor.setBounds(545, 219, 35, 35);
//        myEastDoor = new JButton(doorIcon);
//        myEastDoor.setBackground(Color.RED);
//        myEastDoor.setBounds(605, 160, 35, 35);
//
//        add(myNorthDoor);
//        add(myWestDoor);
//        add(mySouthDoor);
//        add(myEastDoor);
//        addListeners();
//    }

    public void addListeners() {
        int size = myMaze.getMaze().length - 1; //to make sure door is disabled if we're at edge
       // super.paint(g);
        //myMaze.display();
        Room [][] myMazeRoom = myMaze.getMaze();
        myNorthDoor.addActionListener(e -> {
//            if (!myMazeRoom[myMaze.getY()][myMaze.getX()].getDoor(Room.NORTH_INDEX).getLock()) { //if the door isn't locked we could just move the player through it.
//                myMaze.setY(myMaze.getY() - 1);
//            } else {
//                boolean guess = questionGuess();
//                if (guess) {
//                    System.out.println("Cool they got it right");
//                    myMazeRoom[myMaze.getY()][myMaze.getX()].getDoor(Room.NORTH_INDEX).setForeverLocked(false);
//                    myMazeRoom[myMaze.getY()][myMaze.getX()].getDoor(Room.NORTH_INDEX).unlock();
//                    myMaze.setY(myMaze.getY() - 1);
//                } else {
//                    myMazeRoom[myMaze.getY()][myMaze.getX()].getDoor(Room.NORTH_INDEX).setForeverLocked(true);
//                }
//            }
            myMaze.setY(myMaze.getY() - 1);
            removeAll();
            revalidate();
            repaint();
            System.out.println(myMaze.getY());
               // mySouthDoor.setEnabled(true);
        });

        myEastDoor.addActionListener(e -> {
            myMaze.setX(myMaze.getX() + 1);
            removeAll();
            revalidate();
            repaint();
            //myWestDoor.setEnabled(true);
            System.out.println(myMaze.getX());
        });

        mySouthDoor.addActionListener(e -> {
            myMaze.setY(myMaze.getY() + 1);
            removeAll();
            revalidate();
            repaint();
            //myNorthDoor.setEnabled(true);
            System.out.println(myMaze.getY());
        });

        myWestDoor.addActionListener(e -> {
            myMaze.setX(myMaze.getX() - 1);
            removeAll();
            revalidate();
            repaint();
          // myEastDoor.setEnabled(true);
            System.out.println(myMaze.getX());
        });

    }

    public boolean questionGuess() { //method should show the questions
        optionA = new JButton("A");
        optionB = new JButton("B");
        optionC = new JButton("C");
        optionD = new JButton("D");


        optionA.addActionListener(e -> {

        });


        return true;
    }

}
