package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

import control.Tile;

public class Board implements Serializable {
	
	int level; // 0: only Tiles count, 1: Tiles and areas count, 2: Tiles, areas and lines count
	public String[] state;
	private int score;
	private int lastMove;
	private int nextToLastMove;
	Stack<Integer> lastMoves = new Stack<Integer>();
	String lastMovesTile;
	String nextToLastMovesTile;
	ArrayList<Tile> tileList;
	Tile a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r;
	
	public int getScore() {
		updateScore();
		return score;
	}
	
	public void setTile(Tile tile, int x, int y) throws TileNotPlacableException {
		int position = toPosition(x, y) ;//the upper left corner of the tile
		//check if the area is free
		boolean free = true;
		for (int i = 0; i< tile.getVertical() ; i++) {
			int pos = position*2-2+i*20;
			for (int j = 0; j<= tile.getHorizontal(); j+=2) {
				//TODO sicherstellen, dass hier keine IndexOutOfBoundsExc auftriff
				if (!this.state[pos+j].equals("a")) {
					free = false;
				}
			}
		}
		if (free) {
			//update the corresponding squares on the board.
			for (int i = 0; i < tile.getVertical() ; i++) {
				int pos = position*2-2+i*20;
				for (int j = 0; j <= tile.getHorizontal() ; j+=2) {
					this.state[pos+j] = tile.getName();
				}			
			}
			// mark the Tile as placed
			tile.setPlaced(true);
		} else { //there's another Tile in the way
			throw new TileNotPlacableException();
		}
	}
		
	public String toString() {
		StringBuilder stringbuilder = new StringBuilder(200);
		for (int i = 0; i < 200; i++) {
			stringbuilder.append(state[i]);
		}
		return stringbuilder.toString();
	}
	
	public int getLevel() {
		return level;
	}
	
	public String getLevelAsString() {
		switch(level) {
		case 0:
			return "0";
		case 1:
			return "1";
		case 2:
			return "2";
		default:
			return "";
		}
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public Board(int level) {
		this.state = new String[200];
		this.initTiles();
		this.level = level;
		for (int i = 0; i < 199; i+=2) {
			this.state[i] = "a";
		}
		for (int i = 1; i <200; i+=2) {
			this.state[i] = "0";
		}
		
	/*	this.lastMove = -1;
		this.nextToLastMove = -1;
		this.nextToLastMovesTile = "";
		this.lastMovesTile = "";*/
	}
	
	public Board(String state, int level) {
		this.state = new String[200];
		this.initTiles();
	/*	this.lastMove = -1;
		this.nextToLastMove = -1;
		this.nextToLastMovesTile = "a";
		this.lastMovesTile = "a";*/
		for (int i =0; i<200; i++) {
			this.state[i] = state.substring(i, i+1);
			Tile selectedTile = this.findTile(this.state[i]);
			selectedTile.setPlaced(true);  //marks the Tile as places on the board.
			// update the score of the Tile.
			if (i<199 && state.substring(i+1, i+2).equals("2")) {
				selectedTile.changeOwner(1);
			} else if (i<199 && state.substring(i+1, i+2).equals("1")) {
				selectedTile.changeOwner(-1);
			}
		}
		this.updateScore();
		
	}
	
	public void readBoard (String state) {
		for (int i =0; i<200; i++) {
			this.state[i] = state.substring(i, i+1);
			Tile selectedTile = this.findTile(this.state[i]);
			selectedTile.setPlaced(true);  //marks the Tile as places on the board.
			// update the score of the Tile.
			if (i<199 && state.substring(i+1).equals("2")) {
				selectedTile.changeOwner(1);
			} else if (i<199 && state.substring(i+1).equals("1")) {
				selectedTile.changeOwner(-1);
			}
		}
		this.updateScore();
	}
	/**
	 * 
	 * @param newBoard the new Board represendes as a String
	 * @return returns the move the opponent just made as an int (1 to 100)
	 */
	public int findMove (String newBoard) {
		int move = 0;
		for (int i = 0; i<200; i++) {
			if (!state[i].equals(newBoard.substring(i, i+1))) {
				move = i;
			}
		}
		lastMoves.push(move*2-1);
		return move*2-1;
	}
	
	
	void updateScore() {
		score = 0;
		for (Tile tile: tileList) {
			score = score + tile.getTileScore();
		}
		if (level==2) {
			score = score + findLines(PlayerColor.BLACK) - findLines(PlayerColor.RED);
		}
	}
	
	public String getTile ( int position ) {
		return state[2*position-2];
	}
	
	public void placePiece (int position, PlayerColor playerColor ) {
		if (legalMoves().contains(position)) { 
			state[2*position-1] = playerColor.toString();
			String usedTile = getTile(position); //contains the tile that the piece is placed in ('b' to 'p')
			for (Tile tile : this.tileList ) {
				if (usedTile.equals(tile.getName())) {
					if ( playerColor.toInt() == 2 ){
						tile.changeOwner(-1);
					} else if (playerColor.toInt() == 1) {
						tile.changeOwner(1);
						
					}
				}
			}
		//		lastMove = position;
				nextToLastMovesTile = lastMovesTile;
				lastMovesTile = usedTile;
				lastMoves.push(position);
		} else {
			System.out.println("This piece is not part of the Board."); 
		}
	}
	
	public void takeBackMove () {
		int position = lastMoves.pop();
		String player = state[2*position -1];
		state[2*position-1] = "0";
		String usedTile = getTile(position); //contains the tile that the piece is placed in ('b' to 'p')
		for (Tile tile : this.tileList ) {
			if (usedTile.equals(tile.getName())) {			
				if ( player.equals("2") ){
					tile.changeOwner(1);
					
				} else if (player.equals("1")) {
					tile.changeOwner(-1);
					
				}
			}
		}
	}
	
	
	/**
	 * 
	 * @return returns an ArrayList<Integer> with all legal Moves
	 */
	public ArrayList<Integer> legalMoves (){
		ArrayList<Integer> moves = new ArrayList<Integer>();
		// lastMove is only -1 if there have not been any moves yet, so every Move is legal.
		if (lastMoves.isEmpty()) {
			
			for (int i = 1; i <= 100; i++) {
				if ( !state[2*i-2].equals("a")) {
					moves.add(i);
				}
			}
		} else {
			lastMove = lastMoves.pop();			
			if (!lastMoves.empty()) {
				nextToLastMove = lastMoves.pop();
			} else {
				nextToLastMove = lastMove;
			}
			for (int i = 1; i <=100; i++ ) {
				if ( !state[2*i-2].equals("a") && state[2*i-1].equals("0") && lastMove != i && !(getTile(lastMove).equals(state[2*i-2])) && !(getTile(nextToLastMove).equals(state[2*i-2])) && (lastMove%10 == i%10 || lastMove-lastMove%10 == i-i%10 )) {    
					moves.add(i);
				}
			}
			if (nextToLastMove != lastMove) {
				lastMoves.push(nextToLastMove);
			}
			lastMoves.push(lastMove);
		}
		return moves;
	}
	
	/**
	 * 
	 * @param x horizontal Position of a piece
	 * @param y vertical position of a piece
	 * @return the position (1 - 100)
	 */
	public int toPosition (int x, int y) {
		return  y*10+(x+1);
	}
	
	public int yFromPosition (int position) {
		return (position-1)/10;
	}
	
	public int xFromPosition (int position) {
		return (position - yFromPosition(position)*10-1);
	}
	
	public int ownerFromPosition (int position) {
		return (position*2-1);
	}
	
	/**
	 * finds the neighboring pieces of the same color.
	 * @param piece position on the board 1-100
	 * @param direction 
	 * 0: north; 1: north-east; 2: east; 3: south-east; 4: south; 5: south-west; 6: west; 7: north-west
	 * @return
	 */
	public ArrayList<Integer> sameColorNeighbors(int piece, int direction) {
		ArrayList<Integer> sameColorNeighbors = new ArrayList<Integer>();
		switch (direction) { 
			case 0:
				if (piece >10) {
					int pieceAbove = piece-10;
					if (state[piece*2-1].equals(state[pieceAbove*2-1])) {
						System.out.println("pieceAbove: " + pieceAbove + ": " + state[pieceAbove*2-1]);
						System.out.println("piece: " + piece + " :" + state[piece*2-1]);
						sameColorNeighbors.add(pieceAbove);
						System.out.println("added: " + pieceAbove);
						piece = pieceAbove;
						ArrayList<Integer> newList = sameColorNeighbors(pieceAbove, 0);
						if (!newList.isEmpty()) {
							sameColorNeighbors.addAll(newList);
						}
					}	
				}
			default:
				;
		}
		return sameColorNeighbors;
	}
	
	public int findLines (PlayerColor color) {
		
		boolean[] matchedToRight = new boolean[100];
		int allLines = 0;
		for (int i = 1; i<100; i++) {
			int currentLine = 1;
			int checking = i;
			boolean matching = true;
			while (xFromPosition(checking)<9 && yFromPosition(checking)<9 && matching==true) {
				if (state[ownerFromPosition(checking)].equals(state[ownerFromPosition(checking+11)]) && state[ownerFromPosition(checking)].equals(color.toString()) && !state[ownerFromPosition(checking)].equals("0") && !matchedToRight[checking+11]) {
				//	System.out.println("Match gefunden: " + checking + ", " + (checking+11));
					currentLine+=1;
					checking+=11;
					matchedToRight[checking] = true;
				} else {
					matching = false;
				}

			}
			if (currentLine>3) {
				allLines = allLines + currentLine;
			//	System.out.println("allLines erhöht, nach rechts: " + allLines);
			//	System.out.println(checking);
			}
		}
		
		boolean[] matchedToLeft = new boolean[100];
		for (int i = 1; i<100; i++) {
			int currentLine = 1;
			int checking = i;
			boolean matching = true;
			while (xFromPosition(checking)>0 && yFromPosition(checking)<9 && matching == true) {
				if (state[ownerFromPosition(checking)].equals(state[ownerFromPosition(checking+9)]) && state[ownerFromPosition(checking)].equals(color.toString()) && !state[ownerFromPosition(checking)].equals("0") && !matchedToLeft[checking+9]) {
		//			System.out.println("Match gefunden, nach links:  " + checking + ", " + (checking+9));
					currentLine+=1;
					checking+=9;
					matchedToLeft[checking] = true;
				} else {
					matching = false;
				}

			}
			if (currentLine>3) {

				allLines = allLines + currentLine;
		//		System.out.println("allLines erhöht: " + allLines);
		//		System.out.println(checking);
			}
		}
		return allLines;
		
	}
	
	private boolean[] areaChecked = new boolean[100];
	
	public int findArea (int position) {
	//	boolean[] checked = new boolean[100];
		System.out.println("position: " + position);
		int currentArea = 1;
		int up = position -10;
		int down = position +10;
		int right = position+1;
		int left = position-1;
		areaChecked[position] = true;
		if (xFromPosition(position)<9 && state[ownerFromPosition(position)].equals(state[ownerFromPosition(position+1)]) && !areaChecked[position+1]) {
				int newPosition = ++position;
				currentArea+=findArea(newPosition);			
		} 
		/*else if (xFromPosition(position)==9 && state[place].equals(state[place+2]) && !areaChecked[position-10]) {
			currentArea++;
		}*/
		System.out.println("state oben. " + state[ownerFromPosition(position)]);
		System.out.println("state unten: " + state[ownerFromPosition(position+10)]);
//		System.out.println("position: "+ position);
//		System.out.println("positoni + 10: " +(position+10));
//		System.out.println(!areaChecked[position+10]);
		if (yFromPosition(position)<9 && state[ownerFromPosition(position)].equals(state[ownerFromPosition((position+10))]) && !areaChecked[position+10]) {			
			
			int newPosition = position+10;
			currentArea+=findArea(newPosition);
		} 
		/*else if (yFromPosition(position)==9 && state[place].equals(state[place+20]) && !areaChecked[position-10]) {
			currentArea++;
			System.out.println((position+20));
		}*/
		
		if (xFromPosition(position)>0 && state[ownerFromPosition(position)].equals(state[ownerFromPosition(position-1)]) && !areaChecked[position-1]) {
			int newPosition = position-1;
			currentArea+=findArea(newPosition);
		}
		
		
		/*else if (xFromPosition(position)==0 && state[place].equals(state[place-2]) && !areaChecked[position-10]) {
			currentArea++;
			System.out.println((position-2));
		}*/
		if (yFromPosition(position)>0 && state[ownerFromPosition(position)].equals(state[ownerFromPosition(position-10)]) && !areaChecked[position-10])  {
			int newPosition = position-10;
			currentArea+=findArea(newPosition);
			
		} 
		/*else if (yFromPosition(position)==0 && state[place].equals(state[place-20]) && !areaChecked[position-10]) {
			currentArea++;
			System.out.println((position-20));
		}*/
		return currentArea;
	}
	
	/*
	public int findBiggestArea (PlayerColor playerColor) {
		boolean[] checked = new boolean[100];
		int currentArea = 0;
		for (int i = 1; i <=200; i++) {
			checked[i] = true;
			if (xFromPosition(i)<9) {
				if (state[ownerFromPosition(i)].equals(state[ownerFromPosition(1+1)])) {
					currentArea += 
				}
			}
			
		}
	}
	*/
	
	
	public void initTiles () {
		a = new Tile("a", 1, 1); //TODO remove a and make setPiece() handle "a" correctly
		b = new Tile("b", 3, 3);
		c = new Tile("c", 3, 2);
		d = new Tile("d", 3, 2);
		e = new Tile("e", 3, 2);
		f = new Tile("f", 2, 2);
		g = new Tile("g", 2, 2);
		h = new Tile("h", 2, 2);
		i = new Tile("i", 2, 2);
		j = new Tile("j", 2, 2);
		k = new Tile("k", 1, 3);
		l = new Tile("l", 1, 3);
		m = new Tile("m", 3, 1);
		n = new Tile("n", 3, 1);
		o = new Tile("o", 1, 2);
		p = new Tile("p", 2, 1);
		q = new Tile("q", 1, 2);
		r = new Tile("r", 2, 1);
		this.tileList = new ArrayList<Tile>();
		tileList.add(a);
		tileList.add(b);
		tileList.add(c);
		tileList.add(d);
		tileList.add(e);
		tileList.add(f);
		tileList.add(g);
		tileList.add(h);
		tileList.add(i);
		tileList.add(j);
		tileList.add(k);
		tileList.add(l);
		tileList.add(m);
		tileList.add(n);
		tileList.add(o);
		tileList.add(p);
		tileList.add(q);
		tileList.add(r);
	}
	
	public Tile findTile (String name) {
		for (Tile tile: tileList) {
			if (tile.getName().equals(name)) {
				return tile;
			}
		}
		return new Tile("emptyTile", 0, 0);
	}
	
	public Board copyBoard() {
		Board newBoard = new Board(level);
		for (int i = 0; i< this.state.length; i++) {
			newBoard.state[i] = this.state[i];
		}
		newBoard.tileList = this.tileList;
		newBoard.score = this.score;
		newBoard.lastMove = this.lastMove;
		newBoard.lastMovesTile = this.lastMovesTile;
		newBoard.nextToLastMovesTile = this.nextToLastMovesTile;
		return newBoard;
	}
	
	public void print() {
		for (int i = 0; i < 200; i++) {
			if ( !((i+1) % 20 == 0)) {
				System.out.print(state[i]);
			} else {
				System.out.println(state[i]);
			}
			
		}
	}
	

}
