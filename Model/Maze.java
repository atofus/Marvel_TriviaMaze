package Model;

public class Maze {

    private int rows = 5;
    private int columns = 5;

    private Room[][] myMaze = new Room[rows][columns];

    private int myX = 0;

    private int myY = 0;

    private boolean gameStatus = true;

    public Maze (int theX, int theY) {
        myX = theX;
        myY = theY;
    }

    public int getX () {
        return myX;
    }

    public int getY () {
        return myY;
    }

    public void setX (int theX) {
        myX = theX;
    }

    public void setY (int theY) {
        myY = theY;
    }

    public void setLocation (int theX, int theY) {
        myX = theX;
        myY = theY;
    }

    public Room getRoom() {
        return myMaze[myX][myY];
    }

    public void startGame() {
        gameStatus = true;
    }

    public void endGame() {
        gameStatus = false;
    }


    public boolean display (Direction myDir) {



        if (myDir == Direction.NORTH && myY - 1 < 0) {
            myMaze[myX][myY].getDoor(Room.NORTH_INDEX).setVisible(false);
            return false;
        }

        if (myDir == Direction.EAST && myX + 1 >= columns) {
            myMaze[myX][myY].getDoor(Room.EAST_INDEX).setVisible(false);
            return false;
        }

        if (myDir == Direction.SOUTH && myY + 1 >= rows) {
            myMaze[myX][myY].getDoor(Room.SOUTH_INDEX).setVisible(false);
            return false;
        }

        if (myDir == Direction.WEST && myX - 1 < 0) {
            myMaze[myX][myY].getDoor(Room.WEST_INDEX).setVisible(false);
            return false;
        }

        return true;
    }

    public void movePlayer(Direction myDir) {
        if (myDir == Direction.NORTH && myMaze[myX][myY].getDoor(Room.NORTH_INDEX).getLock()) {
            setLocation(myX, myY - 1);
        }

        if (myDir == Direction.EAST && myMaze[myX][myY].getDoor(Room.EAST_INDEX).getLock()) {
            setLocation(myX + 1, myY);
        }

        if (myDir == Direction.SOUTH && myMaze[myX][myY].getDoor(Room.SOUTH_INDEX).getLock()) {
            setLocation(myX, myY + 1);
        }

        if (myDir == Direction.WEST && myMaze[myX][myY].getDoor(Room.WEST_INDEX).getLock()) {
            setLocation(myX - 1, myY);
        }
    }


}
