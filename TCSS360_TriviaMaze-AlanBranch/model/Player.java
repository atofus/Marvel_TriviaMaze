package model;


import java.io.Serial;
import java.io.Serializable;

public class Player implements Serializable {

    @Serial
    private static final long serialVersionUID = 4511018697266812941L;
    private String playerName;

    private int score = 0;

    public Player (String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore () {
        return score;
    }

    public String getImage() {
        return "Comic_Books.png";
    }

    public void setScore (int score) {
        this.score = score;
    }
}
