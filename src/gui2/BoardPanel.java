package gui2;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import gui.Rectangle;


import java.awt.GridLayout;

public class BoardPanel extends JPanel {
	
	public BoardPanel(int size) {
		this.setSize(size, size);
		setLayout(new GridLayout(10, 10, 0, 0));
		for (int i= 1; i <=100; i++) {
			
			Rectangle rectangle = new Rectangle(i);
			Border empty = BorderFactory.createEmptyBorder();
			rectangle.setBorder(empty);
			this.add(rectangle);
		}
		
	}

}
