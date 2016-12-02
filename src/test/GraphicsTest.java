package test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GraphicsTest extends JFrame {
	
	Stroke stroke = new BasicStroke(4f);

	public GraphicsTest () {
		super("Test");
		setSize(480,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}
	
	void drawRectangles (Graphics g) {
		Graphics2D gd = (Graphics2D) g;
		gd.setStroke(stroke);
		gd.setColor(Color.RED);
		gd.draw(new Rectangle(30,50,50,50));
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		drawRectangles(g);
		
	}
	
	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				GraphicsTest gt = new GraphicsTest();
				gt.setVisible(true);;
				
			}
		});
	}
}
