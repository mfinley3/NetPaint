package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import view.NetPaintGUI;

/**
 * The shape object is the class that links to all shapes so NetPaintGUI doesn't know about them but still has the ability to draw a shape of that requested type.
 * 
 * @authors Mike Finley and Katelyn Hudspeth
 *
 */
public abstract class Shape<E> extends JPanel{

	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private Color color;
	private int height;
	private int width;
	private String imageLocation;

	/**
	 * Creates a new shape with all the needed information about that shape.
	 * 
	 * @param startX,startY Takes in startingX position and startingY position. 
	 */
	public Shape(int startX, int startY) {
		this.startX = startX; 
		this.startY = startY;
		endX = startX;
		endY = startY;
		this.color = Color.BLACK;
		imageLocation = "";
	}

	/**
	 * Returns the requested shapes color.
	 * 
	 * @return color Sends back a color. 	 
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Sets the requested shapes color.
	 * 
	 * @param newColor Takes in the new color.	 
	 */
	public void setColor(Color newColor) {
		color = newColor;
	}

	/**
	 * Returns the starting X point of the shape.	 
	 * @return startX Sends back the startingX.
	 */
	public int getStartX() {
		return startX;
	}

	/**
	 * Returns the starting Y point of the shape.	
	 * 
	 * @return startY Sends back the startingY. 
	 */
	public int getStartY() {
		return startY;
	}
	
	/**
	 * Sets the starting X point of the shape.
	 * 
	 * @param newStartX Takes in the new startingX.	 
	 */
	public void setStartX(int newStartX) {
		startX = newStartX;
	}

	/**
	 * Sets the starting Y point of the shape.	 
	 * 
	 * @param newStartY Takes in the new startingY.
	 */
	public void setStartY(int newStartY) {
		startY = newStartY;
	}
	
	/**
	 * Sets the ending X point of the shape.
	 * 
	 * @param newEndX Takes in the new EndX.	 
	 */
	public void setEndX(int newEndX) {
		endX = newEndX;
	}

	/**
	 * Sets the ending Y point of the shape.	
	 * 
	 * @param newEndY Takes in the new EndY. 
	 */
	public void setEndY(int newEndY) {
		endY = newEndY;
	}
	
	/**
	 * Returns the ending X point of the shape.
	 * 	
	 * @return endX Returns the EndingX point. 
	 */
	public int getEndX() {
		return endX;
	}

	/**
	 * Sets the ending Y point of the shape.
	 * 
	 * @return endY Sends back the endingY point.	 
	 */
	public int getEndY() {
		return endY;
	}
	
	/**
	 * Returns the height of the shape.
	 * 
	 * @return height Sends back the height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height of the shape.
	 * 
	 * @param newHeight Takes in the new height. 	 
	 */
	public void setHeight(int newHeight) {
		height = newHeight;
	}

	/**
	 * Returns the width of the shape.	
	 * 
	 * @return width Sends back the width. 
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width of the shape.	
	 *  
	 * @param newWidth Takes in the new width.
	 */
	public void setWidth(int newWidth) {
		width = newWidth;
	}
	
	/**
	 * Sets the image.	
	 *  
	 * @param image Takes in the new image.
	 */
	public void setImage(String image) {
		this.imageLocation = image;
	}
	
	/**
	 * Returns the width of the shape.	
	 * 
	 * @return imageLocation Sends back the imageLocation. 
	 */
	public String getImage() {
		return imageLocation;
	}
	
	/**
	 * How the shape is drawn is different for each shape.	
	 * 
	 * @param newX,newY Takes in the new X and new Y. 
	 */
	abstract public E drawShape(int newX, int newY);
	
	/**
	 * How the shape is "saved" is different for each shape.
	 * 
	 * @param newX,newY Takes in the new X and new Y. 	 
	 */
	abstract public void lastForm(int newX, int newY);


	/**
	 * How the shape is drawn on the canvas,is different for each shape.	
	 * 
	 * @param g2 Takes in a Graphics2D. 
	 */
	abstract public void draw(Graphics2D g2);


}