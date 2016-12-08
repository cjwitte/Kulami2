package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

import control.Tile;

public class Board implements Serializable {
	
	public String[] state;
	private int score;
	private int lastMove;
	private int nextToLastMove;
	Stack<Integer> lastMoves = new Stack<Integer>();
	String lastMovesTile;
	String nextToLastMovesTile;
	ArrayList<Tile> tileList;
	public Tile a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r;
	int level;
	

	public Board() {
		this.state = new String[200];
		this.initTiles();
		for (int i = 0; i < 199; i+=2) {
			this.state[i] = "a";
		}
		for (int i = 1; i <200; i+=2) {
			this.state[i] = "0";
		}
		this.level = 0;
	}
	
	public Board(String state) {
		this.state = new String[200];
		this.initTiles();
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
		this.updateScore(level);
		
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
		this.updateScore(level);
	}

	
	public int getScore() {
		updateScore(level);
		return score;
	}
	
	public void setTile(Tile tile, int x, int y) throws TileNotPlacableException {
		int position = toPosition(x, y) ;//the upper left corner of the tile
		//check if the area is free
		boolean free = true;
		for (int i = 0; i< tile.getVertical() ; i++) {
			int pos = position*2-2+i*20;
			for (int j = 0; j<= tile.getHorizontal(); j+=2) {
				//TODO sicherstellen, dass hier keine IndexOutOfBoundsExc auftritt
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
	/**
	 * 
	 * @param newBoard the new Board represends as a String
	 * @return returns the move the opponent just made as an int (1 to 100)
	 */
	public int findMove (String newBoard) {
		int move = 0;
		for (int i = 0; i<200; i++) {
			if (!state[i].equals(newBoard.substring(i, i+1))) {
				move = i;
				System.out.println("anders:" + i);
			}
		}
		return ((move+1)/2);
	}
	
	
	void updateScore(int level) {
		score = 0;
		for (Tile tile: tileList) {
			score = score + tile.getTileScore();
		}
		if (level >=1) {
			//TODO größte Fläche einrechnen
		}
		if (level==2) {
			score = score + findLines(PlayerColor.BLACK) - findLines(PlayerColor.RED);
		}

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
			System.out.println("This move is not legal: " + xFromPosition(position) + "" + yFromPosition(position)); 
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
	
	public void setLevel (int level) {
		this.level = level;
	}
	
	public int getLevel () {
		return level;
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
	
	public String getTile ( int position ) {
		return state[2*position-2];
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
					currentLine+=1;
					checking+=11;
					matchedToRight[checking] = true;
				} else {
					matching = false;
				}

			}
			if (currentLine>3) {
				allLines = allLines + currentLine;
			}
		}
		
		boolean[] matchedToLeft = new boolean[100];
		for (int i = 1; i<100; i++) {
			int currentLine = 1;
			int checking = i;
			boolean matching = true;
			while (xFromPosition(checking)>0 && yFromPosition(checking)<9 && matching == true) {
				if (state[ownerFromPosition(checking)].equals(state[ownerFromPosition(checking+9)]) && state[ownerFromPosition(checking)].equals(color.toString()) && !state[ownerFromPosition(checking)].equals("0") && !matchedToLeft[checking+9]) {
					currentLine+=1;
					checking+=9;
					matchedToLeft[checking] = true;
				} else {
					matching = false;
				}
			}
			if (currentLine>4) {
				allLines = allLines + currentLine;
			}
		}
		return allLines;
		
	}
	
	private boolean[] areaChecked = new boolean[100];
	
	public int findArea (int position) {
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
	
	
	
	public void initTiles () {
		b = new Tile("b", 3, 2);
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
		m = new Tile("m", 1, 3);
		n = new Tile("n", 1, 3);
		o = new Tile("o", 2, 1);
		p = new Tile("p", 2, 1);
		q = new Tile("q", 2, 1);
		r = new Tile("r", 2, 1);
		this.tileList = new ArrayList<Tile>();
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
		Tile matchingTile = new Tile("empty Tile", 2, 2);
		for (Tile tile: tileList) {
			if (tile.getName().equals(name)) {
				return tile;
			}
		}
		return matchingTile;
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
