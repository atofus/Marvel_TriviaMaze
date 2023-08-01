package model;



public class Player {

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
