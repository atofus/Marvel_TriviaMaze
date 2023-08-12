package model;

import model.DBQuestions;
import view.Display;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a door within the game maze.
 */
public class Door implements Serializable {

    @Serial
    private static final long serialVersionUID = -2328691798472437421L;
    private boolean myLock;
    private boolean myForeverLocked;

    private boolean visible;
    private Question myQuestion;

    private DBQuestions dbq = new DBQuestions();


    //private Question myQuestion = new Question("Captain America's Name?", "Steve Rogers");


    /**
     * Constructs a new Door object.
     * Initializes the door's lock status, forever locked status, visibility,
     * and associated question from the database.
     */
    public Door () {
        myLock = true;
        myForeverLocked = false;
        visible = true; //visible is all about seeing the door at edges or corners of the map.
       // myQuestion = SQLiteClass.generateQuestion();

        //will be a question to ask for all doors.
//        myQuestion = new Question("What is Spiderman real name?", "Frank Clark",
//                "Stevie Wonder", "Steve Rogers", "Peter Parker", "Peter Parker");

        myQuestion = dbq.getRandomQuestion(Display.charName);
        //System.out.println(myQuestion.getQuestion());
    }

    /**
     * Checks if the door is currently locked.
     *
     * @return true if the door is locked, false otherwise.
     */
    public boolean getLock() {
        return myLock;
    }

    /**
     * Sets the lock status of the door.
     *
     * @param locked true to lock the door, false to unlock.
     */

    public void setLock (boolean locked) {
        myLock = locked;
    }

    /**
     * Checks if the door is visible.
     *
     * @return true if the door is visible, false otherwise.
     */

    public boolean getVisible() {
        return visible;
    }

    /**
     * Sets the visibility status of the door.
     *
     * @param theVis true to make the door visible, false to hide.
     */

    public void setVisible(boolean theVis) {
        visible = theVis;
    }

    /**
     * Unlocks the door.
     */

    public void unlock () {
        myLock = false;
    }

    /**
     * Checks if the door is forever locked.
     *
     * @return true if the door is forever locked, false otherwise.
     */

    public boolean getForeverLocked() {
        return myForeverLocked;
    }

    /**
     * Retrieves the question associated with the door.
     *
     * @return the question.
     */

    public String getQuestion() {
        return myQuestion.getQuestion();
    }
    /**
     * Retrieves option A of the question associated with the door.
     *
     * @return option A.
     */
    public String getOptionA() {
        return myQuestion.getOptionA();
    }

    /**
     * Retrieves option B of the question associated with the door.
     *
     * @return option B.
     */
    public String getOptionB() {
        return myQuestion.getOptionB();
    }

    /**
     * Retrieves option C of the question associated with the door.
     *
     * @return option C.
     */
    public String getOptionC() {
        return myQuestion.getOptionC();
    }

    /**
     * Retrieves option D of the question associated with the door.
     *
     * @return option D.
     */
    public String getOptionD() {
        return myQuestion.getOptionD();
    }

    /**
     * Retrieves the correct answer of the question associated with the door.
     *
     * @return the correct answer.
     */
    public String getAnswer() {
        return myQuestion.getAnswer();
    }

    /**
     * Sets the forever locked status of the door.
     *
     * @param locked true to set the door as forever locked, false to unlock.
     */
    public void setForeverLocked(boolean locked) {
        myForeverLocked = locked;
    }
}
