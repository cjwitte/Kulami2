package control;

import java.io.Serializable;

public class Tile implements Serializable{
	private String name;
	private int size;
	private int horizontal;
	private int vertical;
	private int owner;
	private boolean placed;
	
	public Tile (String name, int horizontal, int vertical) {
		this.name = name;
		this.horizontal = horizontal;
		this.vertical = vertical;
		this.size = horizontal * vertical;
		owner = 0;
		placed = false;
	}
	
	void rotate() {
		int swap = horizontal;
		horizontal = vertical;
		vertical = swap;
	}
	
	
	/**
	 * 
	 * @param i pass +1 for black, -1 for red
	 */
	public void changeOwner (int i) {
		owner+=i;
	}
	
	public int getTileScore() {
		if (owner>0) {
			return size;
		} else if (owner < 0 ) {
			return size*-1;
		} else return 0;
	}
	
	public int getHorizontal() {
		return horizontal;
	}
	
	public int getVertical() {
		return vertical;
	}
	
	public int getSize() {
		return size;
	}
	
	public String getName() {
		return name;
	}
	
	public int getOwnder() {
		return owner;
	}
	
	public void setPlaced (boolean b) {
		this.placed = b;
	}
	
	public boolean getPlaced () {
		return this.placed;
	}
}