package model;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a room in the maze with doors and visitation status.
 */
public class Room implements Serializable {

    @Serial
    private static final long serialVersionUID = -8034325652014162988L;

    private Door[] myDoors = new Door[4];
    private boolean visited = false;

    public static final int NORTH_INDEX = 0;
    public static final int EAST_INDEX = 1;
    public static final int SOUTH_INDEX = 2;
    public static final int WEST_INDEX = 3;

    /** Used to identify each room and make each room exclusive. */
    private int myRoomNumber;

    /**
     * Constructs a new Room with provided doors and room number.
     *
     * @param theNorthDoor The door to the north of the room.
     * @param theEastDoor  The door to the east of the room.
     * @param theSouthDoor The door to the south of the room.
     * @param theWestDoor  The door to the west of the room.
     * @param theRoomNumber The room number.
     */
    public Room(Door theNorthDoor, Door theEastDoor, Door theSouthDoor, Door theWestDoor, int theRoomNumber) {
        myDoors[NORTH_INDEX] = theNorthDoor;
        myDoors[EAST_INDEX] = theEastDoor;
        myDoors[SOUTH_INDEX] = theSouthDoor;
        myDoors[WEST_INDEX] = theWestDoor;
        myRoomNumber = theRoomNumber;
    }

    /**
     * Constructs an empty Room.
     */
    public Room() {
    }

    /**
     * Retrieves the room number.
     *
     * @return The room number.
     */
    public int getRoomNumber() {
        return myRoomNumber;
    }

    /**
     * Retrieves the door at the specified index.
     *
     * @param index The index of the door (NORTH_INDEX, EAST_INDEX, SOUTH_INDEX, WEST_INDEX).
     * @return The door at the specified index.
     */
    public Door getDoor(final int index) {
        return myDoors[index];
    }

    /**
     * Sets the visitation status of the room.
     *
     * @param theVisited The visitation status to set.
     */
    public void setVisited(boolean theVisited) {
        visited = theVisited;
    }

    /**
     * Retrieves the visitation status of the room.
     *
     * @return The visitation status of the room.
     */
    public boolean getVisited() {
        return visited;
    }
}
