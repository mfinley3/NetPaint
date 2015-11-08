package networking;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

import model.Shape;

/**
 * Updates a client with the current list of shapes
 * 
 * @author Mike Finley and Katelyn Hudspeth
 *
 */
public class UpdateClientCommand extends Command<Client> {
	private static final long serialVersionUID = 4222014184904080846L;
	private List<Shape> listOfShapes; // the message log from the server
	
	/**
	 * Creates a new UpdateClientCommand with the given list of shapes
	 * 
	 * @param listOfShapes	the listOfShapes
	 */
	public UpdateClientCommand(List<Shape> listOfShapes){
		this.listOfShapes = new ArrayList<Shape>(listOfShapes); // note: we are making a copy of the given list
	}

	/**
	 * Executes an disconnect with the given clientName
	 * 
	 * @param executeOn	Command to execute.
	 */
	public void execute(Client executeOn) {
		// update the client
		executeOn.update(listOfShapes);
	}
}
