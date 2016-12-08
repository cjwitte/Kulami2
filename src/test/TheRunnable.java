package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TheRunnable implements Runnable {

	private String toWrite;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	
	public void setToWrite(String text) {
		toWrite = text;
	}
	
	public TheRunnable(String text) {
		toWrite = text;
	}
	
	@Override
	public void run() {
		while(true) {
			try (
					Socket socket = new Socket ("localhost", 2021);
		            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
					) {
				out.println(toWrite);
				System.out.println("geschrieben: " + toWrite);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	

}
