package mapstuff;

import entitystuff.EntityList;

public class Map {
	public final EntityList entities;
	private int[][] tiles;

	public Map(int[][] data) {
		this.tiles = data;
		entities = new EntityList();
	}

	public int tileAt(int x, int y) {
		return tiles[x][y];
	}

	/**
	 * 
	 * @param x
	 *            the x index of the tile
	 * @param y
	 *            the y index of the tile
	 * @return whether or not the tile is walkable 	
	 */
	public boolean tileWalkable(int x, int y) { // TODO add any tiles data to
												// see if their walkable to this
												// method
		switch (tiles[x][y]) {
		case 0:
			return false;
		}
		return false;
	}
	public int getWidth(){
		return tiles.length;
	}
	public int getHeight(){
		return tiles[0].length;
	}
}