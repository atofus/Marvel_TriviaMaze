package model;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;

/**
 * The DBQuestions class handles retrieving random questions from a database.
 * @author Alan To
 * @author Jordan Williams
 * @author Aimee Tollett
 * @version Summer 2023
 */
public class DBQuestions implements Serializable {
    @Serial
    private static final long serialVersionUID = -3434517038136172662L;

    /**Datasource for our SQLite questions. */
    private transient SQLiteDataSource myDataSource;

    /**
     * Constructs a DBQuestions object and initializes the database connection.
     */
    public DBQuestions() {
        myDataSource = new SQLiteDataSource();
        myDataSource.setUrl("jdbc:sqlite:questions.db");
    }

    /**
     * Retrieves a random question for a given character name.
     *
     * @param theCharName The name of the character associated with the question.
     * @return A random Question object or null if no question is found.
     */
    public Question getRandomQuestion(final String theCharName) {
        final String query = "SELECT * FROM questions WHERE Character = '"
                + theCharName + "' ORDER BY RANDOM() LIMIT 1";



        try (Connection conn = myDataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                final String question = rs.getString("QUESTION");
                final String optionA = rs.getString("OptionA");
                final String optionB = rs.getString("OptionB");
                final String optionC = rs.getString("OptionC");
                final String optionD = rs.getString("OptionD");
                final String answer = rs.getString("Answer");

                return new Question(question, optionA, optionB, optionC, optionD, answer);
            }

        } catch (final SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}