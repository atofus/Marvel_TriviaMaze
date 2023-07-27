package view;

import model.*;
import model.Question;

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

    private DBQuestions dbQuestions = new DBQuestions();
    private Question currentQuestion;

    public Display() {
        currentQuestion = dbQuestions.getRandomQuestion();
        createQuestionLayout(); // Create the question layout with answer buttons
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Room [][] mazeMap = myMaze.getMaze();
        Graphics2D g2d = (Graphics2D) g;
        playerIcon = new ImageIcon("TCSS360_TriviaMaze-AlanBranch/images/Comic_Books.png");
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
        drawQuestionRectangle(g);
     //   drawQuestionBox(g);
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

        if (currentQuestion != null) {
            String question = currentQuestion.getQuestion();
            String[] words = question.split(" ");

            g.setFont(new Font("Arial", Font.PLAIN, 18));
            int x = 100; // Starting x position for the text
            int y = 450; // Starting y position for the text
            int lineHeight = g.getFontMetrics().getHeight();

            StringBuilder line = new StringBuilder();
            for (String word : words) {
                if (g.getFontMetrics().stringWidth(line + word) > 636) { // Check if the line width exceeds the width of the rectangle
                    g.drawString(line.toString(), x, y); // Draw the line
                    line = new StringBuilder(); // Reset the line for the next line
                    y += lineHeight; // Move to the next line
                }
                line.append(word).append(" ");
            }

            // Draw the last line (or the only line if the question is short)
            g.drawString(line.toString(), x, y);

            // Display answer options
//            String optionA = "A) " + currentQuestion.getOptionA();
//            String optionB = "B) " + currentQuestion.getOptionB();
//            String optionC = "C) " + currentQuestion.getOptionC();
//            String optionD = "D) " + currentQuestion.getOptionD();

//            int yOffset = y + 100; // Add extra space between question and options
//            g.drawString(optionA, x, yOffset);
//            g.drawString(optionB, x, yOffset + lineHeight);
//            g.drawString(optionC, x, yOffset + 2 * lineHeight);
//            g.drawString(optionD, x, yOffset + 3 * lineHeight);
        }
    }

    public void createQuestionLayout() {
        textField = new JTextField();
        textField.setBounds(4, 4, 4, 4);
        addQuestionButtons(currentQuestion);
        add(optionA);
        add(optionB);
        add(optionC);
        add(optionD);
        optionA.setBounds(100, 530, 200, 50);
        optionB.setBounds(400, 530, 200, 50);
        optionC.setBounds(100, 590, 200, 50);
        optionD.setBounds(400, 590, 200, 50);

        addQuestionListeners(currentQuestion); // Add action listeners to the answer buttons
        optionA.setVisible(false);
        optionB.setVisible(false);
        optionC.setVisible(false);
        optionD.setVisible(false);
        textField.setVisible(false);
    }
   // public void drawQuestionBox(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.CYAN);
//        g2d.setStroke(new BasicStroke(6));
//        g2d.drawRect(80, 150, 636, 200); // Adjust the position and size of the question box
//
//        if (currentQuestion != null) {
//            String question = currentQuestion.getQuestion();
//            String[] words = question.split(" ");
//
//            g.setFont(new Font("Arial", Font.PLAIN, 18));
//            int x = 100; // Starting x position for the text
//            int y = 180; // Starting y position for the text
//            int lineHeight = g.getFontMetrics().getHeight();
//
//            StringBuilder line = new StringBuilder();
//            for (String word : words) {
//                if (g.getFontMetrics().stringWidth(line + word) > 636) { // Check if the line width exceeds the width of the rectangle
//                    g.drawString(line.toString(), x, y); // Draw the line
//                    line = new StringBuilder(); // Reset the line for the next line
//                    y += lineHeight; // Move to the next line
//                }
//                line.append(word).append(" ");
//            }
//
//            // Draw the last line (or the only line if the question is short)
//            g.drawString(line.toString(), x, y);
//        }
//    }

    public void createNorthDoor() {
        doorIcon = new ImageIcon("TCSS360_TriviaMaze-AlanBranch/images/door.png");

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
        doorIcon = new ImageIcon("TCSS360_TriviaMaze-AlanBranch/images/door.png");
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
        doorIcon = new ImageIcon("TCSS360_TriviaMaze-AlanBranch/images/door.png");

        //scaling image down.
        Image image = doorIcon.getImage();
        Image newing = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        doorIcon = new ImageIcon(newing);

        mySouthDoor = new JButton(doorIcon);
        mySouthDoor.setBackground(Color.RED);
        mySouthDoor.setBounds(545, 219, 35, 35);
        if (!myMaze.display(Direction.SOUTH) ||
                (myMaze.getRoom().getDoor(Room.NORTH_INDEX) != null
                        && myMaze.getRoom().getDoor(Room.NORTH_INDEX).getForeverLocked())) {  //&& myMaze.getDoor()) {
            mySouthDoor.setBackground(Color.GRAY);
            mySouthDoor.setEnabled(false);
        } else {
            mySouthDoor.setEnabled(true);
        }
        add(mySouthDoor);
    }

    public void createEastDoor() {
        doorIcon = new ImageIcon("TCSS360_TriviaMaze-AlanBranch/images/door.png");

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

    public void addQuestionButtons(Question question) {
        optionA = new JButton(question.getOptionA());
        optionB = new JButton(question.getOptionB());
        optionC = new JButton(question.getOptionC());
        optionD = new JButton(question.getOptionD());

        optionA.setBounds(100, 450, 200, 50);
        optionB.setBounds(400, 450, 200, 50);
        optionC.setBounds(100, 550, 200, 50);
        optionD.setBounds(400, 550, 200, 50);

        add(optionA);
        add(optionB);
        add(optionC);
        add(optionD);

        addQuestionListeners(question);
    }

    public void addQuestionListeners(Question question) {
        optionA.addActionListener(e -> checkAnswer(question, optionA.getText()));
        optionB.addActionListener(e -> checkAnswer(question, optionB.getText()));
        optionC.addActionListener(e -> checkAnswer(question, optionC.getText()));
        optionD.addActionListener(e -> checkAnswer(question, optionD.getText()));
    }

    public void checkAnswer(Question question, String selectedAnswer) {
        if (question.getCorrectAnswer().equals(selectedAnswer)) {
            System.out.println("Correct!");
            // Handle correct answer here (e.g., update score, move to the next room, etc.)
        } else {
            System.out.println("Wrong!");
            // Handle wrong answer here (e.g., penalize player, show correct answer, etc.)
        }
    }

    public void addListeners() {
        int size = myMaze.getMaze().length - 1; //to make sure door is disabled if we're at edge
        Room[][] myMazeRoom = myMaze.getMaze();

        myNorthDoor.addActionListener(e -> {
            myMaze.setY(myMaze.getY() - 1);
            removeAll();
            revalidate();
            repaint();
            System.out.println(myMaze.getY());
            displayQuestion();
        });

        myEastDoor.addActionListener(e -> {
            myMaze.setX(myMaze.getX() + 1);
            removeAll();
            revalidate();
            repaint();
            System.out.println(myMaze.getX());
            displayQuestion();
        });

        mySouthDoor.addActionListener(e -> {
            myMaze.setY(myMaze.getY() + 1);
            removeAll();
            revalidate();
            repaint();
            System.out.println(myMaze.getY());
            displayQuestion();
        });

        myWestDoor.addActionListener(e -> {
            myMaze.setX(myMaze.getX() - 1);
            removeAll();
            revalidate();
            repaint();
            System.out.println(myMaze.getX());
            displayQuestion();
        });
    }
    public void displayQuestion() {
        removeAll(); // Clear the panel to remove the door buttons

        // Set the visibility of the answer buttons and question text to true
        optionA.setVisible(true);
        optionB.setVisible(true);
        optionC.setVisible(true);
        optionD.setVisible(true);
        textField.setVisible(true);

        createQuestionLayout(); // Create the question layout with answer buttons
        revalidate();
        repaint();
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
//    void drawQuestionRectangle(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.CYAN);
//        g2d.setStroke(new BasicStroke(6));
//        g2d.drawRect(80, 410, 636, 335);
//
//        // Display question and answers in the question rectangle
//        if (myMaze.isQuestionAvailable()) {
//            Question question = myMaze.getCurrentQuestion();
//            String[] options = question.getOptions();
//
//            g.setColor(Color.BLACK);
//            g.setFont(new Font("Arial", Font.PLAIN, 14));
//
//            // Display question text
//            g.drawString("Question: " + question.getQuestionText(), 100, 430);
//
//            // Display answer options
//            int yOffset = 450;
//            for (int i = 0; i < options.length; i++) {
//                g.drawString((char) ('A' + i) + ". " + options[i], 100, yOffset);
//                yOffset += 20;
//            }
//        }
//    }
}
