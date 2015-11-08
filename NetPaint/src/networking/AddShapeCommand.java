package networking;

import model.Shape;

/**
 *	Adds a shape object to the server's listOfShapes
 *
 * @authors Mike Finley and Katelyn Hudspeth
 *
 */
public class AddShapeCommand extends Command<Server>{
	private static final long serialVersionUID = 8394654307009158284L;
	private Shape addAShape; // message from client
	
	/**
	 * Creates an AddMessageCommand with the given message
	 * 
	 * @param addShape	Shape to add to shapeList
	 */
	public AddShapeCommand(Shape addShape){
		this.addAShape = addShape;
	}
	
	/**
	 * Executes an addAShape with the given Shape
	 * 
	 * @param executeOn	Command to execute.
	 */
	public void execute(Server executeOn) {
		// add message to server's chat log
		executeOn.addShape(addAShape);
	}

}
