package test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

import javax.swing.*;

public class CircleTest extends JPanel{
	
	public void paint(Graphics g) {
		super.paint(g);
		paintStuff(g);
	}
	
	public void paintStuff(Graphics g) {
		Stroke stroke = new BasicStroke(4f);
		Graphics2D gd = (Graphics2D) g;
		gd.setStroke(stroke);
		gd.setColor(Color.RED);
		gd.draw(new Rectangle(30,50,50,50));
		
	}
	
	public static void main (String[] args) {
		
		JFrame frame = new JFrame("CireleTest");
		JPanel panel = new JPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);

		CircleTest ct = new CircleTest();
		
		panel.add(ct);
		frame.add(panel);
		frame.setVisible(true);
		
	}

}
