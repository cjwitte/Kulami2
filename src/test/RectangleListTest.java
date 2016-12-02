package test;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.Rectangle;

public class RectangleListTest {

	public static void main (String [] args) {
		JFrame f = new JFrame();
		JPanel p = new JPanel();
		Rectangle r = new Rectangle("lk", 40, 40,false);
		p.add(r);
		f.add(p);
		f.setVisible(true);
	}
	
}
