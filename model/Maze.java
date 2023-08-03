package model;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import model.Room;

public class Maze {

    private int rows = 5;
    private int columns = 5;

    private Room[][] myMaze = new Room[rows][columns];

    private int myX = 0;

    private int myY = 0;

    private boolean gameStatus = true;


    private final PropertyChangeSupport myPcs;
    //private List<PropertyChangeListener> myListeners;
    private static Maze myInstance = new Maze();



    public Maze (int theX, int theY) {
        myX = theX;
        myY = theY;

        myPcs = new PropertyChangeSupport(this);
        //myListeners = new ArrayList<>();
        startGame();
        roomSetup();

    }


    public static Maze getMyInstance() {
        return myInstance;
    }

    public Maze () {
        myPcs = new PropertyChangeSupport(this);
        startGame();
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
        int roomNumber = 1;
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
                myMaze[x][y] = new Room(northDoor, eastDoor, southDoor, westDoor, roomNumber);
                roomNumber++;
            }
        }
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

    public Room getCurrentRoom() {
        return myMaze[myX][myY];
    }

    public Room getRoom(final int theIndexX, final int theIndexY) {
        if (theIndexX >= myMaze.length || theIndexY >= myMaze.length) {
            throw new IllegalArgumentException("Index out of bounds: "
                    + theIndexX + " and " + theIndexY);
        }
        return myMaze[theIndexX][theIndexY];
    }


    public void endGame() {
        gameStatus = false;
    }

    public boolean gameFinished() {
        return myX == rows - 1 && myY == columns - 1;
    }

    public boolean display (Direction myDir) {


        if (myDir == Direction.NORTH && myY - 1 < 0) {
            if (myMaze[myX][myY].getDoor(Room.NORTH_INDEX) != null) { //because some rooms we didn't create a door. like in top left, we didnt create a north door, so it'll be null.
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
//            myMaze[myX][myY].getDoor(Room.SOUTH_INDEX).setVisible(false);
//            //System.out.println("SOUTH updating?");
//            return false;
        }

        if (myDir == Direction.WEST && myX - 1 < 0) {
            if (myMaze[myX][myY].getDoor(Room.WEST_INDEX) != null) {
                myMaze[myX][myY].getDoor(Room.WEST_INDEX).setVisible(false);
            }
            return false;
//            myMaze[myX][myY].getDoor(Room.WEST_INDEX).setVisible(false);
//            return false;
        }
        return true;
    }

    //TODO figure this out
    //TODO call getLock on null
    public void movePlayer(Direction myDir) {

        //System.out.println("Is this getting called?");

        if (myMaze[myX][myY] == null) {
            System.out.println("Why is this null?");
            return;
        }

        if (myDir == Direction.NORTH && myMaze[myX][myY].getDoor(Room.NORTH_INDEX) != null && !myMaze[myX][myY].getDoor(Room.NORTH_INDEX).getLock()) {
            setLocation(myX, myY - 1);
            System.out.println(myY);
            System.out.println("NORTH GETTING CALLED???");
        }

        if (myDir == Direction.EAST && myMaze[myX][myY].getDoor(Room.EAST_INDEX) != null && !myMaze[myX][myY].getDoor(Room.EAST_INDEX).getLock()) { //exclimation mark
            setLocation(myX + 1, myY);
        }

        if (myDir == Direction.SOUTH && myMaze[myX][myY].getDoor(Room.SOUTH_INDEX) != null && !myMaze[myX][myY].getDoor(Room.SOUTH_INDEX).getLock()) {
            setLocation(myX, myY + 1);
            //System.out.println("SOUTH getting called?");
        }

        if (myDir == Direction.WEST && myMaze[myX][myY].getDoor(Room.WEST_INDEX) != null && !myMaze[myX][myY].getDoor(Room.WEST_INDEX).getLock()) {
            setLocation(myX - 1, myY);
        }

        myPcs.firePropertyChange("ChangeX", myX, myX);
        myPcs.firePropertyChange("ChangeY", myY, myY);
    }

    public boolean canMoveDirection(final Direction theDir) {
        //Door localDoor =
        return true;
    }


    public boolean isPossible() {

        return isPossible(4,4);

    }

    private boolean isPossible (int goalX, int goalY) {




        return true;
    }

    public Room[][] getMaze() {
        return myMaze;
    }

    public void unlockingDoors(final Door theDoor) {
        if (theDoor.equals(getCurrentRoom().getDoor(Room.NORTH_INDEX))) {
            System.out.println("UL NORTH: " + getY());
            getRoom(getX(), getY() - 1).getDoor(Room.SOUTH_INDEX).unlock();
        }
        if (theDoor.equals(getCurrentRoom().getDoor(Room.SOUTH_INDEX))) {
            System.out.println("UL SOUTH: " + getY());
            getRoom(getX(), getY() + 1).getDoor(Room.NORTH_INDEX).unlock();
        }
        if (theDoor.equals(getCurrentRoom().getDoor(Room.WEST_INDEX))) {
            System.out.println("UL WEST: " + getX());
            getRoom(getX() - 1, getY()).getDoor(Room.EAST_INDEX).unlock();
        }
        if (theDoor.equals(getCurrentRoom().getDoor(Room.EAST_INDEX))) {
            System.out.println("UL EAST: " + getX());
            getRoom(getX() + 1, getY()).getDoor(Room.WEST_INDEX).unlock();
        }
    }

    public void unlockingDoors(final Direction theDir) {
        if (theDir == Direction.NORTH) {
            System.out.println("UL WEST: " + getX());
            getRoom(getX(), getY() - 1).getDoor(Room.SOUTH_INDEX).unlock();
        } else if (theDir == Direction.SOUTH) {
            System.out.println("UL SOUTH: " + getY());
            getRoom(getX(), getY() + 1).getDoor(Room.NORTH_INDEX).unlock();
        } else if (theDir == Direction.WEST) {
            System.out.println("UL WEST: " + getX());
            getRoom(getX() - 1, getY()).getDoor(Room.EAST_INDEX).unlock();
        } else if (theDir == Direction.EAST) {
            System.out.println("UL EAST: " + getX());
            getRoom(getX() + 1, getY()).getDoor(Room.WEST_INDEX).unlock();
        }
    }

    public void lockingDoors(final Door theDoor) {
        if (theDoor.equals(getCurrentRoom().getDoor(Room.NORTH_INDEX))) {
            System.out.println("NORTH: " + getY());
            getRoom(getX(), getY() - 1).getDoor(Room.SOUTH_INDEX).setForeverLocked(true);
        }
        if (theDoor.equals(getCurrentRoom().getDoor(Room.SOUTH_INDEX))) {
            System.out.println("SOUTH: " + getY());
            getRoom(getX(), getY() + 1).getDoor(Room.NORTH_INDEX).setForeverLocked(true);
        }
        if (theDoor.equals(getCurrentRoom().getDoor(Room.WEST_INDEX))) {
            System.out.println("WEST: " + getX());
            getRoom(getX() - 1, getY()).getDoor(Room.EAST_INDEX).setForeverLocked(true);
        }
        if (theDoor.equals(getCurrentRoom().getDoor(Room.EAST_INDEX))) {
            System.out.println("EAST: " + getX());
            getRoom(getX() + 1, getY()).getDoor(Room.WEST_INDEX).setForeverLocked(true);
        }
    }

    public void lockingDoors(final Direction theDir) {
        if (theDir == Direction.NORTH) {
            System.out.println("NORTH: " + getY());
            getRoom(getX(), getY() - 1).getDoor(Room.SOUTH_INDEX).setForeverLocked(true);
        } else if (theDir == Direction.SOUTH) {
            System.out.println("SOUTH: " + getY());
            getRoom(getX(), getY() + 1).getDoor(Room.NORTH_INDEX).setForeverLocked(true);
        } else if (theDir == Direction.WEST) {
            System.out.println("WEST: " + getX());
            getRoom(getX() - 1, getY()).getDoor(Room.EAST_INDEX).setForeverLocked(true);
        } else if (theDir == Direction.EAST) {
            System.out.println("EAST: " + getX());
            getRoom(getX() + 1, getY()).getDoor(Room.WEST_INDEX).setForeverLocked(true);
        }
    }
}
