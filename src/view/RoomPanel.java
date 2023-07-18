package view;

import javax.swing.*;
import java.awt.*;
import model.Maze;

public class RoomPanel extends JPanel {

    private JButton myNorthDoor;
    private JButton myWestDoor;
    private JButton mySouthDoor;
    private JButton myEastDoor;
    private Maze myMaze;

    public RoomPanel() {
       // myMaze = new Maze();
    }

    @Override
    //method is used to draw the box representing the room.
    public void paint(Graphics g) {
        //Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.YELLOW);
        g.drawRect(137, 593, 200, 200);

        //g.setColor(Color.YELLOW);
        //g.drawRect(137, 593, 200, 200);
    }

    //action listeners for buttons.
    //if (myWestDoor is pressed)
    //myMaze.setX(myMaze.getX() - 1);


}
