package control;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.UnsupportedOperationException;

public class Player {
	
	private int score;
	public PlayerColor color;
	private Board board;
	private String name;
	private int move;
	
	public Player (String name) {
		this.name = name;
		this.color = PlayerColor.RED;
		this.move = 0;
	}
	
	public Player (String name, Board board) {
		this.name = name;
		this.board = board;
		this.color = PlayerColor.RED;
	}

	public Player(PlayerColor color, Board board) {
		this.score = 0;
		this.color = color;
		this.board = board;
	}
	
	public int pickMove() {
		throw new UnsupportedOperationException();
	}
	
	public String getName() {
		return name;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setColor (PlayerColor color) {
		this.color = color;
	}
	
	public void setColor (Character color) {
		if (color.equals('r')) {
			this.color = PlayerColor.RED;
		} else if (color.equals('b')) {   
			this.color = PlayerColor.BLACK;
		}
	}
	
	/**
	 * 
	 * @return returns the corresponding int.
	 * 1 for Black, 2 for Red
	 */
	public int getColorAsInt () {
		return color.toInt();
	}
	
	public char getColorAsChar () {
		return color.toChar();
	}
	
	public String getColorAsString() {
		return color.toString();
	}
	
	public Color getColor() {
		return color.toColor();
	}
	

	
}
