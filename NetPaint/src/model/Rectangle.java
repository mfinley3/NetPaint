package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * This is the rectangle shape it extends shape and is used to draw and save rectangles.
 * 
 * @authors Mike Finley and Katelyn Hudspeth
 *
 */
public class Rectangle extends Shape{
	
	
	/**
	 * Creates a new rectangle using Shape by passing shape needed information.	 
	 * 
	 * @param startX,startY Takes in startingX position and startingY position.
	 */
	public Rectangle(int startX, int startY) {
		super(startX, startY);
	}

	/**
	 * Returns a Rectangle to be drawn using information of new coordinates to draw.
	 * 
	 * @param newX,newY Takes in newX position and newY position. 
	 * @return rect Sends back a Rectangle. 	 
	 */
	@Override
	public Rectangle2D drawShape(int newX, int newY) {

		Rectangle2D rect = null;
		
		// Quadrant 4
		if(newX > getStartX() && newY > getStartY()) {
			rect = new Rectangle2D.Double(getStartX(), getStartY(), newX - getStartX(), newY - getStartY());
		// Quadrant 3
		}else if(newX < getStartX() && newY > getStartY()) {
			rect = new Rectangle2D.Double(newX, getStartY(), getStartX() - newX, newY - getStartY());
		// Quadrant 2
		}else if(newX > getStartX() && newY < getStartY()) {
			rect = new Rectangle2D.Double(getStartX(), newY, newX - getStartX(), getStartY() - newY);
		// Quadrant 1
		}else{ //newX < getX() && newY < getY() 
			rect = new Rectangle2D.Double(newX, newY,getStartX() - newX, getStartY() - newY);
		}
		return rect;
	}
	
	/**
	 * Used to save the final end points of a shape that has been drawn.
	 * 
	 * @param newX,newY Takes in newX position and newY position.
	 */
	public void lastForm(int newX, int newY) {
		// Quadrant 4
		if(newX > getStartX() && newY > getStartY()) {
			setWidth(newX - getStartX());
			setHeight(newY - getStartY());
			setStartX(getStartX());
			setStartY(getStartY());
		// Quadrant 3
		}else if(newX < getStartX() && newY > getStartY()) {
			setWidth(getStartX() - newX);
			setHeight(newY - getStartY());
			setStartX(newX);
			setStartY(getStartY());
		// Quadrant 2
		}else if(newX > getStartX() && newY < getStartY()) {
			setWidth(newX - getStartX());
			setHeight(getStartY() - newY);
			setStartX(getStartX());
			setStartY(newY);
		// Quadrant 1
		}else{ //newX < getX() && newY < getY() 
			setWidth(getStartX() - newX);
			setHeight(getStartY() - newY);
			setStartX(newX);
			setStartY(newY);
		}
		
	}

	/**
	 * Used to draw an rectangle onto the canvas.
	 * 
	 * @param g2 Takes in a Graphic2D this is what draws on the canvas.
	 */
	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(getColor());
		g2.fillRect(getStartX(),getStartY(),getWidth(), getHeight());
		
	}

}
