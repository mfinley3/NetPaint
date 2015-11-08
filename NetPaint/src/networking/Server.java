package networking;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import model.Shape;

/**
 * This class is the server side of NetPaint. The server communicates with
 * clients, sends and receives commands, and holds the listOfShapes
 * 
 * @author Mike Finley and Katelyn Hudspeth
 */
public class Server {
	private ServerSocket socket;

	private List<Shape> listOfShapes;
	private HashMap<String, ObjectOutputStream> outputs;

	/**
	 * This thread reads and executes commands sent by a client
	 */
	private class ClientHandler implements Runnable {
		private ObjectInputStream input;

		public ClientHandler(ObjectInputStream input) {
			this.input = input;
		}

		public void run() {
			try {
				while (true) {

					Command<Server> command = (Command<Server>) input.readObject();
					command.execute(Server.this);

					if (command instanceof DisconnectCommand) {
						input.close();
						return;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This thread listens for and sets up connections to new clients
	 */
	private class ClientAccepter implements Runnable {
		public void run() {
			try {
				while (true) {

					Socket s = socket.accept();
					ObjectOutputStream output = new ObjectOutputStream(
							s.getOutputStream());
					ObjectInputStream input = new ObjectInputStream(
							s.getInputStream());

					String clientName = (String) input.readObject();

					/*
					 * This makes sure that the user name the client has typed in, is not already being used.  If it is, the user will be prompted to enter a new user name until they pick one that has not been used yet.
					 */
					boolean sameName = true;
					while (sameName) {
						sameName = false;
						for (String key : outputs.keySet()) {
							if (clientName.equals(key)) {
								clientName = JOptionPane.showInputDialog("That user name has already been used.  Pick a different one: ");
								sameName = true;
								break;
							}
						}
					}

					output.writeObject(true);
					
					new Thread(new ClientHandler(input)).start();
					
					outputs.put(clientName, output);

					updateClients();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This Starts up the new Server
	 */
	public Server() {
		this.listOfShapes = new ArrayList<Shape>();
		this.outputs = new HashMap<String, ObjectOutputStream>();

		try {

			socket = new ServerSocket(9001);
			System.out.println("NetPaintServer started on port 9001");

			new Thread(new ClientAccepter()).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a shape to the listOfShapes. Called by an AddMessageCommand.
	 * 
	 * @param addShape Shape to add
	 */
	public void addShape(Shape addShape) {
		listOfShapes.add(addShape);
		updateClients();
	}

	/**
	 * Writes an UpdateClientCommand to every connected user.
	 */
	public void updateClients() {

		UpdateClientCommand update = new UpdateClientCommand(listOfShapes);
		try {
			for (ObjectOutputStream out : outputs.values())
				out.writeObject(update);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Server();
	}

	/**
	 * Disconnects a given user from the server gracefully
	 * 
	 * @param clientName user to disconnect
	 */
	public void disconnect(String clientName) {
		try {
			outputs.get(clientName).close();
			outputs.remove(clientName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}