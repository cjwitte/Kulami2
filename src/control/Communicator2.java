package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.SwingUtilities;

public class Communicator2 implements Runnable{
	int portNr;
	String hostName;
	Game game;
	
	String fromServer;
	String toServer;
	
	private PrintWriter out;
	
	
	public Communicator2 (int portNr, String hostName, Game game2 ) {
		this.portNr = portNr;
		this.hostName = hostName;
		this.game = game2;
		this.toServer = "";
		System.out.println("communicator erzeugt");
	}

	@Override
	public void run() {
			try (
				Socket socket = new Socket (hostName, portNr);
	            
	            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
				) {
				this.out = new PrintWriter(socket.getOutputStream(), true);
			System.out.println("================================================");
			System.out.println("Communicator2.run()");
			
			while (!((fromServer=in.readLine()).startsWith("spielstart"))) {
				System.out.println("================================================");
				System.out.println("FROMSERVER: " + fromServer);
				System.out.println("TOSERVER:   " + toServer);
				System.out.println("================================================\n");
				if (!fromServer.startsWith("message") && !fromServer.startsWith("spielermessage") ) {
					game.handleServerInput(fromServer);				
				}			
				if (!toServer.equals("")) {
					System.out.println("schreibe, out: " + toServer);
					System.out.println("================================================\n");
					out.println(toServer);
					toServer = "";
				}
				// In dem Fall haben wir doch schon lange eine NullPointerException oder?
				if (fromServer==null) {
					fromServer = "leer";
				}
			}
			
			while (!(fromServer).startsWith("spielende")) {
				System.out.println("================================================");
				System.out.println("Communicator.fromServer: " + fromServer);
				System.out.println("Communicator.toServer:   " + toServer);
				System.out.println("================================================\n");
		//		if (!fromServer.startsWith("message") && !fromServer.startsWith("spielermessage") ) {
					game.handleServerInputInGame(fromServer);
			/*	} else {
					System.out.println(fromServer);
				}*/
				System.out.println("toServer: " + toServer);
				System.out.println("================================================\n");
				if (!toServer.equals("")) {
					System.out.println("schreibe: " + toServer);
					System.out.println("================================================\n");
					out.println(toServer);
					SwingUtilities.invokeLater( new Runnable() {
						public void run() {
							game.getGameFrame().revalidate();
						}
					});
					toServer = "";
				} 
				fromServer = in.readLine();
				if (fromServer == null) {
					System.out.println("fromServer ist null");
					System.out.println("================================================\n");
					fromServer = ("");
				} 
			}
			if (fromServer.startsWith("spielende")) {
				System.out.println("Spiel vorbei.");
				System.out.println("================================================\n");
			}
		}
		 catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				if (out != null) {
					out.close();
				}
			}
	}
	
	public void setToServer (String toServer) {
		System.out.println("================================================");
		System.out.println("Communicator.setToServer(): FROMSERVER: " + fromServer);
		System.out.println("Communicator.setToServer(): TOSERVER:   " + toServer);
		System.out.println("================================================\n");
		this.toServer = toServer;
		if (this.out != null) {
			System.out.println("================================================");
			System.out.println("Communicator.setToServer(): OutputStream not null: writing to Server.");
			out.println(this.toServer);
			System.out.println("Communicator.setToServer(): Output written to server");		
			SwingUtilities.invokeLater( new Runnable() {
				public void run() {
					game.getGameFrame().revalidate();
				}
			});
			System.out.println("Communicator.setToServer(): JFrame revalidation queued.");
			System.out.println("Communicator.setToServer(): Resetting toServer");		
			System.out.println("================================================\n");
			this.toServer = "";
		}
	}
	
	public String getToServer () {
		return toServer;
	}
}
