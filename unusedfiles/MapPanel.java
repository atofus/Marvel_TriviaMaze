package unusedfiles;

import javax.swing.*;
import java.awt.*;
import model.Room;
import model.Maze;

public class MapPanel extends JPanel {

  //  private Maze myMaze = new Maze();
    private Maze myMaze = Maze.getMyInstance();
   // private Player myPlayer = new Player();
    private ImageIcon playerIcon;
    private static MapPanel myMapInstance = new MapPanel();


    public static MapPanel getMyInstance() {
        return myMapInstance;
    }


    public MapPanel() {
//        super();
//        this.setOpaque(true);
//        this.setBackground(Color.BLACK);
      //  add(new RoomPanel());
    }

//    public void setMapMazeX(final int theIndex) {
//        myMaze.setX(theIndex);
//    }
//
//    public void setMapMazeY(final int theIndex) {
//        myMaze.setY(theIndex);
//    }
//
//    public int getMapMazeX() {
//        return myMaze.getX();
//    }
//
//    public int getMapMazeY() {
//        return myMaze.getY();
//    }
//
//    public Room[][] getMapMaze() {
//        return myMaze.getMaze();
//    }


    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        System.out.println("is it repainting?");

        Room [][] mazeMap = myMaze.getMaze();
       // String playerIcon = myPlayer.getImage();
        Graphics2D g2d = (Graphics2D) g;

        playerIcon = new ImageIcon("src/images/Comic_Books.png");
        //draw border for playing field
        g2d.setColor(Color.YELLOW); //sets the border/line to yellow

        //scales the player icon down a little bit.
        Image image = playerIcon.getImage();
        Image newing = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        playerIcon = new ImageIcon(newing);

        int boxWidth = 70;
        int boxHeight = 70;
        for (int currentX = 0; currentX < mazeMap.length * boxWidth; currentX += boxWidth) {
            for (int currentY = 0; currentY < mazeMap[0].length * boxHeight; currentY += boxHeight) {
                g2d.drawRect(currentX, currentY, boxWidth, boxHeight);

                if (currentX == myMaze.getX() * boxWidth && currentY == myMaze.getY() * boxHeight) {
                    g2d.drawImage(playerIcon.getImage(), currentX + boxWidth/2 - playerIcon.getIconWidth()/2,
                        currentY + boxHeight/2 - playerIcon.getIconHeight()/2, null);
                }

                //will receive the player room and we'll check every door around the room. If door is foreverlocked, we draw pic to let player know they can't go thru
//                for (int i = 0; i < 4; i++) {
//                    if (myMaze.getRoom().getDoor(i).getForeverLocked()) {
//                        g.drawImage(playerIcon.getImage(), currentX + boxWidth/2 - playerIcon.getIconWidth()/2,
//                                currentY + boxHeight/2 - playerIcon.getIconHeight()/2, null);
//                    }
//                }

            }
        }

        g.setColor(Color.LIGHT_GRAY);

    }

    public void repaintPanel() {
        repaint();
    }



}
