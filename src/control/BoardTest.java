//package control;
//
//import javax.swing.JFrame;
//
//import gui.BoardPanel;
//
//public class BoardTest {
//	
//	public static void main (String[] args) {
//	/*	Board board = new Board( 1);
//		board.setTile(board.c, 1,3);
//		board.setTile(board.d, 7, 7);
//		board.setTile(board.e, 7, 4);
//		board.setTile(board.k, 3, 0);
//		board.setTile(board.f, 4, 0);
//		board.setTile(board.o, 2, 1);
//		board.setTile(board.b, 4, 2);
//		board.p.rotate();
//		board.setTile(board.p, 1, 6);
//		
//		board.setTile(board.g, 7, 2);
//		*/
//
//		
//		Board board = new Board("a0a0a0k0f0f0a0a0a0a0a0a0o0k0f0f0p0p0a0a0a0a0o0k0b0b0b0g0g0a0a0c0c0c0b0b0b0g0g0a0a0c0c0c0l0d0d0d0a0a0h0h0i0i0l0d0d0d0m0a0h0h0i0i0l0q0j0j0m0a0a0a0e0e0e0q0j0j0m0a0a0a0e0e0e0r0r0a0a0a0a0a0a0n0n0n0a0a0a0a0", 1);
//
//		board.print();
//
//		System.out.println("Score: " + board.getScore());
//
//		KIPlayer player1 = new KIPlayer(PlayerColor.BLACK, board, 2);
//		KIPlayer player2 = new KIPlayer(PlayerColor.RED, board, 9);
//		
//		
//		int move;
//		int zug = 1;
//		boolean finished = false;
//		while (!board.legalMoves().isEmpty()) {
//
//			System.out.println("Zug: " + zug);
//			int move2 = player2.alphaBetaMin(player2.color, 9, -10000, 10000);
//			if (move2 > 0 ) {
//				board.placePiece(move2, player2.color);
//				board.print();
//				System.out.println(board.getScore());
//			} else {
//				System.out.println("something went wrong with player 2.");
//				break;
//			}
//		//	move = player1.pickRandomMove(board);
//			
//			move = player1.alphaBetaMax(player1.color, 2, -10000, 10000);
//			if (move > 0 ) {
//				board.placePiece(move, player1.color);
//				board.print();
//				System.out.println(board.getScore());
//			} else {
//				System.out.println("something went wrong with player 1,");
//				break;
//			}
//			
//			
//			
//		
//			
//			zug++;
//		}
//		String winner = (board.getScore()>0 ? "Black" : "Red");
//		System.out.println("And the winner is: " + winner);
//		System.out.println("area: " + board.findArea(74));
//		System.out.println(board.state[24*2-1]);
//		JFrame frame = new JFrame("Test");
//		frame.setSize(750,750);
//		BoardPanel bp = new BoardPanel(500);
//		bp.readBoard(board);
//		frame.add(bp);
//		frame.setVisible(true);
//		
//		/*
//		
//		int move = player1.alphaBetaMax(player1.color, 6, -10000, 10000);
//		board.placePiece(move, player1.color);
//		board.print();
//		System.out.println();
//		int move2 = player2.alphaBetaMin(player1.color, 6, -10000, 10000);
//		board.placePiece(move2, player2.color);
//		board.print();
//		System.out.println("Fertig. Score: " + board.getScore());
//		
//		*/
//		
//		
//		/*int move;
//		int zug = 1;
//		boolean finished = false;
//		while (!board.legalMoves().isEmpty() || !finished) {
//
//			System.out.println("Zug: " + zug);
//			move = player2.max(board, player2.color, 6);
//			board.placePiece(move, player2.color);
//			board.print();
//			System.out.println(board.getScore());
//
//			move = player1.pickRandomMove(board);
//			if (move != 0) {
//				board.placePiece(move, player1.color);
//				board.print();
//				System.out.println(board.getScore());
//			} else {
//				System.out.println("Spiel beendet");
//				finished = true;
//			}
//			
//
//			zug++;
//		}
//		String winner = (board.getScore()>0 ? "Black" : "Red");
//		System.out.println("And the winner is: " + winner);
//		*/
//		
//	/*	for (int i = 0; i < 4; i ++) {
//			int move = player1.max(board, player1.color, 4);
//			System.out.println(move);
//			board.placePiece(move, player1.color);
//			board.print();
//			System.out.println("Score: " + board.getScore());
//			int move2 = player2.min(board, player2.color, 4);
//			board.placePiece(move2, player2.color);
//			System.out.println(move2);
//			board.print();
//			System.out.println("Score: " + board.getScore());
//		}*/
//			
//		
//		//game of random moves
//		/*
//		Board board = new Board("a0a0a0k0f0f0a0a0a0a0a0a0o0k0f0f0p0p0a0a0a0a0o0k0b0b0b0g0g0a0a0c0c0c0b0b0b0g0g0a0a0c0c0c0l0d0d0d0a0a0h0h0i0i0l0d0d0d0m0a0h0h0i0i0l0q0j0j0m0a0a0a0e0e0e0q0j0j0m0a0a0a0e0e0e0r0r0a0a0a0a0a0a0n0n0n0a0a0a0a0", 1);
//		KIPlayer player = new KIPlayer(Color.BLACK, board, 2);
//		KIPlayer player2 = new KIPlayer(Color.RED, board, 2);
//		int runde = 1;
//		while (board.legalMoves().size()!=0) {
//			System.out.println("Runde: " + runde);
//			int move = player.pickRandomMove(board);
//			if (move !=0) {
//				player.submitMove(move, board);
//				board.updateScore();
//				board.print();
//				System.out.println("Score: " + board.getScore());
//			} else {
//				System.out.println("I could not find a legal Move. Final Score: " + board.getScore());
//			}
//			int move2 = player2.pickRandomMove(board);
//			if (move2!=0) {
//				player2.submitMove(move2, board);
//				board.updateScore();
//				board.print();
//				System.out.println("Score: " + board.getScore());
//			} else { 
//				System.out.println("I could not find a legal Move. Final Score: " + board.getScore());
//			}
//			runde++;
//		}*/
//		
//		
//		//randomMoves
//	/*	player.submitMove(player.pickRandomMove(board), board);
//		board.updateScore();
//		board.print();
//		
//		player2.submitMove(player2.pickRandomMove(board), board);
//		board.updateScore();
//		board.print();
//		
//		System.out.println(board.legalMoves());
//
//		
//		System.out.println(player.max(player.getColor(), player.getStufe()));
//		*/
//
//	//	System.out.println(player.max(Color.BLACK.toInt(), 1));
//		
//	}
//
//}
