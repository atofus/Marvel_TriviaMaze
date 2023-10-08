package tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomTest {

    private Room myRoomEmptyConstructor;

    private Room myRoomFullConstructor;

    private Door myNorthDoor;
    private Door mySouthDoor;
    private Door myEastDoor;
    private Door myWestDoor;

    @BeforeEach
    void setUp() {
        myRoomEmptyConstructor = new Room();

        myNorthDoor = new Door();
        mySouthDoor = new Door();
        myEastDoor = new Door();
        myWestDoor = new Door();

        myRoomFullConstructor = new Room(myNorthDoor, myEastDoor, mySouthDoor, myWestDoor, 0);
    }

    @Test
    void testRoomNumber() {
        assertEquals(0, myRoomFullConstructor.getRoomNumber());

        //TODO add in initial room num to other constructor
    }

    @Test
    void testGetDoor() {
        assertEquals(myNorthDoor, myRoomFullConstructor.getDoor(0));
        assertEquals(myEastDoor, myRoomFullConstructor.getDoor(1));
        assertEquals(mySouthDoor, myRoomFullConstructor.getDoor(2));
        assertEquals(myWestDoor, myRoomFullConstructor.getDoor(3));
    }

    @Test
    void testVisited() {
        myRoomFullConstructor.setVisited(true);
        assertEquals(true, myRoomFullConstructor.getVisited());

        myRoomEmptyConstructor.setVisited(false);
        assertEquals(false, myRoomEmptyConstructor.getVisited());
    }


}
