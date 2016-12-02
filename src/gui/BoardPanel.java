package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


import control.Board;
import control.PlayerColor;

public class BoardPanel extends JPanel {
	
	Rectangle[] allRectangles;
	
	
	
	public BoardPanel(int size) {
		this.setSize(size, size);
		setLayout(new GridLayout(10, 10, 0, 0));
		allRectangles = new Rectangle[100];
		for (int i= 1; i <=100; i++) {
			Rectangle rectangle = new Rectangle(i, false, false, false, false);
			allRectangles[i-1] = rectangle;
			this.add(rectangle);
		}
		this.setVisible(true);	
	}
	
	public BoardPanel(int size, boolean linesVisible) {
		this.setSize(size, size);
		setLayout(new GridLayout(10, 10, 0, 0));
		allRectangles = new Rectangle[100];
		if (linesVisible) {
			for (int i= 1; i <=100; i++) {
				Rectangle rectangle = new Rectangle(i, true, true, true, true);
				allRectangles[i-1] = rectangle;
				this.add(rectangle);
			}
		} else {
			for (int i= 1; i <=100; i++) {
				Rectangle rectangle = new Rectangle(i, false, false, false, false);
				allRectangles[i-1] = rectangle;
				this.add(rectangle);
			}
		}
		this.setVisible(true);
	}
	
	
	public void readBoard( Board board) {
		for (int i = 1; i<=100; i++) {
			int index = i-1;
			if (board.getTile(i).equals("a")) {
				allRectangles[index].setPlaced(false);
			}
			if (board.state[board.ownerFromPosition(i)].equals("1")) {
				allRectangles[index].setColor(Color.BLACK);
			} else if (board.state[board.ownerFromPosition(i)].equals("2")) {
				allRectangles[index].setColor(Color.RED);
			}
			if (!board.getTile(i).equals("a") && i<=10) {
				allRectangles[index].setUp(true);
			}	else if (!board.getTile(i).equals("a") && !board.getTile(i).equals(board.getTile(i-10))) {
				allRectangles[index].setUp(true);
			}
			if (!board.getTile(i).equals("a") && board.xFromPosition(i)==0 ) {
				allRectangles[index].setLeft(true);
			}
			else if (!board.getTile(i).equals("a") && !board.getTile(i).equals(board.getTile(i-1))) {
				allRectangles[index].setLeft(true);
			}
			if (!board.getTile(i).equals("a") && i>=90) {
				allRectangles[index].setDown(true);
			}	else if (!board.getTile(i).equals("a") && !board.getTile(i).equals(board.getTile(i+10))) {
				allRectangles[index].setDown(true);
			}
			if (!board.getTile(i).equals("a") && board.xFromPosition(i)==9) {
				allRectangles[index].setRight(true);
			}	else if (!board.getTile(i).equals("a") && !board.getTile(i).equals(board.getTile(i+1))) {
				allRectangles[index].setRight(true);
			}
		}
		repaint();
	}
	
	public void addMouseListeners (MouseListener ml) {
		for (int i = 1; i<=100; i++) {
			allRectangles[i-1].addMouseListener(ml);
		}
	}
	
	public void showMove (int position, PlayerColor playerColor) {	
	}
	
}