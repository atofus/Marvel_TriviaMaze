package tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoorTest {

    private Door door;

    @BeforeEach
    void setUp() {
        door = new Door();
    }

    @Test
    void testLock() {
        door.setLock(false);
        assertEquals(false, door.getLock());

        door.setLock(true);
        assertEquals(true, door.getLock());
    }

    @Test
    void testVisible() {
        door.setVisible(false);
        assertEquals(false, door.getVisible());

        door.setVisible(true);
        assertEquals(true, door.getVisible());
    }

    @Test
    void testUnlock() {
        door.setLock(false);
        door.unlock();
        assertEquals(false, door.getLock());
    }

    @Test
    void testForeverLocked() {
        door.setForeverLocked(false);
        assertEquals(false, door.getForeverLocked());

        door.setForeverLocked(true);
        assertEquals(true, door.getForeverLocked());
    }

    @Test
    void testGetQuestion() {
        assertNotEquals(null, door.getQuestion());
        assertNotEquals(null, door.getOptionA());
        assertNotEquals(null, door.getOptionB());
        assertNotEquals(null, door.getOptionC());
        assertNotEquals(null, door.getOptionD());
        assertNotEquals(null, door.getAnswer());
    }



}
