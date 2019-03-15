package artatawe.classes;

import java.awt.*;

/**
 * Circle defines a circle object, used by Drawing to create a trace line.
 *
 * Created: 2017/11/27
 * @author Joel Osborne
 */
public class Circle {

    /**
     * Create a new circle and add it to drawing frame
     *
     * @param g     the frame to draw the circle on
     * @param oldX  the start point for X coordinates
     * @param oldY  the start point for Y coordinates
     * @param currX end point for X coordinates
     * @param currY end point for Y coordinates
     **/
    public Circle(Graphics2D g, int oldX, int oldY, int currX, int currY) {
        int x, width, y, height;
        if (oldX < currX) {
            x = oldX;
            width = currX - x;
        } else {
            x = currX;
            width = oldX - x;
        }
        if (oldY < currY) {
            y = currY;
            height = oldY - y;
        } else {
            y = oldY;
            height = currY - y;
        }
        g.fillOval(x, y, width, height);
    }
}