package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Maze {

    private int rows = 5;
    private int columns = 5;

    private Room[][] myMaze = new Room[rows][columns];

    private int myX = 0;

    private int myY = 0;

    private boolean gameStatus = true;

    private final PropertyChangeSupport myPcs;

    private static Maze myInstance = new Maze();
    private boolean questionAvailable;

    public Maze(int theX, int theY) {
        myX = theX;
        myY = theY;
        myPcs = new PropertyChangeSupport(this);
        startGame();
        roomSetup();
    }

    public Maze() {
        myPcs = new PropertyChangeSupport(this);
        startGame();
    }

    public static Maze getMyInstance() {
        return myInstance;
    }

    public void startGame() {
        gameStatus = true;
        setX(0);
        setY(0);
        roomSetup();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        myPcs.addPropertyChangeListener(listener);
    }

    public void roomSetup() {
        for (int x = 0; x < rows; ++x) {
            for (int y = 0; y < columns; ++y) {

                Door northDoor = null;
                Door southDoor = null;
                Door eastDoor = null;
                Door westDoor = null;

                if (x > 0) {
                    westDoor = new Door();
                    westDoor.setVisible(true);
                }

                if (y > 0) {
                    northDoor = new Door();
                    northDoor.setVisible(true);
                }

                if (x < rows - 1) {
                    eastDoor = new Door();
                    eastDoor.setVisible(true);
                }

                if (y < columns - 1) {
                    southDoor = new Door();
                    southDoor.setVisible(true);
                }

                myMaze[x][y] = new Room(northDoor, eastDoor, southDoor, westDoor);
            }
        }
    }

    public int getX() {
        return myX;
    }

    public int getY() {
        return myY;
    }

    public void setX(int theX) {
        myX = theX;
    }

    public void setY(int theY) {
        myY = theY;
    }

    public void setLocation(int theX, int theY) {
        myX = theX;
        myY = theY;
    }

    public Room getRoom() {
        return myMaze[myX][myY];
    }

    public void endGame() {
        gameStatus = false;
    }

    public boolean gameFinished() {
        return myX == rows - 1 && myY == columns - 1;
    }

    public boolean display(Direction myDir) {
        if (myDir == Direction.NORTH && myY - 1 < 0) {
            if (myMaze[myX][myY].getDoor(Room.NORTH_INDEX) != null) {
                myMaze[myX][myY].getDoor(Room.NORTH_INDEX).setVisible(false);
            }
            return false;
        }

        if (myDir == Direction.EAST && myX + 1 >= columns) {
            if (myMaze[myX][myY].getDoor(Room.EAST_INDEX) != null) {
                myMaze[myX][myY].getDoor(Room.EAST_INDEX).setVisible(false);
            }
            return false;
        }

        if (myDir == Direction.SOUTH && myY + 1 >= rows) {
            if (myMaze[myX][myY].getDoor(Room.SOUTH_INDEX) != null) {
                myMaze[myX][myY].getDoor(Room.SOUTH_INDEX).setVisible(false);
            }
            return false;
        }

        if (myDir == Direction.WEST && myX - 1 < 0) {
            if (myMaze[myX][myY].getDoor(Room.WEST_INDEX) != null) {
                myMaze[myX][myY].getDoor(Room.WEST_INDEX).setVisible(false);
            }
            return false;
        }
        return true;
    }

    public void movePlayer(Direction myDir) {
        Room currentRoom = myMaze[myX][myY];
        Door door = currentRoom.getDoor(myDir.ordinal());

        if (myMaze[myX][myY] == null) {
            System.out.println("Why is this null?");
            return;
        }

        if (myDir == Direction.NORTH && myMaze[myX][myY].getDoor(Room.NORTH_INDEX) != null && myMaze[myX][myY].getDoor(Room.NORTH_INDEX).getLock()) {
            setLocation(myX, myY - 1);
        }

        if (myDir == Direction.EAST && myMaze[myX][myY].getDoor(Room.EAST_INDEX) != null && myMaze[myX][myY].getDoor(Room.EAST_INDEX).getLock()) {
            setLocation(myX + 1, myY);
        }

        if (myDir == Direction.SOUTH && myMaze[myX][myY].getDoor(Room.SOUTH_INDEX) != null && myMaze[myX][myY].getDoor(Room.SOUTH_INDEX).getLock()) {
            setLocation(myX, myY + 1);
        }

        if (myDir == Direction.WEST && myMaze[myX][myY].getDoor(Room.WEST_INDEX) != null && myMaze[myX][myY].getDoor(Room.WEST_INDEX).getLock()) {
            setLocation(myX - 1, myY);
        }

        myPcs.firePropertyChange("ChangeX", myX, myX);
        myPcs.firePropertyChange("ChangeY", myY, myY);
    }

    public boolean isPossible() {
        return isPossible(4, 4);
    }

    private boolean isPossible(int goalX, int goalY) {
        // Implementation of maze solving algorithm (if needed)...
        return true;
    }

    public Room[][] getMaze() {
        return myMaze;
    }

    public boolean isQuestionAvailable() {
        Room currentRoom = myMaze[myY][myX];
        return currentRoom.hasQuestion();
    }
}
