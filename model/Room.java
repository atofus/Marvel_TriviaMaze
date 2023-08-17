package model;

import java.io.Serial;
import java.io.Serializable;

public class Room implements Serializable {

    /** The north door in the room represented by index 0. */
    public static final int NORTH_INDEX = 0;
    /** The east door in the room represented by index 1. */
    public static final int EAST_INDEX = 1;
    /** The south door in the room represented by index 2. */
    public static final int SOUTH_INDEX = 2;
    /** The west door in the room represented by index 3. */
    public static final int WEST_INDEX = 3;
    @Serial
    private static final long serialVersionUID = -8034325652014162988L;
    /** The amount of doors around the room. */
    private final int myDoorAmount = 4;
    /** Door array that creates the amount of doors in a room. */
    private Door[] myDoors = new Door[myDoorAmount];

    /** Check if a room has been visited. */
    private boolean myVisited;

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
    public Room(final Door theNorthDoor, final Door theEastDoor,
                final Door theSouthDoor, final Door theWestDoor, final int theRoomNumber) {
        myDoors[NORTH_INDEX] = theNorthDoor;
        myDoors[EAST_INDEX] = theEastDoor;
        myDoors[SOUTH_INDEX] = theSouthDoor;
        myDoors[WEST_INDEX] = theWestDoor;
        myRoomNumber = theRoomNumber;
        myVisited = false;
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
     * @param theIndex The index of the door (NORTH_INDEX,
     * EAST_INDEX, SOUTH_INDEX, WEST_INDEX).
     * @return The door at the specified index.
     */
    public Door getDoor(final int theIndex) {
        return myDoors[theIndex];
    }

    /**
     * Sets the visitation status of the room.
     *
     * @param theVisited The visitation status to set.
     */
    public void setVisited(final boolean theVisited) {
        myVisited = theVisited;
    }

    /**
     * Retrieves the visitation status of the room.
     *
     * @return The visitation status of the room.
     */
    public boolean getVisited() {
        return myVisited;
    }

}
