package test;

import javax.swing.SwingUtilities;

public class TheApp {
	
	public static void main (String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TheFrame frame = new TheFrame(new TheRunnable("test"));
				frame.init();
			}
		});
		
	}

}
