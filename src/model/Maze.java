package model;

public class Maze {

    private Room [][] myMaze;
    private int myX;
    private int myY;
    private boolean myGameActive;

    private static Maze myInstance = new Maze();


    public static Maze getMyInstance() {
        return myInstance;
    }

    public Maze() {
        startGame();
    }

    public void startGame() {
        setX(0);
        setY(0);
        myMaze = new Room[5][5];
        createRoom();
    }

    private void createRoom() {
        for (int y = 0; y < myMaze.length; y++) {
            for (int x = 0; x < myMaze[0].length; x++) {
                boolean west = false;
                boolean north = false;
                boolean south = false;
                boolean east = false;

                Door northDoor;
                Door eastDoor;

            }
        }
    }

    public int getX() {
        return myX;
    }

    public int getY() {
        return myY;
    }

    public void setX(final int theX) {
        myX = theX;
    }

    public void setY(final int theY) {
        myY = theY;
    }

//    public void setLocation(int theX, int theY) {
//        myX = theX;
//        myY = theY;
//    }

    public Room getRoom() {
        return myMaze[myX][myY];
    }

    public boolean isPossible() {
        return true;
    }

    public boolean endGame() {
        if (myX == myMaze.length - 1 && myY == myMaze.length - 1) {
            return true;
        }
        myGameActive = false;
        return false;
    }

    public Room[][] getMaze() {
        return myMaze;
    }

}
