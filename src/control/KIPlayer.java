package control;

import java.util.ArrayList;
import java.util.Random;

public class KIPlayer extends Player {
	Board board;
	String name;
	
	public KIPlayer(PlayerColor color, Board board) {
		super(color, board);
	}

	public KIPlayer(String name, int stufe, Board board) {
		super(name);
		this.stufe = stufe;
		this.board = board;
	}
	
	public KIPlayer(PlayerColor color, Board board, int stufe) {
		super(color, board);
		this.board = board;
		this.stufe = stufe;
	}
	
	private int stufe;
	
	public void setColor ( PlayerColor color) {
		this.color = color;
	}
	
	public int getStufe() {
		return stufe;
	}
	
	public int pickRandomMove(Board board) {
		Random random = new Random();
		if (board.legalMoves().isEmpty()) {
			System.out.println("keinen legalen Move gefunden.");
			return 0;
		} else {
			int selectedMove = board.legalMoves().get(random.nextInt(board.legalMoves().size()));
			return selectedMove;
		}
		
	}
	
/*	public int maxMove ( Board board, int depth ) {
		if ( board.legalMoves().size()== 0 || depth == 0) {
			return board.getScore();
		}
		else {
			int bestMove = -10000;
			ArrayList<Integer> legalMoves = board.legalMoves();
			for ( Integer move: legalMoves) {
				int max = minMove(board.placePiece(move, Color.RED), depth-1); //testMove()
				if (move>bestMove) {
					bestMove = max;
				}
			}
			return bestMove;
		}
	}
	
	
	public int minMove ( Board board, int depth) {
		int bestMove = 10000;
		ArrayList<Integer> legalMoves = board.legalMoves();
		for (Integer move: legalMoves) {
			int min = maxMove(board.placePiece(move, Color.BLACK), depth - 1);
			if (move < bestMove) {
				bestMove = min;
			}
		}
		return bestMove;
	}
	
	*/
	
	int pickedMove;
	public int max (Board board, PlayerColor color, int depth) {
		ArrayList<Integer> Moves = board.legalMoves();
		if (depth == 0 || Moves.size() == 0) {
			return board.getScore();
		}
		int maxValue = -10000;
		for (int move: board.legalMoves()) {
			board.placePiece(move, color);
			int value = min(board, color.reverseColor(), depth-1);
			if (value > maxValue) {
				maxValue = value;
				if (depth == stufe) {
					pickedMove = move;
				}
			}
			board.takeBackMove();
		}

		return pickedMove;
	}
	
	public int alphaBetaMax (PlayerColor color, int depth, int alpha, int beta) {
		ArrayList<Integer> Moves = board.legalMoves();
		if (depth == 0 || Moves.size() == 0) {
			return board.getScore();
		}
		int maxValue = alpha;
		for (int move: board.legalMoves()) {
			board.placePiece(move, color);
			int value = alphaBetaMin(color.reverseColor(), depth-1, maxValue, beta);
			board.takeBackMove();
			if (value > maxValue) {
				maxValue = value;
				if (maxValue >= beta) {
					break;
				}
				if (depth == stufe) {
					pickedMove = move;
				}
			}
		}
		
		return pickedMove;
	}
	
	public int pickMove() {
		if (color == PlayerColor.BLACK) {
			return alphaBetaMax(PlayerColor.BLACK, stufe, -1000, 1000);
		} else {
			return alphaBetaMin(PlayerColor.RED, stufe, -1000, 1000);
		}
	}

	int minMove;
	public int alphaBetaMin (PlayerColor color, int depth, int alpha, int beta) {
		ArrayList<Integer> Moves = board.legalMoves();
		if (depth == 0 || Moves.size() == 0) {
			return board.getScore();
		}
		int minValue = beta;
		for (int move: board.legalMoves()) {
			board.placePiece(move,  color);
			int value = alphaBetaMax(color.reverseColor(), depth-1, alpha, minValue);
			board.takeBackMove();
			if (value < minValue) {
				minValue = value;
				if (minValue <= alpha) {
					break;
				}
				if (depth == stufe) {
					minMove = move;
				}
			}
		}

		return minMove;
	}
	
	int minPicked;
	public int min (Board board, PlayerColor color, int depth) {
		int minValue = 10000;
		if (depth == 0 || board.legalMoves().size() == 0) {
			return board.getScore();
		}

		for (int move: board.legalMoves()) {
			board.placePiece(move, color);
			int value = max(board, color.reverseColor(), depth-1);
			if (value < minValue) {
				minValue = value;
				if (depth == stufe) {
					minPicked = move;
				}
			}
			board.takeBackMove();
		}

		return minPicked;
	}
	
	
}
