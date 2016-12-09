package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import control.Board;
import control.KIPlayer;
import control.PlayerColor;

public class GuiTest {
	
	public static void main (String[] args) {
		JFrame window = new JFrame();
		TextField message = new TextField();
		TextArea messageArea = new TextArea(5,10);
		JButton nextMove = new JButton("next Move");

		Board board = new Board("a0a0a0k0f0f0a0a0a0a0a0a0o0k0f0f0p0p0a0a0a0a0o0k0b0b0b0g0g0a0a0c0c0c0b0b0b0g0g0a0a0c0c0c0l0d0d0d0a0a0h0h0i0i0l0d0d0d0m0a0h0h0i0i0l0q0j0j0m0a0a0a0e0e0e0q0j0j0m0a0a0a0e0e0e0r0r0a0a0a0a0a0a0n0n0n0a0a0a0a0", 1);
		KIPlayer player1 = new KIPlayer(PlayerColor.BLACK, board, 2);
		KIPlayer player2 = new KIPlayer(PlayerColor.RED, board, 9);
		
		

		int size = 800;
		BoardPanel boardPanel = new BoardPanel((size/4)*3);
		boardPanel.readBoard(board);
		window.setSize(size, size);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boardPanel.setLayout(new GridLayout(10,10,0,0));
		/*
		for (Rectangle rectangle: boardPanel.allRectangles) {
			if (rectangle.getPlaced()) {	
				rectangle.addMouseListener(new RectangleMouseListener());
			}
		}
		*/
		window.add(boardPanel, BorderLayout.CENTER);
		window.add(message, BorderLayout.SOUTH);
		window.add(messageArea, BorderLayout.EAST);
		window.setVisible(true);
		
		
	

		
		int move;
		int zug = 1;
		boolean finished = false;
		while (!board.legalMoves().isEmpty()) {

			System.out.println("Zug: " + zug);
			int move2 = player2.alphaBetaMin(player2.color, 9, -10000, 10000);
			if (move2 > 0 ) {
				board.placePiece(move2, player2.color);
				board.print();
				boardPanel.readBoard(board);
				System.out.println(board.getScore());
			} else {
				System.out.println("something went wrong with player 2.");
				break;
			}
		//	move = player1.pickRandomMove(board);
			
			move = player1.alphaBetaMax(player1.color, 2, -10000, 10000);
			if (move > 0 ) {
				board.placePiece(move, player1.color);
				board.print();
				boardPanel.readBoard(board);
				System.out.println(board.getScore());
			} else {
				System.out.println("something went wrong with player 1,");
				break;
			}
			
			
			
		
			
			zug++;
		}
		String winner = (board.getScore()>0 ? "Black" : "Red");
		
		System.out.println(winner);
		System.out.println(board.findLines(PlayerColor.BLACK));
		System.out.println(board.findLines(PlayerColor.RED));
		
		
	}	


	


}


class NextMoveListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
	
}


