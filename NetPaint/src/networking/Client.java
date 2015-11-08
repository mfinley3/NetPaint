package networking;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Shape;
import view.NetPaintGUI;

/**
 * This class is the client side of NetPaint. The server communicates with clients, 
 * sends and receives commands, and holds the listOfShapes
 * 
 * @author Mike Finley and Katelyn Hudspeth
 */
public class Client extends JFrame{

	private String clientName; 
	private NetPaintGUI netPaint;
	
	private Socket server; 
	private ObjectOutputStream out; 
	private ObjectInputStream in; 

	/**
	 * This class reads and executes commands sent from the server
	 * 
	 * @author Mike Finley and Katelyn Hudspeth
	 *
	 */
	private class ServerHandler implements Runnable{
		public void run() {
			try{
				while(true){
					
					Command<Client> c = (Command<Client>)in.readObject();
					c.execute(Client.this);
				}
			}
			catch(SocketException e){
				return; 
			}
			catch (EOFException e) {
				return; 
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This sets up a new Client, asking for the needed info to connect.
	 */
	public Client(){
		
		String host = JOptionPane.showInputDialog("Host address:");
		String port = JOptionPane.showInputDialog("Host port:");
		clientName = JOptionPane.showInputDialog("User name:");
		
		if (host == null || port == null || clientName == null)
			return;
		
		try{
			
			server = new Socket(host, Integer.parseInt(port));
			out = new ObjectOutputStream(server.getOutputStream());
			in = new ObjectInputStream(server.getInputStream());
			
			out.writeObject(clientName);
			
			this.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent arg0) {
					try {
						out.writeObject(new DisconnectCommand(clientName));
						out.close();
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			
			//This is so that the GUI will not be setup until the server has said that the username has 
			//successfully been added to the map, meaning it is a unique username
			boolean temp = (boolean) in.readObject();
			
			setupGUI();
		
			new Thread(new ServerHandler()).start();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 	Creates a NetPaintGUI and adds it to this frame
	 */
	private void setupGUI() {
	
		netPaint = new NetPaintGUI(out);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		this.setSize(d.width - 250, d.height - 150);
		this.setTitle("NetPaint");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(netPaint);
		
		this.setVisible(true);
	}

	public static void main(String[] args){
		new Client();
	}

	/**
	 * Updates the Canvas with the updated listOfShapes
	 * 
	 * @param currentShapes	the listOfShapes to display
	 */
	public void update(List<Shape> currentShapes) {
		netPaint.update(currentShapes);
	}
}
	
