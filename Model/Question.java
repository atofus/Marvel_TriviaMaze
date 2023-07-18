package Model;

public class Question {

    private String myQuestion;

    private String myAnswer;

    public Question (String theQuestion, String theAnswer) {
        myQuestion = theQuestion;
        myAnswer = theAnswer;
    }

    public String getQuestion() {
        return myQuestion;
    }

    public String getAnswer() {
        return myAnswer;
    }

}
