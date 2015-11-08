package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

/**
 * This is the line shape it extends shape and is used to draw and save lines.
 * 
 * @authors Mike Finley and Katelyn Hudspeth
 *
 */
public class Line extends Shape{
	

	/**
	 * Creates a new line using Shape by passing shape needed information.
	 * 
	 * @param startX,startY Takes in startingX position and startingY position. 	 
	 */
	public Line(int startX, int startY) {
		super(startX, startY);
		
	}

	/**
	 * Returns a line to be drawn using information of new coordinates to draw.
	 * 	 
	 * @param newX,newY Takes in newX position and newY position. 
	 * @return line Sends back a Line 
	 */
	@Override
	public Line2D drawShape(int newX, int newY) {
		Line2D line = new Line2D.Double(getStartX(), getStartY(), newX, newY);
		return line;
		
	}

	/**
	 * Used to save the final end points of a shape that has been drawn.
	 * 
	 * @param newX,newY Takes in newX position and newY position.
	 */
	public void lastForm(int newX, int newY) {
		setEndX(newX);
		setEndY(newY);
		
	}

	/**
	 * Used to draw a line onto the canvas.
	 * 
	 * @param g2 Takes in a Graphic2D this is what draws on the canvas.
	 */
	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(getColor());
		g2.drawLine(getStartX(),getStartY(), getEndX(),getEndY());
		
	}
	

}
