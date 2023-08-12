package model;

import java.io.Serial;
import java.io.Serializable;

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

    public Room(Door theNorthDoor, Door theEastDoor, Door theSouthDoor, Door theWestDoor, int theRoomNumber) {
        myDoors[NORTH_INDEX] = theNorthDoor;
        myDoors[EAST_INDEX] = theEastDoor;
        myDoors[SOUTH_INDEX] = theSouthDoor;
        myDoors[WEST_INDEX] = theWestDoor;
        myRoomNumber = theRoomNumber;
    }

    public Room() {

    }

    public int getRoomNumber() {
        return myRoomNumber;
    }

    public Door getDoor(final int index) {
        return myDoors[index];
    }

    public void setVisited(boolean theVisited) {
        visited = theVisited;
    }

    public boolean getVisited() {
        return visited;
    }

}
