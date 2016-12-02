package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Communicator2 implements Runnable{
	int portNr;
	String hostName;
	Game game;
	int state;
	String fromServer;
	String toServer;
	boolean shouldWrite;
	
	public Communicator2 (int portNr, String hostName, Game game ) {
		this.portNr = portNr;
		this.hostName = hostName;
		this.game = game;
		this.state = 0;
		this.toServer = null;
	}

	@Override
	public void run() {
		try (
				Socket socket = new Socket (hostName, portNr);
	            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
				) {
			
			while (true) {
				System.out.println("true");
				fromServer = in.readLine();
				if (fromServer != null) {
					System.out.println("Server: " + fromServer);
					if (fromServer.startsWith("message") || fromServer.startsWith("playerMessage")) {
						System.out.println(fromServer);
						toServer = null;
					} else {
						game.handleServerInput(fromServer);
					}
				}
				if (shouldWrite && !toServer.equals(null)){
					System.out.println("Ich schreibe jetz: " + toServer);
					out.println(toServer);
					shouldWrite = false;
				} else {
					System.out.println(toServer);
				}
			}
				
			
		}
	
		 catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void setState (int state ) {
		this.state = state;
	}
	
	public void setToServer (String toServer) {
		this.toServer = toServer;
	}
	
	public String getToServer () {
		return toServer;
	}
	
	public int getState () {
		return state;
	}
	
	public void setShouldWrite(boolean b) {
		shouldWrite = b;
	}
	
	public boolean getShouldWrite () {
		return shouldWrite;
	}
}
