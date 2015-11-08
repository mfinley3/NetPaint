package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This is the Image it extends shape and is used to draw and save Images.
 * 
 * @authors Mike Finley and Katelyn Hudspeth
 *
 */
public class Image extends Shape {

	/**
	 * Creates a new Image using Shape by passing shape needed information and loading in the image.	
	 * 
	 * @param startX,startY Takes in startingX position and startingY position. 
	 */
	public Image(int startX, int startY) {
		super(startX, startY);
		setImage("doge.jpeg");
	}

	/**
	 * Returns an image to be drawn using information of new coordinates to draw.	
	 * 
	 * @param newX,newY Takes in newX position and newY position. 
	 * @return Sends back an Image
	 */
	@Override
	public Object drawShape(int newX, int newY) { 
		
		try {
			return ImageIO.read(new File(getImage()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Used to save the final end points of an image that has been drawn.
	 * 
	 * @param newX,newY Takes in startingX position and startingY position.
	 */
	@Override
	public void lastForm(int newX, int newY) {
		// Quadrant 4
		if(newX > getStartX() && newY > getStartY()) {
			setWidth(newX - getStartX());
			setHeight(newY - getStartY());
			setStartX(getStartX());
			setStartY(getStartY());
			setImage("doge.jpeg");
			//setImage()
		// Quadrant 3
		}else if(newX < getStartX() && newY > getStartY()) {
			setWidth(getStartX() - newX);
			setHeight(newY - getStartY());
			setStartX(newX);
			setStartY(getStartY());
			setImage("dogeQ3.jpeg");
		// Quadrant 1
		}else if(newX > getStartX() && newY < getStartY()) {
			setWidth(newX - getStartX());
			setHeight(getStartY() - newY);
			setStartX(getStartX());
			setStartY(newY);
			setImage("dogeQ1.jpeg");
		// Quadrant 2
		}else{ //newX < getX() && newY < getY() 
			setWidth(getStartX() - newX);
			setHeight(getStartY() - newY);
			setStartX(newX);
			setStartY(newY);
			setImage("dogeQ2.jpeg");
		}
	}

	/**
	 * Used to draw a image onto the canvas.
	 * 
	 * @param g2 Takes in a Graphic2D this is what draws on the canvas.
	 */
	@Override
	public void draw(Graphics2D g2) {
		
			try {
				g2.drawImage(ImageIO.read(new File(getImage())),getStartX(),getStartY(), getWidth(),getHeight(), null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}

}
