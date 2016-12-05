package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TilePanel extends JPanel{
    
    public TilePanel () {
    	Dimension d = new Dimension(1100,120);
    	setPreferredSize(d);
    	repaint();
    	setVisible(true);
    	addMouseListener (new TilePanelMouseListener());
        
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.drawRect(10, 10, 90, 60);
    	g.drawOval(20, 15, 20, 20);
    	g.drawOval(45, 15, 20, 20);
    	g.drawOval(70, 15, 20, 20);
    	g.drawOval(20, 40, 20, 20);
    	g.drawOval(45, 40, 20, 20);
    	g.drawOval(70, 40, 20, 20);
    	
    	g.drawRect(110, 10, 90, 60);
    	g.drawOval(120, 15, 20, 20);
    	g.drawOval(145, 15, 20, 20);
    	g.drawOval(170, 15, 20, 20);
    	g.drawOval(120, 40, 20, 20);
    	g.drawOval(145, 40, 20, 20);
    	g.drawOval(170, 40, 20, 20);
    	
    	g.drawRect(210, 10, 90, 60);
    	g.drawOval(220, 15, 20, 20);
    	g.drawOval(245, 15, 20, 20);
    	g.drawOval(270, 15, 20, 20);
    	g.drawOval(220, 40, 20, 20);
    	g.drawOval(245, 40, 20, 20);
    	g.drawOval(270, 40, 20, 20);
    	
    	g.drawRect(310, 10, 90, 60);
    	g.drawOval(320, 15, 20, 20);
    	g.drawOval(345, 15, 20, 20);
    	g.drawOval(370, 15, 20, 20);
    	g.drawOval(320, 40, 20, 20);
    	g.drawOval(345, 40, 20, 20);
    	g.drawOval(370, 40, 20, 20);
    	
    	
    	
    	g.drawRect(410, 10, 60, 60);
    	g.drawOval(417, 15, 20, 20);
    	g.drawOval(445, 15, 20, 20);
    	g.drawOval(417, 40, 20, 20);
    	g.drawOval(445, 40, 20, 20);
    	
    	
    	g.drawRect(480, 10, 60, 60);
    	g.drawOval(487, 15, 20, 20);
    	g.drawOval(515, 15, 20, 20);
    	g.drawOval(487, 40, 20, 20);
    	g.drawOval(515, 40, 20, 20);
    	
    	g.drawRect(550, 10, 60, 60);
    	g.drawOval(557, 15, 20, 20);
    	g.drawOval(585, 15, 20, 20);
    	g.drawOval(557, 40, 20, 20);
    	g.drawOval(585, 40, 20, 20);
    	
    	g.drawRect(620, 10, 60, 60);
    	g.drawRect(690, 10, 60, 60);
    	
    	g.drawRect(760, 10, 30, 90);
    	g.drawRect(800, 10, 30, 90);
    	g.drawRect(840, 10, 30, 90);
       	g.drawRect(880, 10, 30, 90);
    	
    	
    	g.drawRect(920, 10, 60, 30);
    	g.drawRect(990, 10, 60, 30);
    	g.drawRect(1060, 10, 60, 30);
    	g.drawRect(1130, 10, 60, 30);

        
    	
    	
    }
    
    class TilePanelMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			int x = arg0.getX();
			int y = arg0.getY();
			if (x >= 10 && x <= 100) {
				System.out.println("B");
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    	
    	
    }

    public static void main (String[] args) {
    	JFrame f = new JFrame("sdf");
    	TilePanel t = new TilePanel();
    	
  //  	t.repaint();
  //  	f.setLayout(new BorderLayout());
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    	f.add(new TilePanel());
    	f.pack();
    	f.setVisible(true);
    }
    
    
    

}
