package model;

public class Door {

    private boolean myLock;
    private boolean myForeverLocked;

    private boolean visible;

    private Question myQuestion = new Question("Captain America's Name?", "Steve Rogers");

    public Door () {
        myLock = true;
        myForeverLocked = false;
        visible = true;
    }

    public boolean getLock() {
        return myLock;
    }

    public void setLock (boolean locked) {
        myLock = locked;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean theVis) {
        visible = theVis;
    }

    public void unlock () {
        myLock = false;
    }

    public boolean getForeverLocked() {
        return myForeverLocked;
    }

    public void setForeverLocked(final boolean locked) {
        myForeverLocked = locked;
    }

}
