package artatawe.classes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Drawing is a window for tracking the information relevant for drawing avatars
 * for the user profile, should they wish to draw their own image.
 * <p>
 * Created 2017/11/30
 *
 * @author Joel Osborne
 **/
public class Drawing extends JComponent {
    Image image;
    Graphics2D drawingFrame;
    int currX;
    int currY;
    int oldX;
    int oldY;
    String toolState = "Line";

    /**
     * Create a new drawing.
     *
     * @param fileLoc the location of the file for storage.
     **/
    public Drawing(String fileLoc) {
        setDoubleBuffered(false);

        /* Update X and Y coordinate when mouse is pressed down. */
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });


        /* Get X and Y coordinates for when mouse is dragged. */
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                currX = e.getX();
                currY = e.getY();

                /* If user has trace mode set, draw a line of circles as they
                * drag the mouse */
                if (toolState.equals("Trace")) {
                    Circle circle = new Circle(drawingFrame, oldX, oldY, currX, currY);
                    repaint();
                    oldX = currX;
                    oldY = currY;
                }
            }
        });


        /* Get ending X and Y coordinates for when the mouse is released. */
        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                currX = e.getX();
                currY = e.getY();

                if (toolState == "Line") {
                    Line line = new Line(drawingFrame, oldX, oldY, currX, currY);
                    repaint();
                }
            }
        });
    }


    /**
     * Renders the images onto the frame and creates a frame if one doesn't exist.
     *
     * @param g the label for the image frame of the image being drawn.
     */
    public void paintComponent(Graphics2D g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            drawingFrame = (Graphics2D) image.getGraphics();
            drawingFrame.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            clearScreen();
        }
        g.drawImage(image, 0, 0, null);
    }


    /**
     * Clears the frame of all work and resets to blank white.
     */
    public void clearScreen() {
        drawingFrame.setPaint(Color.white);
        drawingFrame.fillRect(0, 0, getSize().width, getSize().height);
        drawingFrame.setPaint(Color.black);
        repaint();
    }


    /**
     * Sets the toolState to Line when button is pressed.
     **/
    public void lineButton() {
        toolState = "Line";
    }


    /**
     * Sets the toolState to Circle when button is pressed.
     **/
    public void circleButton() {
        toolState = "Circle";
    }
}
	
