package model;

public class Question {

    private String myQuestion;

    private String myAnswer;
    private String myOptionA;
    private String myOptionB;
    private String myOptionC;
    private String myOptionD;

    public Question(String theQuestion, String theOption1, String theOption2, String theOption3, String theOption4,
                    final String theAnswer) {
        myQuestion = theQuestion;
        myOptionA = theOption1;
        myOptionB = theOption2;
        myOptionC = theOption3;
        myOptionD = theOption4;
        myAnswer = theAnswer;
    }

    public String getOptionA() {
        return myOptionA;
    }

    public String getOptionB() {
        return myOptionB;
    }

    public String getOptionC() {
        return myOptionC;
    }

    public String getOptionD() {
        return myOptionD;
    }

    public String getQuestion() {
        return myQuestion;
    }

    public String getAnswer() {
        return myAnswer;
    }

}
