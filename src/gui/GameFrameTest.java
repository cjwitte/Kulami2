package gui;

import control.Board;
import control.Game;
import control.Player;

public class GameFrameTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GameFrame gameFrame = new GameFrame(new Game(2021, "localhost", 1, new Board("a0a0a0k0f0f0a0a0a0a0a0a0o0k0f0f0p0p0a0a0a0a0o0k0b0b0b0g0g0a0a0c0c0c0b0b0b0g0g0a0a0c0c0c0l0d0d0d0a0a0h0h0i0i0l0d0d0d0m0a0h0h0i0i0l0q0j0j0m0a0a0a0e0e0e0q0j0j0m0a0a0a0e0e0e0r0r0a0a0a0a0a0a0n0n0n0a0a0a0a0", 1), new Player("Jon")));
	
		
		gameFrame.setVisible(true);
		gameFrame.getGame().getBoard().placePiece(4, gameFrame.getGame().getPlayer().color);
		gameFrame.boardPanel.readBoard(gameFrame.getGame().getBoard());
		System.out.println(gameFrame.getGame().getBoard().legalMoves());
		gameFrame.repaint();
	}

}
