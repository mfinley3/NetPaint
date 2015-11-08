package networking;

import java.io.Serializable;

/**
 *	Abstract Class for commands
 *
 * @authors Mike Finley and Katelyn Hudspeth
 *
 */
public abstract class Command<T> implements Serializable {
	private static final long serialVersionUID = -4838155228547508978L;
	
	/**
  	* Executes an addAShape with the given Shape
  	* 
  	* @param executeOn	Command to execute.
  	*/	
	abstract void execute(T executeOn);
}