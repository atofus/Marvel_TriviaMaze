package model;


import java.io.Serializable;

public class Player implements Serializable {

    private static final long serialVersionUID = -6470090944414208496L;

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
