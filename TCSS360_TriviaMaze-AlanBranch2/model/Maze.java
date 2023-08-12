package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;
import java.util.Random;


/**
 * Represents the maze in the game.
 */

public class Maze implements Serializable {

    @Serial
    private static final long serialVersionUID = -2641303058682284534L;

    private final int rows = 5;
    private final int columns = 5;
    private String myCharName;
    private String myCharImage;

    private Room[][] myMaze = new Room[rows][columns];

    private int myX = 0;

    private int myY = 0;

    private boolean myGameStatus = true;
    private int myRoomNumber = 1;
    private int myScoreValue;

    private int myPotions = 1;


    private final PropertyChangeSupport myPcs;
    //private List<PropertyChangeListener> myListeners;

    /**
     * Returns the singleton instance of the Maze class.
     *
     * @return The Maze instance.
     */

    private static Maze myInstance = new Maze();


    public static Maze getMyInstance() {
        return myInstance;
    }

    public Maze () {
        myPcs = new PropertyChangeSupport(this);
        startGame();
    }

    /**
     * Initializes the game's state and maze.
     */

    public void startGame() {
        myGameStatus = true;
        setScore(0);
        setX(0);
        setY(0);
        roomSetup();
    }

    /**
     * Adds a property change listener to the maze.
     *
     * @param listener The property change listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        myPcs.addPropertyChangeListener(listener);
    }

    /**
     * Sets the number of potions the player has.
     *
     * @param thePotions The number of potions to set.
     */
    public void setPotions(final int thePotions) {
        myPotions = thePotions;
    }

    /**
     * Gets the number of potions the player has.
     *
     * @return The number of potions the player has.
     */
    public int getPotions() {
        return myPotions;
    }

    /**
     * Increases the number of potions the player has randomly.
     * The player has a 1 in 10 chance of gaining a potion.
     */
    public void gainPotions() {
        int rand = new Random().nextInt(10);
        if (rand == 1) {
            myPotions++;
        }
    }

    /**
     * Sets the player's score.
     *
     * @param theScore The score to set.
     */
    public void setScore(final int theScore) {
        myScoreValue = theScore;
    }

    /**
     * Gets the player's score.
     *
     * @return The player's score.
     */
    public int getScore() {
        return myScoreValue;
    }

    /**
     * Sets up the rooms in the maze with doors.
     * Each room is initialized with doors in appropriate directions.
     */
    public void roomSetup() {
        // ... (rest of the method)
    }

    /**
     * Gets the current X-coordinate of the player's position.
     *
     * @return The X-coordinate of the player's position.
     */
    public int getX () {
        return myX;
    }

    /**
     * Gets the current Y-coordinate of the player's position.
     *
     * @return The Y-coordinate of the player's position.
     */
    public int getY () {
        return myY;
    }

    /**
     * Sets the X-coordinate of the player's position.
     *
     * @param theX The X-coordinate to set.
     */
    public void setX (int theX) {
        myX = theX;
    }

    /**
     * Sets the Y-coordinate of the player's position.
     *
     * @param theY The Y-coordinate to set.
     */
    public void setY (int theY) {
        myY = theY;
    }

    /**
     * Sets the location of the player's position.
     *
     * @param theX The X-coordinate to set.
     * @param theY The Y-coordinate to set.
     */
    public void setLocation (int theX, int theY) {
        myX = theX;
        myY = theY;
    }

    /**
     * Retrieves the current room at the player's position.
     *
     * @return The current room at the player's position.
     */
    public Room getCurrentRoom() {
        return myMaze[myX][myY];
    }

    /**
     * Retrieves the room at the specified indices.
     *
     * @param theIndexX The X-index of the desired room.
     * @param theIndexY The Y-index of the desired room.
     * @return The room at the specified indices.
     * @throws IllegalArgumentException If the indices are out of bounds.
     */
    public Room getRoom(final int theIndexX, final int theIndexY) {
        if (theIndexX >= myMaze.length || theIndexY >= myMaze.length) {
            throw new IllegalArgumentException("Index out of bounds: "
                    + theIndexX + " and " + theIndexY);
        }
        return myMaze[theIndexX][theIndexY];
    }

    /**
     * Ends the game by changing the game status to false.
     */
    public void endGame() {
        myGameStatus = false;
    }

    /**
     * Checks if the game has been finished by reaching the last room.
     *
     * @return True if the game is finished, false otherwise.
     */
    public boolean gameFinished() {
        if (myX == rows - 1 && myY == columns - 1) {
            endGame();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Displays the room in the specified direction, updating door visibility.
     *
     * @param myDir The direction in which to display the room.
     * @return True if the room can be displayed, false otherwise.
     */

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


    /**
     * Moves the player in the specified direction, if the door is unlocked.
     * Fires property change events for X and Y coordinates.
     *
     * @param myDir The direction in which to move the player.
     */

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

    /**
     * Checks if the player can move in the specified direction.
     * @return True if the player can move in the specified direction, false otherwise.
     */

    public boolean canMoveDirection(final Direction theDir) {
        //Door localDoor =
        return true;
    }


    /**
     * Checks if it's possible to reach the end of the maze from the current position.
     *
     * @return True if it's possible to reach the end, false otherwise.
     */

    public boolean isPossible() {

        for (int x = 0; x < rows; ++x) {
            for (int y = 0; y < columns; ++y) {
                myMaze[x][y].setVisited(false);
            }
        }


        return isPossibleHelper(myX, myY);



    }

    /**
     * Recursive helper method to determine if it's possible to reach the end of the maze.
     *
     * @param theX The X-coordinate of the current room.
     * @param theY The Y-coordinate of the current room.
     * @return True if it's possible to reach the end from the current room, false otherwise.
     */

    private boolean isPossibleHelper (int theX, int theY) {
        if (!myMaze[theX][theY].getVisited()) {

            myMaze[theX][theY].setVisited(true);

            if (theX == 4 && theY == 4) {
                return true;
            }

            if (myMaze[theX][theY].getDoor(Room.NORTH_INDEX) != null
                    && !myMaze[theX][theY].getDoor(Room.NORTH_INDEX).getForeverLocked()) {
                boolean northCheck = isPossibleHelper(theX, theY - 1);
                if (northCheck) {
                    return true;
                }
            }

            if (myMaze[theX][theY].getDoor(Room.SOUTH_INDEX) != null
                    && !myMaze[theX][theY].getDoor(Room.SOUTH_INDEX).getForeverLocked()) {
                boolean southCheck = isPossibleHelper(theX, theY + 1);
                if (southCheck) {
                    return true;
                }
            }

            if (myMaze[theX][theY].getDoor(Room.EAST_INDEX) != null
                    && !myMaze[theX][theY].getDoor(Room.EAST_INDEX).getForeverLocked()) {
                boolean eastCheck = isPossibleHelper(theX + 1, theY);
                if (eastCheck) {
                    return true;
                }
            }

            if (myMaze[theX][theY].getDoor(Room.WEST_INDEX) != null
                    && !myMaze[theX][theY].getDoor(Room.WEST_INDEX).getForeverLocked()) {
                boolean westCheck = isPossibleHelper(theX - 1, theY);
                if (westCheck) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Retrieves the maze's 2D array representation.
     *
     * @return The maze's 2D array of rooms.
     */

    public Room[][] getMaze() {
        return myMaze;
    }

    /**
     * Unlocks doors in the specified direction.
     *
     * @param theDir The direction in which to unlock doors.
     */

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
    /**
     * Locks doors in the specified direction permanently.
     *
     * @param theDir The direction in which to lock doors.
     */
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

    /**
     * Sets the character's name.
     *
     * @param theCharName The character's name.
     */

    public void setCharName(String theCharName) {
        myCharName = theCharName;
    }

    /**
     * Retrieves the character's name.
     *
     * @return The character's name.
     */

    public String getCharName() {
        return myCharName;
    }

    /**
     * Sets the character's image.
     *
     * @param theCharImage The path to the character's image.
     */

    public void setCharImage(String theCharImage) {
        myCharImage = theCharImage;
    }

    /**
     * Retrieves the path to the character's image.
     *
     * @return The path to the character's image.
     */

    public String getCharImage() {
        return myCharImage;
    }



}
