package model;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a trivia question with options and correct answer.
 * @author Alan To
 * @author Jordan Williams
 * @author Aimee Tollett
 * @version Summer 2023
 */
public class Question implements Serializable {

    @Serial
    private static final long serialVersionUID = -8106800075033028256L;
    /** The question being asked. */
    private String myQuestion;
    /** Answer option A of the question. */
    private String myOptionA;
    /** Answer option B of the question. */
    private String myOptionB;
    /** Answer option C of the question. */
    private String myOptionC;
    /** Answer option D of the question. */
    private String myOptionD;
    /** The actual answer of the question. */
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
    public Question(final String theQuestion, final String theOption1,
                    final String theOption2, final String theOption3, final String theOption4,
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
