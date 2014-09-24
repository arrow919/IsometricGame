package mapstuff;

import org.newdawn.slick.Graphics;

public class Map {
	public static enum Direction {
		NORTH, SOUTH, EAST, WEST
	};

	private int[][] tileTypes;
	private int[][] tileHeights;

	public Map(int[][] types, int[][] heights) {
		this.tileTypes = types;
		this.tileHeights = heights;
	}

	public void render(int xLoc, int yLoc, double moveRatio, Direction facing,
			Graphics g) {
		int baseX = xLoc-25;
		int baseY = yLoc-25;
		for (int curX = baseX; curX < xLoc + 25; curX++) {
			for (int curY = baseY; curY < yLoc + 25; curY++) {
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