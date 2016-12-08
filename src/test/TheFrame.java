package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TheFrame extends JFrame {
	
	private TheRunnable runnable;
	JButton btn;
	JPanel panel;
	
	public void init() {
		this.btn = new JButton("shoot");
		this.panel = new JPanel();
		panel.setSize(new Dimension(350,350));
		panel.setBorder(BorderFactory.createDashedBorder(Color.BLUE));
		Thread thread = new Thread(runnable);
		thread.start();
		add(btn, BorderLayout.CENTER);
		
		add(panel, BorderLayout.NORTH);
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				runnable.setToWrite("shoot");
				
			}
		});
		
		panel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				runnable.setToWrite("shit");
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
			
		});
		
	}
	
	public TheFrame (TheRunnable runnable) {
		super();
		this.runnable = runnable;
		setSize(500,500);
		setVisible(true);
	}

}
