package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * This is the Oval shape it extends shape and is used to draw and save ovals.
 * 
 * @authors Mike Finley and Katelyn Hudspeth
 * 
 */
public class Oval extends Shape {

	/**
	 * Creates a new Oval using Shape by passing shape needed information.
	 * 
	 * @param startX,startY Takes in startingX position and startingY position.
	 */
	public Oval(int startX, int startY) {
		super(startX, startY);

	}

	/**
	 * Returns a Oval to be drawn using information of new coordinates to draw.
	 * 
	 * @param newX,newY Takes in newX position and newY position.
	 * @return oval Sends back an oval.
	 */
	@Override
	public Ellipse2D drawShape(int newX, int newY) {
		Ellipse2D oval = null;

		if (newX > getStartX() && newY > getStartY()) {// Quadrant 4
			oval = new Ellipse2D.Double(getStartX(), getStartY(), newX
					- getStartX(), newY - getStartY());
			setWidth(newX - getStartX());
			setHeight(newY - getStartY());
		} else if (newX < getStartX() && newY > getStartY()) {// Quadrant 3
			oval = new Ellipse2D.Double(newX, getStartY(), getStartX() - newX,
					newY - getStartY());
			setWidth(getStartX() - newX);
			setHeight(newY - getStartY());

		} else if (newX > getStartX() && newY < getStartY()) {// Quadrant 2
			oval = new Ellipse2D.Double(getStartX(), newY, newX - getStartX(),
					getStartY() - newY);
			setWidth(newX - getStartX());
			setHeight(getStartY() - newY);
		} else {
			// newX < getX() && newY < getY() // Quadrant 1
			oval = new Ellipse2D.Double(newX, newY, getStartX() - newX,
					getStartY() - newY);
			setWidth(getStartX() - newX);
			setHeight(getStartY() - newY);

		}
		return oval;
	}

	/**
	 * Used to save the final end points of a shape that has been drawn.
	 * 
	 * @param newX,newY Takes in newX position and newY position.
	 */
	@Override
	public void lastForm(int newX, int newY) {
		// Quadrant 4
		if (newX > getStartX() && newY > getStartY()) {
			setWidth(newX - getStartX());
			setHeight(newY - getStartY());
			setStartX(getStartX());
			setStartY(getStartY());
			// Quadrant 3
		} else if (newX < getStartX() && newY > getStartY()) {
			setWidth(getStartX() - newX);
			setHeight(newY - getStartY());
			setStartX(newX);
			setStartY(getStartY());
			// Quadrant 2
		} else if (newX > getStartX() && newY < getStartY()) {
			setWidth(newX - getStartX());
			setHeight(getStartY() - newY);
			setStartX(getStartX());
			setStartY(newY);
			// Quadrant 1
		} else { // newX < getX() && newY < getY()
			setWidth(getStartX() - newX);
			setHeight(getStartY() - newY);
			setStartX(newX);
			setStartY(newY);
		}

	}

	/**
	 * Used to draw an oval onto the canvas.
	 * 
	 * @param g2 Takes in a Graphic2D this is what draws on the canvas.
	 */
	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(getColor());
		g2.fillOval(getStartX(), getStartY(), getWidth(), getHeight());
	}

}
