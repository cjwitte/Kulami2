//package test;
//
//import control.Board;
//import control.Communicator2;
//import control.Player;
//import gui.GameFrame;
//
//public class GameTest {
//
//
//	private int portNr;
//	private String hostname;
//	private Board board;
//	private Player player;
//	public Communicator2 communicator;
//	private String opponentName;
//	private char activePlayer;
//	private int selectedMove;
//	private boolean moveNeeded;
//	private int state;
//	private int move;
//	
//	private boolean opponentNameIsSet;
//	private boolean colorIsSet;
//	private GameFrame gameFrame;
//	private boolean myTurn;
//	
//	private boolean shouldWrite;
//
//	
//	public GameTest (int portNr, String hostname, int level, Board board, Player player) {
//		this.portNr = portNr;
//		this.hostname = hostname;
//		this.board = board;
//		this.board.level = level;
//		this.player = player;
//		this.communicator = new Communicator2(portNr, hostname, this);
//		this.activePlayer = player.getColorAsChar();
//		this.state = 0;
//		moveNeeded = false;
//		shouldWrite = false;
//	}
//	
//	public Player getPlayer() {
//		return player;
//	}
//	
//	public Communicator2 getCommunicator() {
//		return communicator;
//	}
//
//}
