package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;
import java.util.Random;

public class Maze implements Serializable {

    @Serial
    private static final long serialVersionUID = -2641303058682284534L;

    /** Data used for singleton. */
    private static Maze myInstance = new Maze();

    /** Amount of rows of the map. */
    private final int myRows = 5;
    /** Amount of columns on the map. */
    private final int myColumns = 5;
    /** The superhero character name that the user chose. */
    private String myCharName;
    /** The superhero character image. */
    private String myCharImage;
    /** Our maze with the specified rows and columns with a room in each element. */
    private Room[][] myMaze = new Room[myRows][myColumns];
    /** The X position of the user. */
    private int myX;
    /** The Y position of the user. */
    private int myY;
    /** Used to check if the game is still on or not. */
    private boolean myGameStatus = true;
    /** Room number that the user is in at the moment. */
    private int myRoomNumber = 1;
    /** The score of that the user earns during game. */
    private int myScoreValue;
    /** Potions are used to increase time limit on questions. */
    private int myPotions = 1;
    /** Integer that describes the difficulty level of the game. */
    private int myDiffLevel;
    /** The question number that the user is on. */
    private int myQuestionNumber;

    /** Property change support to see if something has changed in variables. */
    private final PropertyChangeSupport myPcs;


    /**
     * Constructor that starts the game.
     */
    public Maze() {
        myPcs = new PropertyChangeSupport(this);
        startGame();
    }

    /**
     * Returns the singleton instance of the Maze class.
     *
     * @return The Maze instance.
     */
    public static Maze getMyInstance() {
        return myInstance;
    }

    /** Gets the room number that the player is in at the moment.
     *
     * @return the room number the player is in.
     */
    public int getRoomNumber() {
        return myRoomNumber;
    }

    /**
     * Used to get the difficulty level the player chose.
     * @return the difficulty level.
     */
    public int getDiffLevel() {
        return myDiffLevel;
    }

    /**
     * Used to set the difficulty level that the player has chosen.
     * @param theDiffLevel the set difficulty level the player chose.
     */
    public void setDiffLevel(final int theDiffLevel) {
        myDiffLevel = theDiffLevel;
    }

    /**
     * Initializes the game's state and maze.
     */
    public void startGame() {
        myGameStatus = true;
        setQuestionNumber(1);
        setScore(0);
        setX(0);
        setY(0);
        roomSetup();
    }

    /**
     * See if the game status is still true.
     * @return true if the game is still active, false otherwise.
     */
    public boolean getGameStatus() {
        return myGameStatus;
    }

    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
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
        final int rand = new Random().nextInt(10);
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
        for (int x = 0; x < myRows; ++x) {
            for (int y = 0; y < myColumns; ++y) {

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

                if (x < myRows - 1) {
                    eastDoor = new Door();
                    eastDoor.setVisible(true);
                }

                if (y < myColumns - 1) {
                    southDoor = new Door();
                    southDoor.setVisible(true);
                }
                myMaze[x][y] = new Room(northDoor, eastDoor,
                        southDoor, westDoor, myRoomNumber);
                myRoomNumber++;
            }
        }
    }

    /**
     * Gets the current X-coordinate of the player's position.
     *
     * @return The X-coordinate of the player's position.
     */
    public int getX() {
        return myX;
    }

    /**
     * Gets the current Y-coordinate of the player's position.
     *
     * @return The Y-coordinate of the player's position.
     */
    public int getY() {
        return myY;
    }

    /**
     * Sets the X-coordinate of the player's position.
     *
     * @param theX The X-coordinate to set.
     */
    public void setX(final int theX) {
        myX = theX;
    }

    /**
     * Sets the Y-coordinate of the player's position.
     *
     * @param theY The Y-coordinate to set.
     */
    public void setY(final int theY) {
        myY = theY;
    }

    /**
     * Sets the location of the player's position.
     *
     * @param theX The X-coordinate to set.
     * @param theY The Y-coordinate to set.
     */
    public void setLocation(final int theX, final int theY) {
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
        if (myX == myRows - 1 && myY == myColumns - 1) {
            endGame();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Displays the room in the specified direction, updating door visibility.
     *
     * @param theDir The direction in which to display the room.
     * @return True if the room can be displayed, false otherwise.
     */
    public boolean display(final Direction theDir) {
        if (theDir == Direction.NORTH && myY - 1 < 0) {
            //because some rooms we didn't create a door. like in top left,
            // we didn't create a north door, so it'll be null.
            if (myMaze[myX][myY].getDoor(Room.NORTH_INDEX) != null) {
                myMaze[myX][myY].getDoor(Room.NORTH_INDEX).setVisible(false);
            }
            return false;
        }

        if (theDir == Direction.EAST && myX + 1 >= myColumns) {
            if (myMaze[myX][myY].getDoor(Room.EAST_INDEX) != null) {
                myMaze[myX][myY].getDoor(Room.EAST_INDEX).setVisible(false);
            }
            return false;
        }

        if (theDir == Direction.SOUTH && myY + 1 >= myRows) {
            if (myMaze[myX][myY].getDoor(Room.SOUTH_INDEX) != null) {
                myMaze[myX][myY].getDoor(Room.SOUTH_INDEX).setVisible(false);
            }
            return false;
//            myMaze[myX][myY].getDoor(Room.SOUTH_INDEX).setVisible(false);
//            //System.out.println("SOUTH updating?");
//            return false;
        }

        if (theDir == Direction.WEST && myX - 1 < 0) {
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
     * @param theDir The direction in which to move the player.
     */
    public void movePlayer(final Direction theDir) {

        //System.out.println("Is this getting called?");

        if (myMaze[myX][myY] == null) {
            System.out.println("Why is this null?");
            return;
        }

        if (theDir == Direction.NORTH && myMaze[myX][myY].getDoor(Room.NORTH_INDEX) != null
                && !myMaze[myX][myY].getDoor(Room.NORTH_INDEX).getLock()) {
            setLocation(myX, myY - 1);
            System.out.println(myY);
            System.out.println("NORTH GETTING CALLED???");
        }

        if (theDir == Direction.EAST && myMaze[myX][myY].getDoor(Room.EAST_INDEX) != null
                && !myMaze[myX][myY].getDoor(Room.EAST_INDEX).getLock()) { //exclimation mark
            setLocation(myX + 1, myY);
        }

        if (theDir == Direction.SOUTH && myMaze[myX][myY].getDoor(Room.SOUTH_INDEX) != null
                && !myMaze[myX][myY].getDoor(Room.SOUTH_INDEX).getLock()) {
            setLocation(myX, myY + 1);
            System.out.println("SOUTH getting called?");
        }

        if (theDir == Direction.WEST && myMaze[myX][myY].getDoor(Room.WEST_INDEX) != null
                && !myMaze[myX][myY].getDoor(Room.WEST_INDEX).getLock()) {
            setLocation(myX - 1, myY);
        }

        myPcs.firePropertyChange("ChangeX", myX, myX);
        myPcs.firePropertyChange("ChangeY", myY, myY);
    }

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

        for (int x = 0; x < myRows; ++x) {
            for (int y = 0; y < myColumns; ++y) {
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
    private boolean isPossibleHelper(final int theX, final int theY) {
        if (!myMaze[theX][theY].getVisited()) {

            myMaze[theX][theY].setVisited(true);

            if (theX == myMaze.length - 1 && theY == myMaze.length - 1) {
                return true;
            }

            if (myMaze[theX][theY].getDoor(Room.NORTH_INDEX) != null
                    && !myMaze[theX][theY].getDoor(Room.NORTH_INDEX).getForeverLocked()) {
                final boolean northCheck = isPossibleHelper(theX, theY - 1);
                if (northCheck) {
                    return true;
                }
            }

            if (myMaze[theX][theY].getDoor(Room.SOUTH_INDEX) != null
                    && !myMaze[theX][theY].getDoor(Room.SOUTH_INDEX).getForeverLocked()) {
                final boolean southCheck = isPossibleHelper(theX, theY + 1);
                if (southCheck) {
                    return true;
                }
            }

            if (myMaze[theX][theY].getDoor(Room.EAST_INDEX) != null
                    && !myMaze[theX][theY].getDoor(Room.EAST_INDEX).getForeverLocked()) {
                final boolean eastCheck = isPossibleHelper(theX + 1, theY);
                if (eastCheck) {
                    return true;
                }
            }

            if (myMaze[theX][theY].getDoor(Room.WEST_INDEX) != null
                    && !myMaze[theX][theY].getDoor(Room.WEST_INDEX).getForeverLocked()) {
                final boolean westCheck = isPossibleHelper(theX - 1, theY);
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
            System.out.println("UL NORTH: " + getX());
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
    public void setCharName(final String theCharName) {
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
    public void setCharImage(final String theCharImage) {
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

    /**
     * Sets the number of questions the user has asked.
     * @param theQuestionNumber Number of questions user has asked.
     */
    public void setQuestionNumber(final int theQuestionNumber) {
        myQuestionNumber = theQuestionNumber;
    }

    /**
     * Retrieves the number of questions the user has asked.
     *
     * @return the number of question user has asked.
     */
    public int getQuestionNumber() {
        return myQuestionNumber;
    }



}
