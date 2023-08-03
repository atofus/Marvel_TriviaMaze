package model;

import view.DBQuestions;
import view.Display;

public class Door {

    private boolean myLock;
    private boolean myForeverLocked;

    private boolean visible;
    private Question myQuestion;

    private DBQuestions dbq = new DBQuestions();


    //private Question myQuestion = new Question("Captain America's Name?", "Steve Rogers");

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

    public boolean getLock() {
        return myLock;
    }

    public void setLock (boolean locked) {
        myLock = locked;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean theVis) {
        visible = theVis;
    }

    public void unlock () {
        myLock = false;
    }

    public boolean getForeverLocked() {
        return myForeverLocked;
    }

    public String getQuestion() {
        return myQuestion.getQuestion();
    }

    public String getOptionA() {
        return myQuestion.getOptionA();
    }

    public String getOptionB() {
        return myQuestion.getOptionB();
    }

    public String getOptionC() {
        return myQuestion.getOptionC();
    }

    public String getOptionD() {
        return myQuestion.getOptionD();
    }

    public String getAnswer() {
        return myQuestion.getAnswer();
    }

    public void setForeverLocked(final boolean locked) {
        myForeverLocked = locked;
    }

}
