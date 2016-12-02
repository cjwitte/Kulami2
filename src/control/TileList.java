package control;

import java.util.ArrayList;

public class TileList {
	
	private ArrayList<Tile> tileList;
	
	public TileList() {
		this.tileList = new ArrayList<Tile>();
		this.initTiles();
	}
	
	public void initTiles () {
		Tile a = new Tile("a", 1, 1); //TODO remove a and make setPiece() handle "a" correctly
		Tile b = new Tile("b", 3, 2);
		Tile c = new Tile("c", 2, 3);
		Tile d = new Tile("d", 2, 3);
		Tile e = new Tile("e", 2, 3);
		Tile f = new Tile("f", 2, 2);
		Tile g = new Tile("g", 2, 2);
		Tile h = new Tile("h", 2, 2);
		Tile i = new Tile("i", 2, 2);
		Tile j = new Tile("j", 2, 2);
		Tile k = new Tile("k", 1, 3);
		Tile l = new Tile("l", 1, 3);
		Tile m = new Tile("m", 1, 3);
		Tile n = new Tile("n", 1, 3);
		Tile o = new Tile("o", 1, 2);
		Tile p = new Tile("p", 1, 2);
		Tile q = new Tile("p", 1, 2);
		Tile r = new Tile("p", 1, 2);
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

}
