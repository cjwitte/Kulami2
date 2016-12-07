package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;

import javax.swing.SwingUtilities;

import test.GameTest;

public class Communicator2 implements Runnable{
	int portNr;
	String hostName;
	Game game;
	
	String fromServer;
	String toServer;
	
	
	public Communicator2 (int portNr, String hostName, Game game2 ) {
		this.portNr = portNr;
		this.hostName = hostName;
		this.game = game2;
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
		//		fromServer = in.readLine();
				System.out.println(fromServer);
				if (!fromServer.startsWith("message") && !fromServer.startsWith("spielermessage") ) {
					game.handleServerInput(fromServer);				}
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
		//		if (!fromServer.startsWith("message") && !fromServer.startsWith("spielermessage") ) {
					game.handleServerInputInGame(fromServer);
			/*	} else {
					System.out.println(fromServer);
				}*/
				if (!toServer.equals("")) {
					System.out.println("schreibe: " + toServer);
					out.println(toServer);
					SwingUtilities.invokeLater( new Runnable() {
						public void run() {
							game.getGameFrame().repaint();
						}
					});
					toServer = "";
				} 
				fromServer = in.readLine();
				if (fromServer == null) {
					fromServer = ("");
				}
			}
			if (fromServer.startsWith("spielende")) {
				System.out.println("Spiel vorbei.");
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
