package control;

import javax.swing.SwingUtilities;

import gui.GameFrame;

public class CommTest {
	
	public static void main(String[] args) {
		Board board = new Board("a0a0a0k0f0f0a0a0a0a0a0a0o0k0f0f0p0p0a0a0a0a0o0k0b0b0b0g0g0a0a0c0c0c0b0b0b0g0g0a0a0c0c0c0l0d0d0d0a0a0h0h0i0i0l0d0d0d0m0a0h0h0i0i0l0q0j0j0m0a0a0a0e0e0e0q0j0j0m0a0a0a0e0e0e0r0r0a0a0a0a0a0a0n0n0n0a0a0a0a0", 1);
		
		Game game = new Game(2021, "localhost", 1, board, new Player("Jon", board));
		Game game2 = new Game(2021, "localhost", 1, board, new KIPlayer("Richard", 1, board));
	
	/*	Thread t1 = new Thread(game.getCommunicator());
		t1.start();
		*/
	
		SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					GameFrame gameFrame1 = new GameFrame(game);
					gameFrame1.init();
				}	
		});

		
		/*
		Thread t2 = new Thread(game2.getCommunicator());
		t2.start();
		*/
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GameFrame gameFrame2 = new GameFrame(game2);
				gameFrame2.init();
			}	
		});				
			
		
		
	}

}
