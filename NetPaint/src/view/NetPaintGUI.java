package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import networking.AddShapeCommand;
import model.Image;
import model.Line;
import model.Oval;
import model.Rectangle;
import model.Shape;

/**
 * The class that handles anything related to controlling the GUI and drawing
 * shapes.
 * 
 * @authors Mike Finley and Katelyn Hudspeth
 * 
 */
public class NetPaintGUI<E> extends JPanel {

	private JRadioButton line, rect, oval, image;
	private static String currentShapeName;
	private JScrollPane drawingPanel;
	private JPanel canvas = new Canvas();
	private static boolean isDrawing;
	private static int newX;
	private static int newY;
	private int startingY;
	private int startingX;
	private static Shape currentShape;
	private JColorChooser colorChooser;
	private static List<Shape> listOfShapes;

	private ObjectOutputStream output; // output stream to server

	private static final long serialVersionUID = 7686336736079994065L;

	/**
	 * Lays out the GUI for NetPaint also registers listeners.
	 * 
	 * @param output takes in some output
	 */
	public NetPaintGUI(ObjectOutputStream output) {

		this.output = output;

		listOfShapes = new ArrayList<Shape>();
		drawingPanel = new JScrollPane(canvas);

		isDrawing = false;

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		this.setSize(d.width - 250, d.height - 150);
		this.setVisible(true);
		this.setLocation(100, 0);
		// this.setTitle("NetPaint");
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLayout(new BorderLayout());

		this.add(drawingPanel, BorderLayout.CENTER);

		JPanel colors = new JPanel();
		colors.setLayout(new BorderLayout());
		colorChooser = new JColorChooser();
		colors.add(colorChooser, BorderLayout.CENTER);

		line = new JRadioButton("Line");
		rect = new JRadioButton("Rectangle");
		oval = new JRadioButton("Oval");
		image = new JRadioButton("Image");

		JPanel radioButtons = new JPanel();
		radioButtons.setLayout(new FlowLayout());
		radioButtons.add(line);
		radioButtons.add(rect);
		radioButtons.add(oval);
		radioButtons.add(image);

		colors.add(radioButtons, BorderLayout.NORTH);
		this.add(colors, BorderLayout.SOUTH);

		registerListeners();

	}

	/**
	 * The method that is called by the NetPaint constructor to register the
	 * button and mouse listeners.
	 */
	private void registerListeners() {

		line.addActionListener(new LineButtonListener());
		rect.addActionListener(new RectButtonListener());
		oval.addActionListener(new OvalButtonListener());
		image.addActionListener(new ImageButtonListener());

		MouseListener listener = new ListenToMouse();
		MouseMotionListener motionListener = new ListenToMouse();

		canvas.addMouseMotionListener(motionListener);
		canvas.addMouseListener(listener);

	}

	/**
	 * Adds a listener to line radio button, sets the current shape to be a
	 * line.
	 */
	private class LineButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			line.setSelected(true);
			rect.setSelected(false);
			oval.setSelected(false);
			image.setSelected(false);
			currentShapeName = "line";

		}

	}

	/**
	 * Adds a listener to rectangle radio button, sets the current shape to be a
	 * rectangle.
	 */
	private class RectButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			rect.setSelected(true);
			line.setSelected(false);
			oval.setSelected(false);
			image.setSelected(false);
			currentShapeName = "rect";

		}

	}

	/**
	 * Adds a listener to oval radio button, sets the current shape to be an
	 * oval.
	 */
	private class OvalButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			oval.setSelected(true);
			line.setSelected(false);
			rect.setSelected(false);
			image.setSelected(false);
			currentShapeName = "oval";
		}

	}

	/**
	 * Adds a listener to image radio button, sets the current shape to be a
	 * image.
	 */
	private class ImageButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			image.setSelected(true);
			line.setSelected(false);
			rect.setSelected(false);
			oval.setSelected(false);
			currentShapeName = "image";

		}

	}

	/**
	 * Adds a listener to the mouse.
	 */
	private class ListenToMouse implements MouseMotionListener, MouseListener {

		/**
		 * Event were a mouse button is clicked, Is not used. 
		 * 
		 * @param evt Takes in a clicked event
		 */
		public void mouseClicked(MouseEvent evt) {

		}

		/**
		 * Event were the mouse is moved. If the mouse moves and drawing is
		 * toggled then the shape will be drawn. 
		 * 
		 * @param evt Takes in a moved event
		 */
		public void mouseMoved(MouseEvent evt) {

			if (isDrawing) {
				newX = evt.getX();
				newY = evt.getY();
				
				repaint();

			}
		}

		/**
		 * Event were the mouse is pressed. Toggles drawing on and off.
		 * 
		 * @param evt Takes in a mouse pressed event.
		 */
		public void mousePressed(MouseEvent evt) {

			startingX = evt.getX();
			startingY = evt.getY();
			if (currentShapeName != null) {
				if (!isDrawing) {

					if (currentShapeName.equals("line")) {
						currentShape = new Line(startingX, startingY);
					}
					if (currentShapeName.equals("oval")) {
						currentShape = new Oval(startingX, startingY);
					}
					if (currentShapeName.equals("rect")) {
						currentShape = new Rectangle(startingX, startingY);
					}
					if (currentShapeName.equals("image")) {
						currentShape = new Image(startingX, startingY);
					}
					currentShape.setColor(colorChooser.getColor());
				} else {
					currentShape.lastForm(newX, newY);

					try {
						output.writeObject(new AddShapeCommand(currentShape));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			
			isDrawing = !isDrawing;
			}
		}

		/**
		 * Event were the mouse entering the screen. Is not used.
		 * 
		 * @param evt Takes in a mouse entered event.
		 */
		public void mouseEntered(MouseEvent evt) {
			// newX = evt.getX();
			// newY = evt.getY();
			// System.out.println(newX + " Entered " + newY);
		}

		/**
		 * Event were the mouse is released. Is not used.
		 * 
		 * @param evt Takes in a mouse released event.
		 */
		public void mouseReleased(MouseEvent evt) {
			// newX = evt.getX();
			// newY = evt.getY();
			// System.out.println(newX + " released " + newY);
		}

		/**
		 * Event were the mouse is Exiting the screen. Is not used.
		 * 
		 * @param evt Takes in a mouse exited event.
		 */
		public void mouseExited(MouseEvent evt) {
			// newX = evt.getX();
			// newY = evt.getY();
			// System.out.println(newX + " Exited " + newY);
		}

		/**
		 * Event were the mouse is dragged. Is not used.
		 * 
		 * @param evt Takes in a mouse dragged event.
		 */
		public void mouseDragged(MouseEvent evt) {
			// newX = evt.getX();
			// newY = evt.getY();
			// System.out.println(newX + " dragged " + newY);
		}
	} // end JPanel class

	/**
	 * Get method for listOfShapes
	 * 
	 * @return List<Shape> Returns listOfShapes
	 */
	public static List<Shape> getListOfShapes() {
		return listOfShapes;
	}

	/**
	 * Get method for isDrawing
	 * 
	 * @return boolean Returns isDrawing
	 */
	public static boolean getIsDrawing() {
		return isDrawing;
	}

	/**
	 * Get method for currentShape
	 * 
	 * @return Shape Returns currentShape
	 */
	public static Shape getCurrentShape() {
		return currentShape;
	}

	/**
	 * Get method for newX
	 * 
	 * @return int Returns newX
	 */
	public static int getNewX() {
		return newX;
	}

	/**
	 * Get method for newY
	 * 
	 * @return int Returns newY
	 */
	public static int getNewY() {
		return newY;
	}

	/**
	 * Get method for currentShapeName
	 * 
	 * @return String Returns currentShapeName
	 */
	public static String getCurrentShapeName() {
		return currentShapeName;
	}

	/**
	 * Update method, updates the currentShapes list.
	 * Then repaints it to the canvas
	 * 
	 * @param currentShapes takes in the most current list of shapes
	 */
	public void update(List<Shape> currentShapes) {
		this.listOfShapes = currentShapes;
		repaint();

	}
}
