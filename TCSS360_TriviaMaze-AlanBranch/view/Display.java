package view;

//import extraFiles.SoundPanel;
import model.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.System.exit;

public class Display extends JPanel {

    //maybe just connect panels
    private JButton myNorthDoor;
    private JButton myWestDoor;
    private JButton mySouthDoor;
    private JButton myEastDoor;
    private JLabel myMoveNorth;
    private JLabel myMoveWest;
    private JLabel myMoveSouth;
    private JLabel myMoveEast;
    private JPanel myQuestionPanel;
    private Maze myMaze;
    private JButton optionA;
    private JButton optionB;
    private JButton optionC;
    private JButton optionD;
    private ImageIcon doorIcon;
    private ImageIcon lockIcon;
    private ImageIcon playerIcon;
    private JTextArea myJTextArea;

    private JTextArea myJTextAreaQuestion;
    private JTextField myJTextField;

    private JLabel answer_labelA;
    private JLabel answer_labelB;
    private JLabel answer_labelC;
    private JLabel answer_labelD;
    private JLabel myJTextRoom;

    private int myQuestionNumber = 1;

    //TODO fix meeeeee!!!!!
    public static String charName;

    private String charImage;

    private Timer timer;

    private int scoreVal;
    private String nameVal;

    private final int[] countDownSec = {10};
    private JTextField scoreField;

    private int difficultyLevel;

    boolean hint = false;
    private Question question;
    private DBQuestions myDBQ = new DBQuestions();
    private boolean newQuestion = true;

    private boolean optionAVisibilityHint = true;
    private boolean optionBVisibilityHint = true;
    private boolean optionCVisibilityHint = true;
    private boolean optionDVisibilityHint = true;
    private JLabel myFinishLabel;

    private boolean setButtonsInvisible;

    public Display() {
        question = new Question();
        scoreVal = 0;
        nameVal = "";
        difficultyLevel = 1;
        setButtonsInvisible = false;

        myMaze = Maze.getMyInstance();
        characterSelect();
        getDiffLevel();

        createNorthDoor();
        createEastDoor();
        createWestDoor();
        createSouthDoor();
        addListeners();
    }

    public void characterSelect() {
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("Pick a character, any character.");
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        optionPane.setIcon(getImageResized("images/Comic_Books.png"));
        JButton bwButton = new JButton(getImageResized("images/blackwidow.png"));
        JButton caButton = new JButton(getImageResized("images/captainamerica.png"));
        JButton lokiButton = new JButton(getImageResized("images/loki.png"));
        JButton sButton = new JButton(getImageResized("images/spiderman.png"));


        bwButton.addActionListener(e -> {
            charName = "Black Widow";
            charImage = "images/blackwidow.png";
            optionPane.setValue(bwButton.getText());
        });

        caButton.addActionListener(e -> {
            charName = "Captain America";
            charImage = "images/captainamerica.png";
            optionPane.setValue(caButton.getText());
        });

        lokiButton.addActionListener(e -> {
            charName = "Loki";
            charImage = "images/loki.png";
            optionPane.setValue(lokiButton.getText());
        });

        sButton.addActionListener(e -> {
            charName = "Spiderman";
            charImage = "images/spiderman.png";
            optionPane.setValue(sButton.getText());
        });


        optionPane.setOptions(new Object[] {bwButton, caButton, lokiButton, sButton});
        JDialog dialog = optionPane.createDialog(this, "Character");
        dialog.setVisible(true);

        myMaze.setCharName(charName);
        myMaze.setCharImage(charImage);
    }

    public void getDiffLevel() {

        String[] options = {"Easy", "Medium", "Hard"};

        difficultyLevel = JOptionPane.showOptionDialog(null, "Select Difficulty Level",
                "Difficulty Level", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, null);

        ++difficultyLevel;

        if (difficultyLevel == 1) {
          //  scoreVal += 5;
            myMaze.setScore(myMaze.getScore() + 5);
        } else if (difficultyLevel == 2) {
            //scoreVal += 10;
            myMaze.setScore(myMaze.getScore() + 10);
            randomLocks();
        } else if (difficultyLevel == 3) {
            randomLocks();
            //scoreVal += 15;
            myMaze.setScore(myMaze.getScore() + 15);
        }
    }

    public void randomLocks() {
        myMaze.getRoom(1, 1).getDoor(Room.SOUTH_INDEX).setForeverLocked(true);
        myMaze.getRoom(1, 2).getDoor(Room.NORTH_INDEX).setForeverLocked(true);

        myMaze.getRoom(2, 3).getDoor(Room.SOUTH_INDEX).setForeverLocked(true);
        myMaze.getRoom(2, 4).getDoor(Room.NORTH_INDEX).setForeverLocked(true);

        myMaze.getRoom(4, 2).getDoor(Room.SOUTH_INDEX).setForeverLocked(true);
        myMaze.getRoom(4, 3).getDoor(Room.NORTH_INDEX).setForeverLocked(true);

        myMaze.getRoom(0, 3).getDoor(Room.SOUTH_INDEX).setForeverLocked(true);
        myMaze.getRoom(0, 4).getDoor(Room.NORTH_INDEX).setForeverLocked(true);

        myMaze.getRoom(3, 0).getDoor(Room.SOUTH_INDEX).setForeverLocked(true);
        myMaze.getRoom(3, 1).getDoor(Room.NORTH_INDEX).setForeverLocked(true);
    }

    public Icon getImageResized(String pictureName) {
        ImageIcon charIcon = new ImageIcon(pictureName);
        Image image = charIcon.getImage();
        Image newing = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        charIcon = new ImageIcon(newing);
        Icon icon = (Icon)charIcon;
        return icon;
    }

    public void serialize(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(myMaze);
            fos.close();
            oos.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deserialize(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            myMaze = (Maze) ois.readObject();
            ois.close();
            fis.close();

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        //we now got all our data back.
        //we're now removing everything from before and repainting with our new data.
        removeAll();
        revalidate();
        repaint();

        //with our new position, we'll have to recheck all the doors once more.
        repeat();
    }

    public void createNorthDoor() {
        doorIcon = new ImageIcon("images/door.png");

        //scaling image down.
        Image image = doorIcon.getImage();
        Image newing = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        doorIcon = new ImageIcon(newing);

        myMoveNorth = new JLabel("Move North");
        myMoveNorth.setFont(new Font("Snap ITC", Font.PLAIN, 15));
        myMoveNorth.setBounds(513, 70, 109, 37);
        myMoveNorth.setForeground(Color.PINK);

        myNorthDoor = new JButton(doorIcon);
        myNorthDoor.setBackground(Color.RED);
        myNorthDoor.setOpaque(true);
        myNorthDoor.setBounds(545, 105, 35, 35);

        if (!myMaze.display(Direction.NORTH) || myMaze.getCurrentRoom().getDoor(Room.NORTH_INDEX).getForeverLocked()) {  //&& myMaze.getDoor()) {
            myNorthDoor.setBackground(Color.GRAY);
            myNorthDoor.setOpaque(true);
            myNorthDoor.setEnabled(false);
            // System.out.println("calling");
        } else {
            //System.out.println("North door open");
            myNorthDoor.setEnabled(true);
            myNorthDoor.setBorderPainted(false);
        }
        add(myNorthDoor);
        add(myMoveNorth);

        if (setButtonsInvisible) {
            myNorthDoor.setVisible(false);
        }
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
        myWestDoor.setOpaque(true);

        myMoveWest = new JLabel("Move West");
        myMoveWest.setFont(new Font("Snap ITC", Font.PLAIN, 15));
        myMoveWest.setBounds(386, 157, 109, 37);
        myMoveWest.setForeground(Color.PINK);

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
        add(myMoveWest);

        if (setButtonsInvisible) {
            myWestDoor.setVisible(false);
        }
    }

    public void createSouthDoor() {
        doorIcon = new ImageIcon("images/door.png");

        //scaling image down.
        Image image = doorIcon.getImage();
        Image newing = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        doorIcon = new ImageIcon(newing);

        myMoveSouth = new JLabel("Move South");
        myMoveSouth.setFont(new Font("Snap ITC", Font.PLAIN, 15));
        myMoveSouth.setBounds(513, 250, 109, 37);
        myMoveSouth.setForeground(Color.PINK);

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
        add(myMoveSouth);

        if (setButtonsInvisible) {
            mySouthDoor.setVisible(false);
        }
    }

    public void createEastDoor() {
        doorIcon = new ImageIcon("images/door.png");

        //scaling image down.
        Image image = doorIcon.getImage();
        Image newing = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        doorIcon = new ImageIcon(newing);

        myMoveEast = new JLabel("Move East");
        myMoveEast.setFont(new Font("Snap ITC", Font.PLAIN, 15));
        myMoveEast.setBounds(645, 157, 109, 37);
        myMoveEast.setForeground(Color.PINK);

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
        add(myMoveEast);

        if (setButtonsInvisible) {
            myEastDoor.setVisible(false);
        }
    }


    public Question getQuestion() {
        return question;
    }

    public void provideHint(final Question theQuest) {

        ArrayList<JLabel> incorrectOptions = new ArrayList<>();

        if (newQuestion) {
            optionAVisibilityHint = true;
            optionBVisibilityHint = true;
            optionCVisibilityHint = true;
            optionDVisibilityHint = true;
            newQuestion = false;
        }

        if (hint) {
            if (!answer_labelA.getText().equals(theQuest.getAnswer()) && optionAVisibilityHint) {
                incorrectOptions.add(answer_labelA);
            }
            if (!answer_labelB.getText().equals(theQuest.getAnswer()) && optionBVisibilityHint) {
                incorrectOptions.add(answer_labelB);
            }
            if (!answer_labelC.getText().equals(theQuest.getAnswer()) && optionCVisibilityHint) {
                incorrectOptions.add(answer_labelC);
            }
            if (!answer_labelD.getText().equals(theQuest.getAnswer()) && optionDVisibilityHint) {
                incorrectOptions.add(answer_labelD);
            }
        }

        hint = false;

        //System.out.println("Size: " + incorrectOptions.size());

        if (!incorrectOptions.isEmpty()) {
            int randIndex = new Random().nextInt(incorrectOptions.size());
            incorrectOptions.get(randIndex).setVisible(false);

            if (incorrectOptions.get(randIndex).equals(answer_labelA)) {
                optionA.setVisible(false);
                optionAVisibilityHint = false;
            } else if (incorrectOptions.get(randIndex).equals(answer_labelB)) {
                optionBVisibilityHint = false;
                optionB.setVisible(false);
            } else if (incorrectOptions.get(randIndex).equals(answer_labelC)) {
                optionCVisibilityHint = false;
                optionC.setVisible(false);
            } else if (incorrectOptions.get(randIndex).equals(answer_labelD)) {
                optionDVisibilityHint = false;
                optionD.setVisible(false);
            }
        }

        revalidate();
        repaint();
    }

    public void addListeners() {
        myNorthDoor.addActionListener(e -> {
            createTimer(Room.NORTH_INDEX);

            if (!myMaze.getCurrentRoom().getDoor(Room.NORTH_INDEX).getLock()) { //if the door isn't locked we could just move the player through it.
                myMaze.movePlayer(Direction.NORTH);
                //timer.stop();
                //scoreVal += countDownSec[0];
                removeAll();
                revalidate();
                repaint();

                repeat();
            } else {
                timer.start();
                turnDoorButtonsOFF();
                createQuestionLayout(Direction.NORTH);
                revalidate();
            }
            //timer.stop();
            System.out.println(myMaze.getY());
        });

        myEastDoor.addActionListener(e -> {
            createTimer(Room.EAST_INDEX);
            if (!myMaze.getCurrentRoom().getDoor(Room.EAST_INDEX).getLock()) { //if the door isn't locked we could just move the player through it.
                //timer.stop();
                myMaze.movePlayer(Direction.EAST);
                removeAll();
                revalidate();
                repaint();

                repeat();
            } else {
                timer.start();
                turnDoorButtonsOFF();
                createQuestionLayout(Direction.EAST);

                //timer.stop();

            }
            //timer.stop();
            System.out.println(myMaze.getX());
        });

        mySouthDoor.addActionListener(e -> {

//            Door theDoor = myMaze.getCurrentRoom().getDoor(Room.SOUTH_INDEX);
//            if (hint) {
//                if (!theDoor.getOptionA().equals(theDoor.getAnswer())) {
//                    optionA.setVisible(false);
//                } else if (!theDoor.getOptionB().equals(theDoor.getAnswer())) {
//                    optionB.setVisible(false);
//                } else if (!theDoor.getOptionC().equals(theDoor.getAnswer())) {
//                    optionC.setVisible(false);
//                } else if (!theDoor.getOptionD().equals(theDoor.getAnswer())) {
//                    optionD.setVisible(false);
//                }
//            }

            createTimer(Room.SOUTH_INDEX);

            if (!myMaze.getCurrentRoom().getDoor(Room.SOUTH_INDEX).getLock()) { //if the door isn't locked we could just move the player through it.
                //timer.stop();
                myMaze.movePlayer(Direction.SOUTH);
                removeAll();
                revalidate();
                repaint();

                repeat();
            } else {

                timer.start();

                turnDoorButtonsOFF();

                createQuestionLayout(Direction.SOUTH);

                //timer.stop();
                revalidate();

            }
            //timer.stop();
//            System.out.println(myMaze.getCurrentRoom().getRoomNumber());
            System.out.println(myMaze.getY());
            //timer.stop();
        });

        myWestDoor.addActionListener(e -> {

            createTimer(Room.WEST_INDEX);

            if (!myMaze.getCurrentRoom().getDoor(Room.WEST_INDEX).getLock()) { //if the door isn't already locked.
                //timer.stop();
                myMaze.movePlayer(Direction.WEST);
                removeAll();
                revalidate();
                repaint();

                repeat();
            } else {
                timer.start();

                turnDoorButtonsOFF();

                createQuestionLayout(Direction.WEST);
                //timer.stop();
                revalidate();
            }
            //timer.stop();
            // System.out.println(myMaze.getCurrentRoom().getRoomNumber());
            System.out.println(myMaze.getX());
        });


    }

    public void setHint(boolean theHint) {
        hint = theHint;
    }

    public void createTimer(int indexDir) {
        JLabel countField = new JLabel();
        if (difficultyLevel == 1) {
            countDownSec[0] = 15;
            countField.setText("Time: 16");
            //scoreVal += 5;
        } else if (difficultyLevel == 2) {
            countDownSec[0] = 10;
            countField.setText("Time: 11");
            //scoreVal += 10;
        } else if (difficultyLevel == 3) {
            countDownSec[0] = 5;
            countField.setText("Time: 6");
            //scoreVal += 15;
        } else {
            countDownSec[0] = 10;
            countField.setText("Time: 11");
            //scoreVal += 5;
        }


        countField.setBounds(560,410, 150,50);
        countField.setBackground(new Color(25,25,25));
        countField.setForeground(new Color(255,255,0));
        countField.setFont(new Font("MV Boli",Font.BOLD,25));
        add(countField);



        //System.out.println(scoreField.getText());

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                countField.setText("Time: " + countDownSec[0]);
                countDownSec[0]--;

                System.out.println(countField.getText());

                if (countDownSec[0] == -1) {
                    timer.stop();
                    System.out.println("Locked!");

                    Door theDoor = myMaze.getCurrentRoom().getDoor(indexDir);
//                    if (!theDoor.getOptionA().equals(theDoor.getAnswer())) {
//                        optionA.doClick();
//                    } else if (!theDoor.getOptionB().equals(theDoor.getAnswer())) {
//                        optionB.doClick();
//                    } else if (!theDoor.getOptionC().equals(theDoor.getAnswer())) {
//                        optionC.doClick();
//                    } else if (!theDoor.getOptionD().equals(theDoor.getAnswer())) {
//                        optionD.doClick();
//                    }

                    if (!question.getOptionA().equals(question.getAnswer())) {
                        optionA.doClick();
                    } else if (!question.getOptionB().equals(question.getAnswer())) {
                        optionB.doClick();
                    } else if (!question.getOptionC().equals(question.getAnswer())) {
                        optionC.doClick();
                    } else if (!question.getOptionD().equals(question.getAnswer())) {
                        optionD.doClick();
                    }

                    repaint();

                    timer = null;
                }
            }
        });
    }


    public void createQuestionLayout(final Direction theDir) {
        question = myDBQ.getRandomQuestion(myMaze.getCharName());

        newQuestion = true;
        //createTimer();
        //timer.start();

        myQuestionPanel = new JPanel();
        myQuestionPanel.setLayout(null);
        myQuestionPanel.setBackground(Color.BLACK);

        myQuestionPanel.setBounds(83, 413, 630, 329);

        int index;
        if (theDir == Direction.NORTH) {
            index = Room.NORTH_INDEX;
        } else if (theDir == Direction.EAST){
            index = Room.EAST_INDEX;
        } else if (theDir == Direction.SOUTH) {
            index = Room.SOUTH_INDEX;
        } else {
            index = Room.WEST_INDEX;
        }

        Door door = myMaze.getCurrentRoom().getDoor(index);

        optionA = new JButton();
        optionB = new JButton();
        optionC = new JButton();
        optionD = new JButton();
        myJTextArea = new JTextArea();
        myJTextField = new JTextField();
        answer_labelA = new JLabel();
        answer_labelB = new JLabel();
        answer_labelC = new JLabel();
        answer_labelD = new JLabel();

        //scoreField = new JTextField();

        myJTextAreaQuestion = new JTextArea();

//        myJTextField.setBounds(90,474,616,54);
//        myJTextField.setBounds(0, 54, 630, 54);
//        myJTextField.setBackground(new Color(25,25,25));
//        myJTextField.setForeground(new Color(255,165,0));
//        myJTextField.setFont(new Font("Comic Sans MS",Font.BOLD,10));
//        myJTextField.setBorder(BorderFactory.createBevelBorder(1));
//        myJTextField.setHorizontalAlignment(JTextField.CENTER);
//        myJTextField.setEditable(false);
//        myJTextField.setText(door.getQuestion());
//        myJTextArea.setLineWrap(true);
//        myJTextArea.setWrapStyleWord(true);



        myJTextAreaQuestion.setBounds(0, 54, 630, 60);
        myJTextAreaQuestion.setBackground(new Color(25,25,25));
        myJTextAreaQuestion.setForeground(new Color(255,165,0));
        myJTextAreaQuestion.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        myJTextAreaQuestion.setBorder(BorderFactory.createBevelBorder(1));
        myJTextAreaQuestion.setEditable(false);
        myJTextAreaQuestion.setText(question.getQuestion());
        myJTextAreaQuestion.setLineWrap(true);
        myJTextAreaQuestion.setWrapStyleWord(true);

        //myJTextArea.setBounds(90,420,616,54);
        myJTextArea.setBounds(0, 0, 630, 54);
        myJTextArea.setLineWrap(true);
        myJTextArea.setWrapStyleWord(true);
        myJTextArea.setBackground(new Color(25,25,25));
        myJTextArea.setForeground(new Color(255,255,0));
        myJTextArea.setFont(new Font("Comic Sans MS",Font.BOLD,25));
        myJTextArea.setBorder(BorderFactory.createBevelBorder(1));
        myJTextArea.setEditable(false);
        myJTextArea.setText("Question " + myQuestionNumber);

        //optionA.setBounds(90,540,40,40);
        optionA.setBounds(0, 120, 44, 40);
        optionA.setFont(new Font("MV Boli",Font.BOLD,10));
        optionA.setFocusable(false);
        optionA.setText("A");

        // optionB.setBounds(90,591,40,40);
        optionB.setBounds(0, 171, 44, 40);
        optionB.setFont(new Font("MV Boli",Font.BOLD,10));
        optionB.setFocusable(false);
        optionB.setText("B");

        //  optionC.setBounds(90,642,40,40);
        optionC.setBounds(0, 222, 44, 40);
        optionC.setFont(new Font("MV Boli",Font.BOLD,10));
        optionC.setFocusable(false);
        optionC.setText("C");

        // optionD.setBounds(90,693,40,40);
        optionD.setBounds(0, 273, 44, 40);
        optionD.setFont(new Font("MV Boli",Font.BOLD,10));
        optionD.setFocusable(false);
        optionD.setText("D");

        answer_labelA.setBounds(50, 120, 566, 40);
        answer_labelA.setBackground(new Color(50,50,50));
        answer_labelA.setForeground(new Color(230,230,250));
        answer_labelA.setFont(new Font("MV Boli",Font.PLAIN,20));
        answer_labelA.setText(question.getOptionA());

        // answer_labelB.setBounds(140,591,566,40);
        answer_labelB.setBounds(50, 171, 566, 40);
        answer_labelB.setBackground(new Color(50,50,50));
        answer_labelB.setForeground(new Color(230,230,250));
        answer_labelB.setFont(new Font("MV Boli",Font.PLAIN,20));
        answer_labelB.setText(question.getOptionB());

        //  answer_labelC.setBounds(140,642,566,40);
        answer_labelC.setBounds(50, 222, 566, 40);
        answer_labelC.setBackground(new Color(50,50,50));
        answer_labelC.setForeground(new Color(230,230,250));
        answer_labelC.setFont(new Font("MV Boli",Font.PLAIN,20));
        answer_labelC.setText(question.getOptionC());

        //  answer_labelD.setBounds(140,693,566,40);
        answer_labelD.setBounds(50, 273, 566, 40);
        answer_labelD.setBackground(new Color(50,50,50));
        answer_labelD.setForeground(new Color(230,230,250));
        answer_labelD.setFont(new Font("MV Boli",Font.PLAIN,20));
        answer_labelD.setText(question.getOptionD());

        myQuestionPanel.add(optionA);
        myQuestionPanel.add(optionB);
        myQuestionPanel.add(optionC);
        myQuestionPanel.add(optionD);
        myQuestionPanel.add(myJTextArea);
        //panel.add(myJTextField);
        //panel.add(scoreField);
        myQuestionPanel.add(myJTextAreaQuestion);
        myQuestionPanel.add(answer_labelA);
        myQuestionPanel.add(answer_labelB);
        myQuestionPanel.add(answer_labelC);
        myQuestionPanel.add(answer_labelD);
        add(myQuestionPanel);

        //TODO hint
//        if (hint) {
//            if (!answer_labelA.getText().equals(door.getAnswer())) {
//                answer_labelA.setVisible(false);
//                optionA.setVisible(false);
//                hint = false;
//            }
//        }



        myQuestionPanel.revalidate();
        myQuestionPanel.repaint();


        addListenersOptions(theDir);
        myQuestionNumber++;

        //timer.stop();
    }

    public void addListenersOptions(Direction theDir) {
        optionA.setEnabled(true);
        optionB.setEnabled(true);
        optionC.setEnabled(true);
        optionD.setEnabled(true);
        int index;
        if (theDir == Direction.NORTH) {
            index = Room.NORTH_INDEX;
        } else if (theDir == Direction.EAST){
            index = Room.EAST_INDEX;
        } else if (theDir == Direction.SOUTH) {
            index = Room.SOUTH_INDEX;
        } else {
            index = Room.WEST_INDEX;
        }

        Door theDoor = myMaze.getCurrentRoom().getDoor(index);
        optionA.addActionListener(e -> {
            //     System.out.println("HI");
            if (!question.getOptionA().equals(question.getAnswer())) {
                timer.stop();
                theDoor.setForeverLocked(true);
                myMaze.lockingDoors(theDir);

                SoundPanel.playLockSound();
            } else {
                //     System.out.println("Cool they got it right");
                timer.stop();
                //scoreVal += countDownSec[0];
                myMaze.setScore(myMaze.getScore() + countDownSec[0]);
                //scoreField.setText("Score: " + scoreVal);

                theDoor.setForeverLocked(false);
                myMaze.unlockingDoors(theDir);
                theDoor.unlock();
                myMaze.movePlayer(theDir);

                SoundPanel.playCorrectAnswerSound();
            }
            removeAll();
            revalidate();
            repaint();

            repeat();

            try {
                checkEndGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            timer = null;
        });

        optionB.addActionListener(e -> {
            //    System.out.println("HI");
            if (!question.getOptionB().equals(question.getAnswer())) {
                timer.stop();
                theDoor.setForeverLocked(true);
                myMaze.lockingDoors(theDir);

                SoundPanel.playLockSound();
            } else {
                //    System.out.println("Cool they got it right");
                timer.stop();
              //  scoreVal += countDownSec[0];
                myMaze.setScore(myMaze.getScore() + countDownSec[0]);
                //scoreField.setText("Score: " + scoreVal);

                theDoor.setForeverLocked(false);
                myMaze.unlockingDoors(theDir);
                theDoor.unlock();
                myMaze.movePlayer(theDir);

                SoundPanel.playCorrectAnswerSound();
            }
            removeAll();
            revalidate();
            repaint();

            repeat();

            try {
                checkEndGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            timer = null;


        });

        optionC.addActionListener(e -> {
            //   System.out.println("HI");
            if (!question.getOptionC().equals(question.getAnswer())) {
                timer.stop();
                theDoor.setForeverLocked(true);
                myMaze.lockingDoors(theDir);

                SoundPanel.playLockSound();
            } else {
                //      System.out.println("Cool they got it right");
                timer.stop();
                //scoreVal += countDownSec[0];
                myMaze.setScore(myMaze.getScore() + countDownSec[0]);
                //scoreField.setText("Score: " + scoreVal);

                theDoor.setForeverLocked(false);
                myMaze.unlockingDoors(theDir);
                theDoor.unlock();
                myMaze.movePlayer(theDir);

                SoundPanel.playCorrectAnswerSound();
            }
            removeAll();
            revalidate();
            repaint();

            repeat();

            try {
                checkEndGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            timer = null;
        });

        optionD.addActionListener(e -> {
            //     System.out.println("HI");
            if (!question.getOptionD().equals(question.getAnswer())) {
                timer.stop();
                theDoor.setForeverLocked(true);
                myMaze.lockingDoors(theDir);

                SoundPanel.playLockSound();
            } else {
                //       System.out.println("Cool they got it right");
                timer.stop();
                //scoreVal += countDownSec[0];
                myMaze.setScore(myMaze.getScore() + countDownSec[0]);
                //scoreField.setText("Score: " + scoreVal);

                theDoor.setForeverLocked(false);
                myMaze.unlockingDoors(theDir);
                theDoor.unlock();
                myMaze.movePlayer(theDir);

                SoundPanel.playCorrectAnswerSound();
            }
            removeAll();
            revalidate();
            repaint();

            repeat();

            try {
                checkEndGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            timer = null;
        });

//        timer.stop();
    }




    @Override
    public void paintComponent (Graphics g) {
//        JPanel mapPanel = new JPanel();
//        mapPanel.setBounds(0, 0, 421, 396);
//        mapPanel.setBackground(Color.WHITE);
//        add(mapPanel);
        super.paintComponent(g);
        Room [][] mazeMap = myMaze.getMaze();
        Graphics2D g2d = (Graphics2D) g;
        playerIcon = new ImageIcon(myMaze.getCharImage());

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
                    g2d.drawImage(playerIcon.getImage(), currentX + boxWidth/2 - playerIcon.getIconWidth()/2,
                            currentY + boxHeight/2 - playerIcon.getIconHeight()/2, null);
                }
            }
        }

        myFinishLabel = new JLabel("<--- Finish Here to WIN some Chimichangas! ;)");
        myFinishLabel.setFont((new Font("Comic Sans MS", Font.BOLD, 14)));
        myFinishLabel.setBounds(365, 280, 400, 37);
        myFinishLabel.setForeground(Color.RED);
        add(myFinishLabel);




        g.setColor(Color.LIGHT_GRAY);
        try {
            drawLock(g);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void drawLock(Graphics g) throws IOException, InterruptedException {
        Graphics2D g2d = (Graphics2D) g;

        Room [][] mazeMap = myMaze.getMaze();
        lockIcon = new ImageIcon("images/lock.png");
        Image image = lockIcon.getImage();
        Image newing = image.getScaledInstance(23, 23, java.awt.Image.SCALE_SMOOTH);
        lockIcon = new ImageIcon(newing);


        int boxWidth = 70;
        int boxHeight = 70;
        for (int i = 0; i < mazeMap.length; i++) { //want to go through each room.
            for (int j = 0; j < mazeMap[i].length; j++) {
                for (int doorIndex = 0; doorIndex < 4; doorIndex++) {
                    int xCoords = i * boxWidth;
                    int yCoords = j * boxHeight;
                    if (myMaze.getRoom(i, j).getDoor(doorIndex) != null && myMaze.getRoom(i, j).getDoor(doorIndex).getForeverLocked()) {
                        if (doorIndex == Room.NORTH_INDEX) { //north
                            g2d.drawImage(lockIcon.getImage(), xCoords + boxWidth/2 - lockIcon.getIconWidth()/2,
                                    yCoords + boxHeight/2 - lockIcon.getIconHeight() - 26, null);
                        } else if (doorIndex == Room.EAST_INDEX) { //east
                            g2d.drawImage(lockIcon.getImage(), xCoords + boxWidth/2 + lockIcon.getIconWidth() + 2,
                                    yCoords + boxHeight/2 - lockIcon.getIconHeight()/2, null);
                        } else if (doorIndex == Room.SOUTH_INDEX) { //south
                            g2d.drawImage(lockIcon.getImage(), xCoords + boxWidth/2 - lockIcon.getIconWidth()/2,
                                    yCoords + boxHeight/2 + lockIcon.getIconHeight() - 2, null);
                        } else { //west
                            g2d.drawImage(lockIcon.getImage(), xCoords + boxWidth/2 - lockIcon.getIconWidth() - 22,
                                    yCoords + boxHeight/2 - lockIcon.getIconHeight()/2, null);
                        }
                    }
                }
            }
        }



        drawRoomBox(g);
    }

    public void addTime() {
        countDownSec[0] += 6;
    }

    public Timer getTimer() {
        return timer;
    }

    public void checkEndGame() throws IOException, InterruptedException {
        if (myMaze.gameFinished()) {

            nameVal = JOptionPane.showInputDialog("You won! What is your name?");
            String nameAndScore = "Congratulations " + nameVal + " you scored " + myMaze.getScore() + " points.";
            JOptionPane.showMessageDialog(null, nameAndScore);
            setButtonsInvisible = true;

            leaderboard();
            //exit(0);
            SoundPanel.stopBackgroundMusic();
            SoundPanel.playWinSound();
        }

        if (!myMaze.isPossible()) {
            //timer.stop();
            //drawLock(g);
            nameVal = JOptionPane.showInputDialog("Game Over! What is your name?");
            String nameAndScore = "Congratulations " + nameVal + " you scored " + myMaze.getScore() + " points.";
            JOptionPane.showMessageDialog(null, nameAndScore);

            setButtonsInvisible = true;

            leaderboard();
            SoundPanel.stopBackgroundMusic();
            SoundPanel.playLoseSound();

        }

        if (setButtonsInvisible) {
            mySouthDoor.setVisible(false);
            myEastDoor.setVisible(false);
            myNorthDoor.setVisible(false);
            myWestDoor.setVisible(false);

            myMoveNorth.setVisible(false);
            myMoveSouth.setVisible(false);
            myMoveEast.setVisible(false);
            myMoveWest.setVisible(false);

            myFinishLabel.setVisible(false);
            myJTextRoom.setVisible(false);
            SoundPanel.stopBackgroundMusic();
        }

        //revalidate();
        //repaint();
    }

    void drawRoomBox(Graphics g) throws IOException, InterruptedException {

//        boolean setButtonsInvisible = false;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.YELLOW);
        g2d.drawRect(488, 104, 150, 150);

        //TODO FIX MEEEEEEEE
        //checkEndGame();

//        if (setButtonsInvisible) {
//            mySouthDoor.setVisible(false);
//            myEastDoor.setVisible(false);
//            myNorthDoor.setVisible(false);
//            myWestDoor.setVisible(false);
//
//            myMoveNorth.setVisible(false);
//            myMoveSouth.setVisible(false);
//            myMoveEast.setVisible(false);
//            myMoveWest.setVisible(false);
//
//            myFinishLabel.setVisible(false);
//        }
        drawQuestionArea(g);
    }

    public String getNameVal() {
        return nameVal;
    }

    public int getScoreVal() {
        return scoreVal;
    }



    public void leaderboard () throws IOException, IOException, InterruptedException {

        int numLeaders = 1;

        TreeMap<Integer, String> leader = new TreeMap<>(Collections.reverseOrder());

        String filename = "Leaderboard.txt";
        Scanner scanner = new Scanner(new File(filename));
        //scanner.useDelimiter(" ");

        while (scanner.hasNext()) {
            Integer inScore = scanner.nextInt();
            String inName = scanner.nextLine();
            inName = inName.trim();

            System.out.println("Name:" + inName);

            leader.put(inScore, inName);
        }

        if (!nameVal.equals("")) {
            leader.put(myMaze.getScore(), nameVal);
        }


        scanner.close();

        StringBuilder sb = new StringBuilder("Leader Board\n");

        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        for (Map.Entry<Integer, String> entry: leader.entrySet()) {
            writer.write(entry.getKey() + " " + entry.getValue() + "\n");
            //System.out.println(entry.getKey() + " " + entry.getValue());

            if (entry.getKey() <= 9) {
                sb.append(numLeaders + ". Score: " + entry.getKey() + "   Name: " + entry.getValue() + "\n");
            } else {
                sb.append(numLeaders + ". Score: " + entry.getKey() + " Name: " + entry.getValue() + "\n");
            }


            numLeaders++;
        }

        JTextArea scoreTextField = new JTextArea(sb.toString());
        scoreTextField.setBounds(0,0, 800, 800);
        //scoreTextField.setHorizontalAlignment(JTextField.CENTER);
        scoreTextField.setFont(new Font("Geneva", Font.BOLD, 40));
        scoreTextField.setBackground(Color.BLACK);
        scoreTextField.setForeground(Color.YELLOW);
        add(scoreTextField);

        //scoreTextField.wait(10000);

        //Thread.sleep(10000);

//        revalidate();
//        repaint();

        writer.close();

        //setButtonsInvisible = true;
        revalidate();
        repaint();



//        mySouthDoor.setVisible(false);
//        myEastDoor.setVisible(false);
//        myNorthDoor.setVisible(false);
//        myWestDoor.setVisible(false);
//        myMoveNorth.setVisible(false);
//        myMoveSouth.setVisible(false);
//        myMoveEast.setVisible(false);
//        myMoveWest.setVisible(false);

        //removeAll();


        //JOptionPane.showMessageDialog(null, sb.toString());




    }




    void drawQuestionArea(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.CYAN);
        g2d.setStroke(new BasicStroke(6));
        g2d.drawRect(80, 410, 636, 335);

        //displayRoomNumber();


        ImageIcon myDeadpoolIcon = new ImageIcon("images/deadpoolLay2.png");
        Image image = myDeadpoolIcon.getImage();
        Image newing = image.getScaledInstance(300, 200, java.awt.Image.SCALE_SMOOTH);
        myDeadpoolIcon = new ImageIcon(newing);
        g2d.drawImage(myDeadpoolIcon.getImage(), 350, 245, null);

        drawDeadpoolStool(g);
        displayRoomNumber(); //a jlabel that will display what room we're in.
    }

    void drawDeadpoolStool(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.CYAN);
        g2d.setStroke(new BasicStroke(6));
        g2d.drawRect(370, 399, 50, 10);
    }

    public void displayRoomNumber() {
        myJTextRoom = new JLabel();
        myJTextRoom.setBounds(425, 36, 360, 31);
        myJTextRoom.setBackground(Color.BLACK);
        myJTextRoom.setForeground(Color.MAGENTA);
        myJTextRoom.setFont(new Font("Aharoni", Font.PLAIN, 20));
        myJTextRoom.setText("You are currently at Room: " + myMaze.getCurrentRoom().getRoomNumber());
        add(myJTextRoom);
    }

    /**
     * Helper method that turns off all the button doors.
     * Used to eliminate redundancy in code.
     */
    private void turnDoorButtonsOFF() {
        myEastDoor.setEnabled(false);
        myEastDoor.setBackground(Color.GRAY);
        myEastDoor.setOpaque(true);

        myWestDoor.setEnabled(false);
        myWestDoor.setBackground(Color.GRAY);
        myWestDoor.setOpaque(true);

        myNorthDoor.setEnabled(false);
        myNorthDoor.setBackground(Color.GRAY);
        myNorthDoor.setOpaque(true);

        mySouthDoor.setEnabled(false);
        mySouthDoor.setBackground(Color.GRAY);
        mySouthDoor.setOpaque(true);
    }

    /**
     * Helper method that goes at looks at each door with new data
     * and its listeners. Used to eliminate redundancy in code.
     */
    private void repeat() {
        createNorthDoor();
        createEastDoor();
        createWestDoor();
        createSouthDoor();
        addListeners();
    }


}
