package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RectForBuilder extends JPanel {

	private int horizontal, vertical, horStart, vertStart;
	String name;
	
		
	public RectForBuilder ( String name, int horizontal, int vertical, int horStart, int vertStart) {
		this.name = name;
		this.horizontal = horizontal;
		this.vertical = vertical;
		this.horStart = horStart;
		this.vertStart = vertStart;
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		g.drawRect(horStart, vertStart, horizontal, vertical);
		g.drawString(name, horizontal/3, vertical/2);
	}

	public void rotate () {
		int swap = horizontal;
		horizontal = vertical;
		vertical = swap;
		repaint();
	}
	public static void main (String[] args) {
		JFrame f = new JFrame("Test");
		f.setSize(1400,200);
		f.setResizable(false);
		JPanel panel = new JPanel();

		RectForBuilder p = new RectForBuilder("b", 90, 60, 0, 0);
		RectForBuilder r = new RectForBuilder("r", 60,90,100,0);
		panel.setSize(1000,1000);
		panel.setLayout(new FlowLayout());
		panel.add(p);

		
		f.add(panel);
		f.setVisible(true);;
	}
	
}
