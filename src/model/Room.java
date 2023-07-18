package model;

public class Room {
    public Player myPlayer;
    private Door [] myDoor;



    public Door getDoor(final int theIndex) {
        return myDoor[theIndex];
    }

    public void setNorthDoor(final boolean theLock) {
        myDoor[0].getForeverLocked();
    }

}
