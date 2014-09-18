package mapstuff;

import org.newdawn.slick.Graphics;

import entitystuff.EntityList;

public class Map {
	public final EntityList entities;
	private int[][] tiles;
	private final Boundaries bounds;

	public Map(int[][] data, Boundaries bounds) {
		this.tiles = data;
		entities = new EntityList();
		this.bounds = bounds;
	}
	public void render(int xLoc, int yLoc, Graphics g){
		
	}
	public int getTile(int x, int y) {
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

	public int getWidth() {
		return tiles.length;
	}

	public int getHeight() {
		return tiles[0].length;
	}

	/**
	 * The class used in Map to determine the x and y boundaries that the player
	 * and other entities can walk within.
	 * 
	 * @author RevNate
	 * 
	 */
	public static class Boundaries {
		/**
		 * Give th
		 * 
		 * @param xLow
		 *            The west most (upper-left) tile that can be walked in.
		 * @param xHigh
		 *            The east most (lower-right) tile that can be walked in.
		 * @param yLow
		 *            The north most (upper-right) tile that can be walked in.
		 * @param yHigh
		 *            The South most (lower-left) tile that can be walked in.
		 */
		public Boundaries(int xLow, int xHigh, int yLow, int yHigh) {

		}
	}
}