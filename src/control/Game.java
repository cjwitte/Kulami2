package control;

import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import gui.GameFrame;
import gui.MainFrame;
import javafx.beans.InvalidationListener;


public class Game extends Observable {

	private int portNr;
	private String hostname;
	private Board board;
	private Player player;
	public Communicator2 communicator;
	private String opponentName;
	private char activePlayer;
	private boolean moveNeeded;
	private int level;
	private int score;
	private int move;
	
	private boolean opponentNameIsSet;
	private boolean colorIsSet;
	private GameFrame gameFrame;

	private boolean myTurn;
	
	private boolean shouldWrite;
	Thread gameThread;
	
	public Game (int portNr, String hostname, int level, Board board, Player player) {
		this.portNr = portNr;
		this.hostname = hostname;
		this.board = board;
		this.level = level;
		this.player = player;
		this.communicator = new Communicator2(portNr, hostname, this);
		this.activePlayer = player.getColorAsChar();
		moveNeeded = false;
		shouldWrite = false;
		System.out.println("game with Player " + player.getName() + "constructed");
		GameFrame gameFrame = new GameFrame(this);
		gameFrame.setVisible(false);
		gameThread = new Thread(communicator);
		gameThread.start();
		System.out.println("game with Player " + player.getName() + "started");
	}
	
	public void setGameFrame(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}
	
	public GameFrame getGameFrame () {
		return gameFrame;
	}
	
	public boolean getMyTurn() {
		return myTurn;
	}
	
	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}

	public void setActivePlayer(char playerColor) {
		this.activePlayer = playerColor;
	}
	
	public void nextPlayer() {
		if (activePlayer == 'b' ) {
			activePlayer = 'r';
			System.out.println("nextPlayer: r");
		} else if (activePlayer == 'r') {
			activePlayer = 'b';
			System.out.println("nextPlayer: b");
		}
		
	}
	
	public char getActivePlayer() {
		return activePlayer;
	}
	
	public void setOpponentName (String name) {
		this.opponentName = name;
	}
	
	public String getOpponentName () {
		return opponentName;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public boolean getShouldWrite () {
		return shouldWrite;
	}
	
	public void setShouldWrite (boolean bool) {
		shouldWrite = bool;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Communicator2 getCommunicator() {
		return communicator;
	}
	
	public void run() {
		Thread thread = new Thread(communicator);
		thread.start();	
		if (activePlayer == player.getColorAsChar()) {
			System.out.println("Move needed");
			moveNeeded = true;
		}
	}
	
	public void handleServerInputInGame (String fromServer) {
		System.out.println("in Game");
		if (fromServer.startsWith("spielstart(")) {
			activePlayer = fromServer.charAt(11);
			System.out.println(player.getName());
			System.out.println("activePlayer: " + activePlayer);
			System.out.println("player.getColorAsChar(); " + player.getColorAsChar());
			
			if (activePlayer == player.getColorAsChar()) {
				myTurn = true;
				System.out.println("Move needed: " + player.getName());
				if (player instanceof KIPlayer) {
					
						move = getPlayer().pickMove();
						int y = getBoard().yFromPosition(move);
						int x = getBoard().xFromPosition(move);

						board.placePiece(move, getPlayer().color);
						
						communicator.setToServer("zug("+ x + ", " + y + ").");
						SwingUtilities.invokeLater( new Runnable() {
							public void run() {
								gameFrame.revalidate();
							}
						});
					
				} else {
						System.out.println("else no KI");
						moveNeeded = true;
						setChanged();
						notifyObservers();
				}
			}
			else {
				myTurn = false;
			}
			return;
		}
		if (fromServer.startsWith("g\u00FDltig")) {
			myTurn = false;
			nextPlayer();
			
		}
		
		if (fromServer.startsWith("zug")) {
			move = board.findMove(fromServer.substring(4, 204));
			board.placePiece(move, player.color.reverseColor());
	//		board.readBoard(fromServer.substring(4, 204));
			myTurn = true;
			nextPlayer();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					gameFrame.revalidate();
				}
			});
			
				
			if (player instanceof KIPlayer) {
				move = getPlayer().pickMove();
				if (move!=-1) {
					getBoard().placePiece(move, getPlayer().color);
					int y = getBoard().yFromPosition(move);
					int x = getBoard().xFromPosition(move);
					communicator.setToServer("zug("+ x + ", " + y + ").");
					SwingUtilities.invokeLater( new Runnable() {
					public void run() {
						gameFrame.revalidate();
							}
					});
				}
			} else {
				moveNeeded = true;
				setChanged();
				notifyObservers();
			}
			
		}
		
	}
	
	public void handleServerInput(String fromServer) {
		if (fromServer.equals("Kulami?")) {
			communicator.setToServer(("neuerClient(" + getPlayer().getName() + ")."));	
		}
		if (fromServer.equals("spielparameter?")) {
			communicator.setToServer("spielparameter(" + getBoard().toString() + ", " + getBoard().level + ").");
			System.out.println("spielparameter geschickt");
		}
		if (fromServer.startsWith("name(")) {
			setOpponentName(fromServer.substring(5, fromServer.length()-2));
			opponentNameIsSet = true;
		}
		if (fromServer.startsWith("farbe(")) {
			player.setColor(fromServer.charAt(6));
			System.out.println("spieler " + player.getName() + " " + player.getColorAsChar());
			colorIsSet = true;
		}
		if (fromServer.startsWith("spielparameter(")) {
			String boardName = fromServer.substring(15, 215);
			System.out.println(fromServer);
			System.out.println("board: " + boardName);
			System.out.println(fromServer.substring(216,217));
			int intLevel = Integer.parseInt(fromServer.substring(216,217));
			level = intLevel;
			System.out.println("farbe: " + fromServer.charAt(218));
			player.setColor(fromServer.charAt(218));
			board = new Board(boardName);
			board.setLevel(intLevel);
			System.out.println("Gegner: " + fromServer.substring(220, fromServer.length()-2));;
			setOpponentName(fromServer.substring(220, fromServer.length()-2));
			opponentNameIsSet = true;
			colorIsSet = true;
		}
		
		if (fromServer.startsWith("spielstart(")) {
			activePlayer = fromServer.charAt(11);
			if (activePlayer == player.getColorAsChar()) {
				System.out.println("Move needed");
				moveNeeded = true;
				notifyObservers();
			}
			return;
		}

		
		//the game has started
		
		//move is not legal or it wasnt our turn
		if (fromServer.startsWith("ung")) {
		}
		
		//move was legal
		if (fromServer.startsWith("g\u00FDltig")) {
			board.placePiece(move, player.color);
			nextPlayer();
		}
		
		if (fromServer.startsWith("zug")) {
			String newBoard = fromServer.substring(4, 204);
			board.findMove(newBoard);
			board.readBoard(fromServer.substring(4, 204));
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					gameFrame.revalidate();
				}
			});
			moveNeeded = true;
			notifyObservers();
		}
		
		if (fromServer.startsWith("spielende")) {
			System.out.println("Spiel beendet.");
		}
		
	}



	
	
}
