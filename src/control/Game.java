package control;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.TimerTask;

public class Game {

	private int portNr;
	private String hostname;
	private Board board;
	private Player player;
	public Communicator2 communicator;
	private String opponentName;
	private char activePlayer;
	private int selectedMove;
	private boolean moveNeeded;
	private int state;
	private int move;
	
	private boolean shouldWrite;
	
	private PropertyChangeSupport activePlayerChange = new PropertyChangeSupport(this);
	private PropertyChangeSupport playerColorChange = new PropertyChangeSupport(this);
	private PropertyChangeSupport moveNeededChange = new PropertyChangeSupport(this);
	
	public Game (int portNr, String hostname, int level, Board board, Player player) {
		this.portNr = portNr;
		this.hostname = hostname;
		this.board = board;
		this.board.level = level;
		this.player = player;
		this.communicator = new Communicator2(portNr, hostname, this);
		this.activePlayer = player.getColorAsChar();
		this.state = 0;
		moveNeeded = false;
		shouldWrite = false;
		
	//	communicator.run();
	}
	
	public void setMoveNeeded(boolean needed) {
		moveNeeded = needed;
	}
	
	public boolean getMoveNeeded() {
		return moveNeeded;
	}
	
	public int getMove() {
		return move;
	}
	
	public void setMove(int move) {
		this.move = move;
	}
	
	public void addMoveListener (PropertyChangeListener p) {
		moveNeededChange.addPropertyChangeListener(p);
	}
	
	public void removeMoveListener (PropertyChangeListener p) {
		moveNeededChange.removePropertyChangeListener(p);
	}
	
	public void addActivePlayerChangeListener (PropertyChangeListener p) {
		activePlayerChange.addPropertyChangeListener(p);
	}
	
	public void removeActivePlayerChangeListener (PropertyChangeListener p) {
		activePlayerChange.removePropertyChangeListener(p);
	}
	
	public void addPlayerColorChangeListener (PropertyChangeListener p) {
		playerColorChange.addPropertyChangeListener(p);
	}
	
	public void removePlayerColorChangeListener (PropertyChangeListener p) {
		playerColorChange.removePropertyChangeListener(p);
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public void setActivePlayer(char playerColor) {
		this.activePlayer = playerColor;

	}
	
	public void nextPlayer() {
		if (activePlayer == 'b' ) {
			activePlayer = 'r';
			activePlayerChange.firePropertyChange("activePlayer", 'b', 'r');
		} else if (activePlayer == 'r') {
			activePlayer = 'b';
			activePlayerChange.firePropertyChange("activePlayer", 'r', 'b');
		}
		
	}
	
	public void setSelectedMove(int move) {
		this.selectedMove = move;
	}
	
	public int getSelectedMove() {
		return selectedMove;
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
	
	
	public void pickMove() {
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
	
	public void test() {
		System.out.println("it works!");
	}
	
	public void run() {
		communicator.run();
		
	}
	
	public void handleServerInput(String fromServer) {
		if (fromServer.equals("Kulami?")) {
			communicator.setToServer(("neuerClient(" + getPlayer().getName() + ")."));
			communicator.setShouldWrite(true);
			System.out.println("Kulami empfangen");
		}
		if (fromServer.equals("spielparameter?")) {
			System.out.println("nach Spielparametern gefragt worden.");
			communicator.setToServer("spielparameter(" + getBoard().toString() + ", " + getBoard().level + ").");
			communicator.setShouldWrite(true);
			
		}
		if (fromServer.startsWith("name(")) {
			setOpponentName(fromServer.substring(5, fromServer.length()-2));
		}
		if (fromServer.startsWith("farbe(")) {
			player.setColor(fromServer.charAt(6));
			System.out.println("Farbe SPieler 1 " + player.getColorAsChar());
		}
		if (fromServer.startsWith("spielparemeter(")) {
			String boardName = fromServer.substring(15, 215);
			int intLevel = Integer.parseInt(fromServer.substring(216));
			board.setLevel(intLevel);
			player.setColor(fromServer.charAt(218));
			System.out.println("Farbe Spieler 2: " + player.getColorAsChar());
			board = new Board(boardName, intLevel);
			setOpponentName(fromServer.substring(220, fromServer.length()-2));
		}
		
		while (fromServer.startsWith("spielstart(")) {
		//	System.out.println(player.getColorAsChar() + " spielstart erhalten");
			activePlayer = fromServer.charAt(11);
			state = 1;
			if (activePlayer == player.getColorAsChar()) {
		//		System.out.println("if");
				boolean moveNeededOld = moveNeeded;
				moveNeeded = true;
				while (moveNeeded) {
					
				}
		//		moveNeededChange.firePropertyChange("moveNeeded", moveNeededOld, moveNeeded);
		//		System.out.println("move no longer needed. toServer: " + communicator.toServer + "shouldWrite: " + communicator.shouldWrite);
				communicator.setShouldWrite(true);
			}
			

		}
		
		//the game has started
		
		//move is not legal or it wasnt our turn
		if (fromServer.startsWith("ung")) {
			System.out.println(fromServer);
		/*	if (fromServer.contains("nicht am Zug")) {
				nextPlayer();
			}*/
		}
		
		//move was legal
		if (fromServer.startsWith("g\u00FDltig")) {
			board.placePiece(move, player.color);
			nextPlayer();
		}
		
		if (fromServer.startsWith("zug")) {
			board.readBoard(fromServer.substring(4, 204));
			System.out.println(fromServer.substring(4,204));
			moveNeeded = true;
			communicator.setShouldWrite(true);
		}
		
		if (fromServer.startsWith("spielende")) {
			communicator.setState(-1);
			System.out.println("Spiel beendet.");
		}
		
	}
	
	
}
