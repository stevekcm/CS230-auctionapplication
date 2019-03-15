package artatawe.classes;

import java.awt.*;

/**
 * Line
 * Lorem ipsum.
 * Created: 2017/11/27
 * @author Herp Derp
 */
public class Line {

	/**
	 * Constructor for Line adds a line to drawing frame
	 * @param g the frame to draw the line on
	 * @param oldX the start point for X coordinates
	 * @param oldY the start point for Y coordinates
	 * @param currX end point for X coordinates
	 * @param currY end point for Y coordinates
	 **/
	public Line(Graphics2D g, int oldX, int oldY, int currX, int currY){
		int x, width, y, height; // What are these for? Shouldn't they be attributes added above?
		g.drawLine(oldX, oldY, currX, currY);
	}
}