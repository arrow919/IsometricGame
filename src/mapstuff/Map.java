package mapstuff;

import misc.Properties;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entitystuff.EntityList;
import entitystuff.EntityList.Entities;
import entitystuff.Player;

public class Map {
	public static enum Direction {
		NORTH, SOUTH, EAST, WEST
	};

	private int[][] tileTypes;
	private int[][] tileHeights;

	public Map(int[][] types) {
		this.tileTypes = types;
	}

	// Cartesian to isometric:

	// isoX = cartX - cartY;
	// isoY = (cartX + cartY) / 2;
	public void render(int xLoc, int yLoc, double moveRatio, Direction facing,
			Graphics g) {
		for (int baseX = xLoc - 25; baseX < xLoc + 25; baseX++) {
			for (int baseY = yLoc - 25; baseY < yLoc + 25; baseY++) {
				Tiles.renderTile(g, tileTypes[baseX][baseY], baseX, baseY,
						tileHeights[baseX][baseY]);
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