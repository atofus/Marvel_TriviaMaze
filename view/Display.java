package view;

import model.*;

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

    }

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
        myMaze.roomSetup(); //we set up every room in the maze.
        createNorthDoor(); //create north door
        createWestDoor(); //create west door
        createSouthDoor(); //create south door
        createEastDoor(); //create east door
        addListeners(); //method for button listener.
        drawQuestionRectangle(g); //drawing rectangle where we put our question in.
    }

    void drawQuestionRectangle(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.CYAN);
        g2d.setStroke(new BasicStroke(6));
        g2d.drawRect(80, 410, 636, 335);
    }

    public void createQuestionLayout() {
        textField = new JTextField();
        textField.setBounds(4,4,4,4);
//        Question question = new Question("What is Spiderman real name", "Frank Clark",
//                "Stevie Wonder", "Steve Rogers", "Peter Parker", "Peter Parker");


    }

    public void createNorthDoor() {
        doorIcon = new ImageIcon("src/images/door.png");

        //scaling image down.
        Image image = doorIcon.getImage();
        Image newing = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        doorIcon = new ImageIcon(newing);
        myNorthDoor = new JButton(doorIcon);
        myNorthDoor.setBackground(Color.RED);
        myNorthDoor.setOpaque(true);
        myNorthDoor.setBounds(545, 105, 35, 35);

        if (!myMaze.display(Direction.NORTH)) {  //&& myMaze.getDoor()) {
            myNorthDoor.setBackground(Color.GRAY);
            //myNorthDoor.setOpaque(true);
            myNorthDoor.setEnabled(false);
            // System.out.println("calling");
        } else {
            //System.out.println("North door open");
            myNorthDoor.setEnabled(true);
            myNorthDoor.setBorderPainted(false);
        }
        add(myNorthDoor);
    }

    public void createWestDoor() {
        doorIcon = new ImageIcon("src/images/door.png");
        //scaling image down.
        Image image = doorIcon.getImage();
        Image newing = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        doorIcon = new ImageIcon(newing);
        myWestDoor = new JButton(doorIcon);
        myWestDoor.setBounds(489, 160, 35, 35);
        myWestDoor.setBackground(Color.RED);
        myWestDoor.setOpaque(true);

        if (!myMaze.display(Direction.WEST) || myMaze.getCurrentRoom().getDoor(Room.WEST_INDEX).getForeverLocked()) {
            myWestDoor.setBackground(Color.GRAY);
            myWestDoor.setOpaque(true);
            myWestDoor.setEnabled(false);
        } else {
            //    System.out.println("West door open");
            myWestDoor.setEnabled(true);
            myWestDoor.setBorderPainted(false);
        }
        add(myWestDoor);
    }

    public void createSouthDoor() {
        doorIcon = new ImageIcon("src/images/door.png");

        //scaling image down.
        Image image = doorIcon.getImage();
        Image newing = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        doorIcon = new ImageIcon(newing);


        mySouthDoor = new JButton(doorIcon);
        mySouthDoor.setBackground(Color.RED);
        mySouthDoor.setOpaque(true);

        mySouthDoor.setBounds(545, 219, 35, 35);
        if (!myMaze.display(Direction.SOUTH) || myMaze.getCurrentRoom().getDoor(Room.SOUTH_INDEX).getForeverLocked()) {  //&& myMaze.getDoor()) {
            mySouthDoor.setBackground(Color.GRAY);
            mySouthDoor.setOpaque(true);
            mySouthDoor.setEnabled(false);
        } else {
            mySouthDoor.setEnabled(true);
            mySouthDoor.setBorderPainted(false);
        }
        add(mySouthDoor);
    }

    public void createEastDoor() {
        doorIcon = new ImageIcon("src/images/door.png");

        //scaling image down.
        Image image = doorIcon.getImage();
        Image newing = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        doorIcon = new ImageIcon(newing);
        myEastDoor = new JButton(doorIcon);
        myEastDoor.setBackground(Color.RED);
        myEastDoor.setOpaque(true);

        myEastDoor.setBounds(601, 160, 35, 35);
        if (!myMaze.display(Direction.EAST) || myMaze.getCurrentRoom().getDoor(Room.EAST_INDEX).getForeverLocked()) {  //if there's a display of a door east. Or if the east door is forever locked
            myEastDoor.setBackground(Color.GRAY);
            myEastDoor.setOpaque(true);
            myEastDoor.setEnabled(false);
        } else {
            myEastDoor.setEnabled(true);
            myEastDoor.setBorderPainted(false);
        }
        add(myEastDoor);
    }


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
