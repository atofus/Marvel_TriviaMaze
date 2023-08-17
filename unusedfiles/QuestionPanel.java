package unusedfiles;

import model.Direction;
import model.Door;

import javax.swing.*;
import java.awt.*;

public class QuestionPanel extends JPanel {

    private boolean correctNess = true;
    private JButton optionA = new JButton();
    private JButton optionB = new JButton();
    private JButton optionC = new JButton();
    private JButton optionD = new JButton();
    private JTextArea myJTextArea = new JTextArea();
    private JTextField myJTextField = new JTextField();

    private JLabel answer_labelA = new JLabel();
    private JLabel answer_labelB = new JLabel();
    private JLabel answer_labelC = new JLabel();
    private JLabel answer_labelD = new JLabel();
    private final Door myDoor;
    private final int myRoomIndex;
    private final Direction myDir;
    private int myQuestionNumber;



    public QuestionPanel(Door theDoor, int theIndex, Direction theDir) {
        myDoor = theDoor;
        myRoomIndex = theIndex;
        myDir = theDir;
        createQuestionLayout();
    }


    public void createQuestionLayout() {
        setLayout(null);
        setBackground(Color.BLACK);

        setBounds(83, 413, 630, 329);

       // Door door = myMaze.getCurrentRoom().getDoor(theIndex);

//        myJTextField.setBounds(90,474,616,54);
        myJTextField.setText(myDoor.getQuestion());
        myJTextField.setFont(new Font("Geneva", Font.PLAIN, 10));
        myJTextField.setBounds(0, 54, 630, 54);
        myJTextField.setBackground(new Color(25,25,25));
        myJTextField.setForeground(new Color(25,255,0));
        //myJTextField.setFont(new Font("Ink Free",Font.BOLD,10));
        myJTextField.setBorder(BorderFactory.createBevelBorder(1));
        myJTextField.setHorizontalAlignment(JTextField.CENTER);
        myJTextField.setEditable(false);

        //myJTextField.setText(myDoor.getQuestion());
        //myJTextField.setFont(new Font("Ink Free",Font.BOLD,10));
        myJTextArea.setLineWrap(true);
        myJTextArea.setWrapStyleWord(true);

        //myJTextArea.setBounds(90,420,616,54);
        myJTextArea.setBounds(0, 0, 630, 54);
        myJTextArea.setLineWrap(true);
        myJTextArea.setWrapStyleWord(true);
        myJTextArea.setBackground(new Color(25,25,25));
        myJTextArea.setForeground(new Color(25,255,0));
        myJTextArea.setFont(new Font("MV Boli",Font.BOLD,25));
        myJTextArea.setBorder(BorderFactory.createBevelBorder(1));
        myJTextArea.setEditable(false);
        myJTextArea.setText("Question " + myQuestionNumber);

        //optionA.setBounds(90,540,40,40);
        optionA.setBounds(0, 120, 40, 40);
        optionA.setFont(new Font("MV Boli",Font.BOLD,35));
        optionA.setFocusable(false);
        //  optionA.addActionListener((ActionListener) this);
        optionA.setText("A");

        // optionB.setBounds(90,591,40,40);
        optionB.setBounds(0, 171, 40, 40);
        optionB.setFont(new Font("MV Boli",Font.BOLD,35));
        optionB.setFocusable(false);
        //  optionB.addActionListener((ActionListener) this);
        optionB.setText("B");

        //  optionC.setBounds(90,642,40,40);
        optionC.setBounds(0, 222, 40, 40);
        optionC.setFont(new Font("MV Boli",Font.BOLD,35));
        optionC.setFocusable(false);
        //  optionC.addActionListener((ActionListener) this);
        optionC.setText("C");

        // optionD.setBounds(90,693,40,40);
        optionD.setBounds(0, 273, 40, 40);
        optionD.setFont(new Font("MV Boli",Font.BOLD,35));
        optionD.setFocusable(false);
        //   optionD.addActionListener((ActionListener) this);
        optionD.setText("D");

        //answer_labelA.setBounds(140,540,566,40);
        answer_labelA.setBounds(50, 120, 566, 40);
        answer_labelA.setBackground(new Color(50,50,50));
        answer_labelA.setForeground(new Color(25,255,0));
        answer_labelA.setFont(new Font("MV Boli",Font.PLAIN,20));
        answer_labelA.setText(myDoor.getOptionA());

        // answer_labelB.setBounds(140,591,566,40);
        answer_labelB.setBounds(50, 171, 566, 40);
        answer_labelB.setBackground(new Color(50,50,50));
        answer_labelB.setForeground(new Color(25,255,0));
        answer_labelB.setFont(new Font("MV Boli",Font.PLAIN,20));
        answer_labelB.setText(myDoor.getOptionB());

        //  answer_labelC.setBounds(140,642,566,40);
        answer_labelC.setBounds(50, 222, 566, 40);
        answer_labelC.setBackground(new Color(50,50,50));
        answer_labelC.setForeground(new Color(25,255,0));
        answer_labelC.setFont(new Font("MV Boli",Font.PLAIN,20));
        answer_labelC.setText(myDoor.getOptionC());

        //  answer_labelD.setBounds(140,693,566,40);
        answer_labelD.setBounds(50, 273, 566, 40);
        answer_labelD.setBackground(new Color(50,50,50));
        answer_labelD.setForeground(new Color(25,255,0));
        answer_labelD.setFont(new Font("MV Boli",Font.PLAIN,20));
        answer_labelD.setText(myDoor.getOptionD());

        add(optionA);
        add(optionB);
        add(optionC);
        add(optionD);
        add(myJTextArea);
        add(myJTextField);
        add(answer_labelA);
        add(answer_labelB);
        add(answer_labelC);
        add(answer_labelD);
        setVisible(true);

        revalidate();
        repaint();

        addListenersOptions();
        myQuestionNumber++;
    }

    public void addListenersOptions() {
        optionA.setEnabled(true);
        optionB.setEnabled(true);
        optionC.setEnabled(true);
        optionD.setEnabled(true);

        //Door theDoor = myMaze.getCurrentRoom().getDoor(theIndex);
        optionA.addActionListener(e -> {
            setCorrect(myDoor.getOptionA().equals(myDoor.getAnswer()));
        });

        optionB.addActionListener(e -> {
            setCorrect(myDoor.getOptionB().equals(myDoor.getAnswer()));
        });

        optionC.addActionListener(e -> {
            setCorrect(myDoor.getOptionC().equals(myDoor.getAnswer()));

        });

        optionD.addActionListener(e -> {
            setCorrect(myDoor.getOptionD().equals(myDoor.getAnswer()));
        });
    }



    public boolean getCorrect() {
        return correctNess;
    }

    public void setCorrect(boolean theCorrect) {
        correctNess = theCorrect;
    }
}
