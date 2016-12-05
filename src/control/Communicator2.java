package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;

import javax.swing.SwingUtilities;

public class Communicator2 implements Runnable{
	int portNr;
	String hostName;
	Game game;
	
	String fromServer;
	String toServer;
	
	
	public Communicator2 (int portNr, String hostName, Game game ) {
		this.portNr = portNr;
		this.hostName = hostName;
		this.game = game;
		this.toServer = "";
	}

	@Override
	public void run() {
		try (
				Socket socket = new Socket (hostName, portNr);
	            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
				) {
			
			while (!((fromServer=in.readLine()).startsWith("spielstart"))) {
				System.out.println("passiert was");
		//		fromServer = in.readLine();
				System.out.println(fromServer);
				if (!fromServer.startsWith("message") && !fromServer.startsWith("spielermessage") ) {
					game.handleServerInput(fromServer);
				}
		//		System.out.println("zwischen den if");
				if (!toServer.equals("")) {
					System.out.println("schreibe, out: " + toServer);
					out.println(toServer);
					toServer = "";
				}
			
				if (fromServer==null) {
					fromServer = "leer";
				}
			}
			
			while (!(fromServer).startsWith("spielende")) {
				
				System.out.println(fromServer);
				if (!fromServer.startsWith("message") && !fromServer.startsWith("spielermessage") ) {
					game.handleServerInputInGame(fromServer);
				} else {
					System.out.println(fromServer);
				}
		//		System.out.println("zwischen den if");
				if (!toServer.equals("")) {
					System.out.println("schreibe: " + toServer);
					out.println(toServer);
					SwingUtilities.invokeLater( new Runnable() {
						public void run() {
							game.getGameFrame().repaint();
						}
					});
					toServer = "";
				} else {
					System.out.println(toServer);
				}
				fromServer = in.readLine();
			}
		}
		 catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void setToServer (String toServer) {
		this.toServer = toServer;
	}
	
	public String getToServer () {
		return toServer;
	}
}
