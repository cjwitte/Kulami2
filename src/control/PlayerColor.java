package control;

import java.awt.Color;

public enum PlayerColor {
	BLACK, RED;
	
	public int toInt() {
		switch (this) {
		case BLACK:
			return 1;
		case RED:
			return 2;
		default:
			return 0;
		}
	}
	
	public String toString() {
		switch (this) {
		case BLACK:
			return "1";
		case RED:
			return "2";
		default:
			return "";	
		}
	}
	
	public char toChar() {
		switch(this) {
		case BLACK:
			return 'b';
		case RED:
			return 'r';
		default:
			return ' ';
		}
	}
	
	public PlayerColor reverseColor () {
		switch(this) {
		case BLACK:
			return PlayerColor.RED;
		case RED:
			return PlayerColor.BLACK;
		default:
			return PlayerColor.BLACK;
		}
	}
	
	public Color toColor () {
		switch(this) {
		case BLACK:
			return Color.BLACK;
		case RED:
			return Color.RED;
		default:
			return Color.WHITE;
		}
	}
}
