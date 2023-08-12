package model;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a trivia question with options and correct answer.
 */
public class Question implements Serializable {

    @Serial
    private static final long serialVersionUID = -8106800075033028256L;

    private String myQuestion;
    private String myOptionA;
    private String myOptionB;
    private String myOptionC;
    private String myOptionD;
    private String myAnswer;

    /**
     * Constructs a new Question object with the provided details.
     *
     * @param theQuestion the question text.
     * @param theOption1  option A.
     * @param theOption2  option B.
     * @param theOption3  option C.
     * @param theOption4  option D.
     * @param theAnswer   the correct answer.
     */
    public Question(String theQuestion, String theOption1, String theOption2, String theOption3, String theOption4,
                    final String theAnswer) {
        myQuestion = theQuestion;
        myOptionA = theOption1;
        myOptionB = theOption2;
        myOptionC = theOption3;
        myOptionD = theOption4;
        myAnswer = theAnswer;
    }

    /**
     * Constructs an empty Question object.
     */
    public Question() {
    }

    /**
     * Retrieves option A of the question.
     *
     * @return option A.
     */
    public String getOptionA() {
        return myOptionA;
    }

    /**
     * Retrieves option B of the question.
     *
     * @return option B.
     */
    public String getOptionB() {
        return myOptionB;
    }

    /**
     * Retrieves option C of the question.
     *
     * @return option C.
     */
    public String getOptionC() {
        return myOptionC;
    }

    /**
     * Retrieves option D of the question.
     *
     * @return option D.
     */
    public String getOptionD() {
        return myOptionD;
    }

    /**
     * Retrieves the correct answer to the question.
     *
     * @return the correct answer.
     */
    public String getAnswer() {
        return myAnswer;
    }

    /**
     * Retrieves the question text.
     *
     * @return the question text.
     */
    public String getQuestion() {
        return myQuestion;
    }
}
