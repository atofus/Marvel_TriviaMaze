package model;

import model.Question;


import java.io.Serial;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;

/**
 * The DBQuestions class handles retrieving random questions from a database.
 */
public class DBQuestions implements Serializable {
    @Serial
    private static final long serialVersionUID = -3434517038136172662L;
    private transient SQLiteDataSource dataSource;


    /**
     * Constructs a DBQuestions object and initializes the database connection.
     */

    public DBQuestions() {
        dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:questions.db");
    }

    /**
     * Retrieves a random question for a given character name.
     *
     * @param charName The name of the character associated with the question.
     * @return A random Question object or null if no question is found.
     */

    public Question getRandomQuestion(String charName) {
        String query = "SELECT * FROM questions WHERE Character = '" + charName + "' ORDER BY RANDOM() LIMIT 1";



        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                String question = rs.getString("QUESTION");
                String optionA = rs.getString("OptionA");
                String optionB = rs.getString("OptionB");
                String optionC = rs.getString("OptionC");
                String optionD = rs.getString("OptionD");
                String answer = rs.getString("Answer");

                return new Question(question, optionA, optionB, optionC, optionD, answer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if no question is found
    }
}