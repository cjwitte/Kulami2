package test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PropTest {

	public static void main(String[] args) {
		PropModel propModel = new PropModel();
		PropView propView = new PropView(propModel);
		JFrame frame = new JFrame("Props");
		JButton btn = new JButton("Fire!");
		frame.setLayout(new BorderLayout());
		btn.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent arg0) {
				propModel.updateText("1");
				
			}
		});
		frame.add(btn, BorderLayout.NORTH);
		frame.add(propView, BorderLayout.CENTER);
		frame.add(btn, BorderLayout.SOUTH);
		frame.setVisible(true);
		

	}

}
