package model;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a player in the trivia maze game.
 */
public class Player implements Serializable {

    @Serial
    private static final long serialVersionUID = 4511018697266812941L;

    private String playerName;
    private int score = 0;

    /**
     * Constructs a new Player object with the given player name.
     *
     * @param playerName the name of the player.
     */
    public Player(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Retrieves the name of the player.
     *
     * @return the player's name.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Retrieves the current score of the player.
     *
     * @return the player's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Retrieves the filename of the player's image.
     *
     * @return the filename of the player's image.
     */
    public String getImage() {
        return "Comic_Books.png";
    }

    /**
     * Sets the player's score.
     *
     * @param score the score to set.
     */
    public void setScore(int score) {
        this.score = score;
    }
}
