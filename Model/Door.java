package Model;

public class Door {

    private boolean myLock = false;

    private boolean visible = true;

    private Question myQuestion = new Question("Captain America's Name?", "Steve Rogers");

    public Door () {

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
        myLock = true;
    }

}
