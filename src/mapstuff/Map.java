package mapstuff;

import org.newdawn.slick.Graphics;

import entitystuff.EntityList;

public class Map {
	public static enum Direction {
		NORTH, EAST, SOUTH, WEST
	};

	private int[][] tileTypes;
	private int[][] tileHeights;

	public Map(int[][] types, int[][] heights) {
		this.tileTypes = types;
		this.tileHeights = heights;
	}
	private final static int RANGE=12;
	public void render(int xLoc, int yLoc, double moveRatio, Direction facing,
			Graphics g, EntityList entities) {
		int baseX = xLoc-RANGE;
		int baseY = yLoc-RANGE;
		for (int curX = baseX; curX <= xLoc + RANGE; curX++) {
			for (int curY = baseY; curY <= yLoc + RANGE; curY++) {
				Tiles.renderTile(g, tileTypes[curX][curY], curX-baseX, curY-baseY,
						tileHeights[curX][curY]);
			}
		}

	}

	public int getTileType(int x, int y) {
		return tileTypes[x][y];
	}

	public int getWidth() {
		return tileTypes.length;
	}

	public int getHeight() {
		return tileTypes[0].length;
	}

}