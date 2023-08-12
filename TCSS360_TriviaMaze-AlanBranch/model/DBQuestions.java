package model;

import model.Question;


import java.io.Serial;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;


public class DBQuestions implements Serializable {
    @Serial
    private static final long serialVersionUID = -3434517038136172662L;
    private transient SQLiteDataSource dataSource;

    public DBQuestions() {
        dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:questions.db");
    }

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

        return null;
    }
}