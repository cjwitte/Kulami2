//package control;
//
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.net.UnknownHostException;
//
//public class Communicator implements Runnable {
//	
//	int portNr;
//	String hostName;
//	Game game;
//	
//	int state;
//	
//	String fromServer;
//	
//	public Communicator (int portNr, String hostName, Game game ) {
//		this.portNr = portNr;
//		this.hostName = hostName;
//		this.game = game;
//		this.state = 0;
//		
//	}
//	
//	public int getState () {
//		return state;
//	}
//
//
//	@Override
//	public void run() {
//		try (
//				Socket socket = new Socket (hostName, portNr);
//	            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//	            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
//				) {
//			if (state == 0) {
//				fromServer = in.readLine();
//	        	System.out.println(fromServer);
//	        	out.println("neuerClient(" + game.getPlayer().getName() + ").");
//	        	fromServer = in.readLine();
//	        	if (fromServer.startsWith("message(Willkommen ")) {
//	        		System.out.println(fromServer);
//	        		state = 1;
//	        		System.out.println("state: " + 1);
//	        	}
//			}
//			if (state == 1) {
//				fromServer = in.readLine();
//				
//				if (fromServer.startsWith("message")) {
//					System.out.println(fromServer);
//					fromServer = in.readLine();
//				}
//				if (fromServer.equals("spielparameter?")) {   // Spieler 1
//					
//					String spielparameter = ("spielparameter(" + game.getBoard().toString() + ", " + game.getBoard().level + ").");
//					out.println(spielparameter);
//					fromServer = in.readLine();
//					game.setOpponentName(fromServer);
//					System.out.println("opponentName-magic: " + game.getOpponentName());
//					fromServer = in.readLine();
//					Character color = fromServer.charAt(6);
//					
//					game.getPlayer().setColor(color);
//					fromServer = in.readLine();
//					if (fromServer.startsWith("spielstart")) {
//						System.out.println(fromServer.charAt(11));
//						game.setActivePlayer(fromServer.charAt(11));
//						state = 2;
//						System.out.println(game.getPlayer().getName() + " ist in state 2.");
//						System.out.println(fromServer);
//					}
//					
//				} else if (fromServer.startsWith("spielparameter(")) {   // Spieler 2
//					String boardName = fromServer.substring(15, 215);
//	         		System.out.println("boardName: " + boardName);
//	        		String levelString = fromServer.substring(216, 217);
//	        		System.out.println("levelString: " + levelString);
//	        		int intLevel = Integer.parseInt(levelString);
//	        		game.getBoard().setLevel(intLevel);
//	        		Character color = fromServer.charAt(218);
//	        		System.out.println("color: "+ color);
//	        		game.getPlayer().setColor(color);
//	        		game.setBoard(new Board(boardName, intLevel));
//	        		game.setOpponentName(fromServer.substring(220, fromServer.length()-2));
//	        		System.out.println("opponentName: " + fromServer.substring(220, fromServer.length()-2));
//	        		fromServer = in.readLine();
//	        		if (fromServer.startsWith("spielstart")) {
//	        			System.out.println(fromServer.charAt(11));
//	        			game.setActivePlayer(fromServer.charAt(11));
//	        			state = 2;
//	        			System.out.println(game.getPlayer().getName() + " ist in state 2.");
//						System.out.println(fromServer);
//					}
//				}
//			}
//			while (state == 2) {
//				if (game.getActivePlayer() == game.getPlayer().getColorAsChar()) {
//					System.out.println(game.getPlayer().getName() + " ist dran.");
//					int move = game.getPlayer().pickMove();
//					while (!game.getBoard().legalMoves().contains(move)) {
//						move = game.getPlayer().pickMove();
//					}
//		//			System.out.println("gew�hler Zug: " + move);
//		//			System.out.println("Zug(" + (move - (((move-1)/10)*10)-1) + ", " + (move-1)/10 + ").");
//					game.getBoard().placePiece(move, game.getPlayer().color);
//					out.println("zug(" + (move - (((move-1)/10)*10)-1) + ", " + (move-1)/10 + ").");
//					fromServer = in.readLine();
//					System.out.println("nach dem Zug fromServer " + fromServer);
//					if (!fromServer.startsWith("ung")) {
//						System.out.println("g�ltig");
//						game.nextPlayer();
//						
//						
//					}
//				} else {
//					System.out.println(game.getPlayer().getName() + " wartet auf Zug.");
//					fromServer = in.readLine();
//					System.out.println("else: " + fromServer);
//					String newBoard = fromServer.substring(7, 204);
//					System.out.println(newBoard);
//					game.getBoard().readBoard(newBoard);
//					
//				}
//				
//				
//			}
//		
//		}
//	
//		 catch (UnknownHostException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	
//
//	}
//	public void sendMove( String move ) {
//		
//	}
//	
//}
