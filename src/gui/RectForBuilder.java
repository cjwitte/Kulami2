package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RectForBuilder extends JComponent {

	private int horizontal, vertical;
	String name;
	private Graphics g;
		
	public RectForBuilder ( String name, int horizontal, int vertical ) {
		this.name = name;
		this.horizontal = horizontal;
		this.vertical = vertical;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g2);
		g2.drawRect(0, 0, horizontal, vertical);
	}
	
	public static void main (String[] args) {
		JFrame f = new JFrame("Hallo");
		RectForBuilder r = new RectForBuilder("name", 200, 200);
		f.add(r);
		f.setSize(1000, 1000);
		f.setVisible(true);
		
	}
	
}
