package tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DBQuestionsTest {

    private DBQuestions dbq;

    private Question question;

    @BeforeEach
    void setUp() {
        dbq = new DBQuestions();
        question = new Question();
    }

    @Test
    void testGetRandomQuestion() {
        question = dbq.getRandomQuestion("Black Widow");

        assertNotEquals(null, question);
    }


}
