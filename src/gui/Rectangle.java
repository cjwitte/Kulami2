package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Rectangle extends JPanel {
	
	private int horizontal, vertical;
	private Color color;
	private int size;
	int number;
	String name;
	private boolean active;
	private Graphics g;
	boolean up, right, down, left;
	boolean placed;
	
	/*
	public Rectangle (int horizontal, int vertical, Color color) {
		this.horizontal = horizontal;
		this.vertical = vertical;
		this.color = color;
	}*/
	
	public Rectangle (int number, boolean up, boolean right, boolean down, boolean left) {
		this.number = number;
		this.up = up;
		this.right = right;
		this.down = down;
		this.left = left;
		this.name = String.valueOf(number);
		this.active = false;
		this.placed = true;
		this.color = Color.GRAY;
		
	}
	
	public Rectangle (String name) {
		this.number = 0;
		this.active = false;
		this.name = name;
	}
	
	public Rectangle (String name, int horizontal, int vertical, boolean placed) {
		this.name = name;
		this.horizontal = horizontal;
		this.vertical = vertical;
		this.placed = placed;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g2);
		if (up) {
			g2.drawLine(0, 0, this.getWidth(), 0);
		}
		if (right) {
			g2.drawLine(this.getWidth()-1, 0, this.getWidth()-1, this.getHeight());
		}
		if (down) {
			g2.drawLine(0, this.getHeight()-1, this.getWidth(), this.getHeight()-1);
		}
		if (left) {
			g2.drawLine(0, 0, 0, this.getHeight());
		}
		if (placed) {
			g2.setPaint(color);
			g.fillOval(this.getWidth()/4 , this.getHeight()/4 , this.getWidth()/2, this.getHeight()/2);
		} 
		
	}
	
	
	public void setColor (Color color) {
		this.color = color;
	}
	
	
	public void setUp(boolean up) {
		this.up = up;
	}
	
	public void setRight (boolean right) {
		this.right = right;
	}
	
	public void setDown (boolean down) {
		this.down = down;
	}
	
	public void setLeft (boolean left) {
		this.left = left;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public void setActive(boolean b) {
		this.active = b;
	}
	
	public String toString() {
		String name = Integer.toString(number);
		return name;
	}

	public void toggle() {
		active = !active;
	}
	
	public void setPlaced(boolean placed) {
		this.placed = placed;
	}
	
	public boolean getPlaced() {
		return placed;
	}
	
	public int getNumber () {
		return number;
	}
}
