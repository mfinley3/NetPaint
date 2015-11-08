package networking;

/**
 * This command is sent by a client that is disconnecting
 * 
 * @author Mike Finley and Katelyn Hudspeth
 *
 */
public class DisconnectCommand extends Command<Server>{
	private static final long serialVersionUID = -8557424886231888586L;
	private String clientName; // client who is disconnecting
	
	/**
	 * Creates a disconnect command for the given client
	 * 
	 * @param name	username of client to disconnect
	 */
	public DisconnectCommand(String name){
		clientName = name;
	}
	
	/**
	 * Executes an disconnect with the given clientName
	 * 
	 * @param executeOn	Command to execute.
	 */
	@Override
	public void execute(Server executeOn) {
		// disconnect client
		executeOn.disconnect(clientName);
	}

}
