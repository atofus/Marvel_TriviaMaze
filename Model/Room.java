package Model;

public class Room {

    private Door[] myDoors = new Door[4];

    public static final int NORTH_INDEX = 0;
    public static final int EAST_INDEX = 1;
    public static final int SOUTH_INDEX = 2;
    public static final int WEST_INDEX = 3;

    public Room(Door theNorthDoor, Door theEastDoor, Door theSouthDoor, Door theWestDoor) {
        myDoors[NORTH_INDEX] = theNorthDoor;
        myDoors[EAST_INDEX] = theEastDoor;
        myDoors[SOUTH_INDEX] = theSouthDoor;
        myDoors[WEST_INDEX] = theWestDoor;
    }

    public Door getDoor(int index) {
        return myDoors[index];
    }

}
