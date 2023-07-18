package model;

public class Question {

    private String myQuestion;
    private String myOptionA;
    private String myOptionB;
    private String myOptionC;
    private String myOptionD;


    public Question(String theQuestion, String theOption1, String theOption2, String theOption3, String theOption4) {
        myQuestion = theQuestion;
        myOptionA = theOption1;
        myOptionB = theOption2;
        myOptionC = theOption3;
        myOptionD = theOption4;
    }

}
