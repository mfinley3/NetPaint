package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;
import model.Shape;

/**
 * The class creates the area that is going to be drawn on.
 * 
 * @authors Mike Finley and Katelyn Hudspeth
 * 
 */
public class Canvas extends JPanel {

	/**
	 * Lays out the GUI for the canvas.
	 * 
	 */
	public Canvas() {

		Dimension maxSize = new Dimension(5000, 4000);
		this.setPreferredSize(maxSize);
		this.setBackground(Color.WHITE);
		this.setVisible(true);

	}

	/**
	 * Used for painting to the canvas and ghosting images.
	 * Puts the list of shapes on the canvas and creates a ghost image 
	 * when needed.
	 * 
	 * @param g Takes in Graphics 
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		
		
		
		List<Shape> listOfShapes = NetPaintGUI.getListOfShapes();
		for (Shape s : listOfShapes) {
			s.draw(g2);
		}
		
		if (NetPaintGUI.getIsDrawing()) {
			
			String currentShapeName = NetPaintGUI.getCurrentShapeName();
			Shape currentShape = NetPaintGUI.getCurrentShape();
			int currentShapeStartX = NetPaintGUI.getCurrentShape().getStartX();
			int currentShapeStartY = NetPaintGUI.getCurrentShape().getStartY();
			int newX = NetPaintGUI.getNewX();
			int newY = NetPaintGUI.getNewY();
			
			g2.setColor(currentShape.getColor());
			
			if (currentShape != null && !currentShapeName.equals("line") && !currentShapeName.equals("image")) {
				g2.fill((java.awt.Shape) currentShape.drawShape(newX, newY));
				
			} else if(currentShapeName.equals("line")) {
				g2.draw((java.awt.Shape) currentShape.drawShape(newX, newY));
				
			} else if(currentShapeName.equals("image")) {
				currentShape.setEndX(newX);
				currentShape.setEndY(newY);
				
				g2.drawImage((java.awt.Image) currentShape.drawShape(newX, newY),currentShapeStartX, currentShapeStartY, newX - currentShapeStartX, newY - currentShapeStartY, null);
			}
		}


	}
}
