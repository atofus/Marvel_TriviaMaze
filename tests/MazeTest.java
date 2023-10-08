package tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazeTest {

    private Maze myMaze;

    @BeforeEach
    void setUp() throws Exception {
        myMaze = new Maze();
    }

    @Test
    void testStartGame() {
        myMaze.startGame();

        assertEquals(true, myMaze.getGameStatus());
        assertEquals(0, myMaze.getScore());
        assertEquals(0, myMaze.getX());
        assertEquals(0, myMaze.getY());

    }

    @Test
    void testPotions() {
        myMaze.setPotions(10);
        assertEquals(10, myMaze.getPotions());
    }

    @Test
    void testGainPotions1() {
        myMaze.gainPotions();
        myMaze.gainPotions();
        myMaze.gainPotions();
        myMaze.gainPotions();
        myMaze.gainPotions();

        assertTrue(myMaze.getPotions() >= 1);
    }

    @Test
    void testGainPotions2() {
        myMaze.gainPotions();
        assertTrue(myMaze.getPotions() == 1 || myMaze.getPotions() == 2);
    }



    @Test
    void testScore() {
        myMaze.setScore(100);
        assertEquals(100, myMaze.getScore());
    }

    @Test
    void testRoomSetup() {
        //TODO add more in
        assertEquals(null, myMaze.getRoom(0,0).getDoor(Room.NORTH_INDEX));
        assertEquals(true, myMaze.getRoom(0,0).getDoor(Room.SOUTH_INDEX).getVisible());
        assertEquals(null, myMaze.getRoom(0,0).getDoor(Room.WEST_INDEX));
        assertEquals(true, myMaze.getRoom(0,0).getDoor(Room.EAST_INDEX).getVisible());

        myMaze.setLocation(3,3);
        assertEquals(true, myMaze.getRoom(3,3).getDoor(Room.NORTH_INDEX).getVisible());
        assertEquals(true, myMaze.getRoom(3,3).getDoor(Room.SOUTH_INDEX).getVisible());
        assertEquals(true, myMaze.getRoom(3,3).getDoor(Room.WEST_INDEX).getVisible());
        assertEquals(true, myMaze.getRoom(3,3).getDoor(Room.EAST_INDEX).getVisible());

        myMaze.setLocation(4, 4);
        assertEquals(null, myMaze.getRoom(4,4).getDoor(Room.SOUTH_INDEX));
        assertEquals(true, myMaze.getRoom(4,4).getDoor(Room.NORTH_INDEX).getVisible());
        assertEquals(null, myMaze.getRoom(4,4).getDoor(Room.EAST_INDEX));
        assertEquals(true, myMaze.getRoom(4,4).getDoor(Room.WEST_INDEX).getVisible());



    }

    @Test
    void testSetLocation() {
        myMaze.setLocation(3, 3);
        assertEquals(3, myMaze.getX());
        assertEquals(3, myMaze.getY());
    }

    @Test
    void testCurrentRoom() {
        myMaze.setLocation(0,0);
        assertEquals(26, myMaze.getRoomNumber());
    }

//    @Test
//    void testGetRoom() {
//        myMaze.setLocation(0,0);
//        assertEquals(myMaze.getRoom(0,0), myMaze.getMaze());
//        assertsEquals(myMaze.getRoom(0,0), myMaze.getMaze());
//    }

    @Test
    void testEndGame() {
        assertEquals(true, myMaze.getGameStatus());
        myMaze.endGame();
        assertEquals(false, myMaze.getGameStatus());
    }

    @Test
    void testGameFinished() {
        myMaze.setLocation(1, 1);
        assertEquals(false, myMaze.gameFinished());

        myMaze.setLocation(4,4);
        assertEquals(true, myMaze.gameFinished());
    }

    @Test
    void testDisplay1() {
        myMaze.setLocation(0,0);

        assertEquals(false, myMaze.display(Direction.NORTH));
        assertEquals(true, myMaze.display(Direction.SOUTH));
        assertEquals(false, myMaze.display(Direction.WEST));
        assertEquals(true, myMaze.display(Direction.EAST));
    }

    @Test
    void testDisplay2() {
        myMaze.setLocation(3,2);

        assertEquals(true, myMaze.display(Direction.NORTH));
        assertEquals(true, myMaze.display(Direction.SOUTH));
        assertEquals(true, myMaze.display(Direction.WEST));
        assertEquals(true, myMaze.display(Direction.EAST));
    }

    @Test
    void testDisplay3() {
        myMaze.setLocation(4,4);

        assertEquals(true, myMaze.display(Direction.NORTH));
        assertEquals(false, myMaze.display(Direction.SOUTH));
        assertEquals(true, myMaze.display(Direction.WEST));
        assertEquals(false, myMaze.display(Direction.EAST));
    }

    @Test
    void testMovePlayer1() {

        myMaze.roomSetup();

        //assertEquals(null, myMaze.getRoom(0,0).getDoor(Room.SOUTH_INDEX));

        //myMaze.getRoom(0,0).getDoor(Room.NORTH_INDEX).unlock();
        myMaze.setLocation(0,0);
        myMaze.movePlayer(Direction.NORTH);
        assertEquals(0, myMaze.getX());
        assertEquals(0, myMaze.getY());

        myMaze.getRoom(0,0).getDoor(Room.SOUTH_INDEX).unlock();
        myMaze.setLocation(0,0);
        myMaze.movePlayer(Direction.SOUTH);
        assertEquals(0, myMaze.getX());
        assertEquals(1, myMaze.getY());

        //myMaze.getRoom(0,0).getDoor(Room.WEST_INDEX).setLock(false);
        myMaze.setLocation(0,0);
        myMaze.movePlayer(Direction.WEST);
        assertEquals(0, myMaze.getX());
        assertEquals(0, myMaze.getY());

        myMaze.getRoom(0,0).getDoor(Room.EAST_INDEX).unlock();
        myMaze.setLocation(0,0);
        myMaze.movePlayer(Direction.EAST);
        assertEquals(1, myMaze.getX());
        assertEquals(0, myMaze.getY());
    }

    @Test
    void testMovePlayer2() {
        myMaze.roomSetup();

        myMaze.getRoom(3,3).getDoor(Room.NORTH_INDEX).unlock();
        myMaze.setLocation(3,3);
        myMaze.movePlayer(Direction.NORTH);
        assertEquals(3, myMaze.getX());
        assertEquals(2, myMaze.getY());

        myMaze.getRoom(3,3).getDoor(Room.SOUTH_INDEX).unlock();
        myMaze.setLocation(3,3);
        myMaze.movePlayer(Direction.SOUTH);
        assertEquals(3, myMaze.getX());
        assertEquals(4, myMaze.getY());

        myMaze.getRoom(3,3).getDoor(Room.WEST_INDEX).unlock();
        myMaze.setLocation(3,3);
        myMaze.movePlayer(Direction.WEST);
        assertEquals(2, myMaze.getX());
        assertEquals(3, myMaze.getY());

        myMaze.getRoom(3,3).getDoor(Room.EAST_INDEX).unlock();
        myMaze.setLocation(3,3);
        myMaze.movePlayer(Direction.EAST);
        assertEquals(4, myMaze.getX());
        assertEquals(3, myMaze.getY());
    }


    @Test
    void testIsPossible1() {
        myMaze.setLocation(0,0);
        myMaze.roomSetup();

        myMaze.getRoom(0,0).getDoor(Room.SOUTH_INDEX).setForeverLocked(true);
        myMaze.getRoom(0,0).getDoor(Room.EAST_INDEX).setForeverLocked(true);

        assertEquals(false, myMaze.isPossible());
    }

    @Test
    void testIsPossible2() {
        myMaze.setLocation(1,1);
        myMaze.roomSetup();

        myMaze.getRoom(1,1).getDoor(Room.SOUTH_INDEX).setForeverLocked(true);
        myMaze.getRoom(1,1).getDoor(Room.EAST_INDEX).setForeverLocked(true);
        myMaze.getRoom(1,1).getDoor(Room.NORTH_INDEX).setForeverLocked(true);
        myMaze.getRoom(1,1).getDoor(Room.WEST_INDEX).setForeverLocked(true);

        assertEquals(false, myMaze.isPossible());

    }

    @Test
    void testIsPossible3() {
        myMaze.setLocation(1,1);
        myMaze.roomSetup();

        myMaze.getRoom(1,1).getDoor(Room.SOUTH_INDEX).setForeverLocked(true);

        assertEquals(true, myMaze.isPossible());
    }

    @Test
    void testIsPossible4() {
        myMaze.setLocation(0,0);
        myMaze.roomSetup();

        assertEquals(true, myMaze.isPossible());
    }

    @Test
    void testUnlockingDoors() {
        myMaze.setLocation(0,0);
        myMaze.roomSetup();

        myMaze.getRoom(0,0).getDoor(Room.SOUTH_INDEX).setLock(false);
        myMaze.unlockingDoors(Direction.SOUTH);

        assertEquals(false, myMaze.getRoom(0,1).getDoor(Room.NORTH_INDEX).getLock());
    }

    @Test
    void testLockingDoors() {
        myMaze.setLocation(0,0);
        myMaze.roomSetup();

        myMaze.getRoom(0,0).getDoor(Room.SOUTH_INDEX).setLock(true);
        myMaze.lockingDoors(Direction.SOUTH);

        assertEquals(true, myMaze.getRoom(0,1).getDoor(Room.NORTH_INDEX).getLock());
    }

    @Test
    void testCharName() {
        myMaze.setCharName("Black Widow");
        assertEquals("Black Widow", myMaze.getCharName());
    }

    @Test
    void testCharImage() {
        myMaze.setCharImage("blackwidow.png");
        assertEquals("blackwidow.png", myMaze.getCharImage());
    }

    @Test
    void testDiffLevel() {
        myMaze.setDiffLevel(1);
        assertEquals(1, myMaze.getDiffLevel());
    }





}
