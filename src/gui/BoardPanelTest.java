package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import control.Board;

public class BoardPanelTest extends JFrame {

	public static void main(String[] args) {

		Board board = new Board("a0a0a0k0f0f0a0a0a0a0a0a0o0k0f0f0p0p0a0a0a0a0o0k0b0b0b0g0g0a0a0c0c0c0b0b0b0g0g0a0a0c0c0c0l0d0d0d0a0a0h0h0i0i0l0d0d0d0m0a0h0h0i0i0l0q0j0j0m0a0a0a0e0e0e0q0j0j0m0a0a0a0e0e0e0r0r0a0a0a0a0a0a0n0n0n0a0a0a0a0", 1);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(680, 500);

		
		
		
		BoardPanel bp = new BoardPanel(500);
		frame.add(bp, BorderLayout.CENTER);
		bp.readBoard(board);
		bp.repaint();
		frame.setVisible(true);
		board.print();
	}

}
