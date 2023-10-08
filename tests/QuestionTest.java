package tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {

    private Question questionEmptyConstructor;
    private Question questionFullConstructor;

    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;

    @BeforeEach
    void setUp() {

        questionEmptyConstructor = new Question();

        question = "Question";
        optionA = "OptionA";
        optionB = "OptionB";
        optionC = "OptionC";
        optionD = "OptionD";
        answer = "Answer";

        questionFullConstructor = new Question(question, optionA, optionB, optionC, optionD, answer);

    }

    @Test
    void testGetQuestion() {
        assertEquals("Question", questionFullConstructor.getQuestion());
        assertEquals("OptionA", questionFullConstructor.getOptionA());
        assertEquals("OptionB", questionFullConstructor.getOptionB());
        assertEquals("OptionC", questionFullConstructor.getOptionC());
        assertEquals("OptionD", questionFullConstructor.getOptionD());
        assertEquals("Answer", questionFullConstructor.getAnswer());

    }
}
