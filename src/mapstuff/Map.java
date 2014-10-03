package mapstuff;

import java.util.Iterator;

import org.newdawn.slick.Graphics;

import entitystuff.Entity;
import entitystuff.EntityList;

public class Map {

	private int[][] tileTypes;
	private int[][] tileHeights;

	public Map(int[][] types, int[][] heights) {
		this.tileTypes = types;
		this.tileHeights = heights;
	}

	private final static int RANGE = 24;

	public void render(int xLoc, int yLoc, double moveRatio,
			Directional.Dir facing, Graphics g, EntityList entities, long time) {
		int baseX = xLoc - RANGE;
		if (baseX < 0) {
			baseX = 0;
		}
		int baseY = yLoc - RANGE;
		if (baseY < 0) {
			baseY = 0;
		}
		for (int curX = baseX; curX <= xLoc + RANGE; curX++) {
			for (int curY = baseY; curY <= yLoc + RANGE; curY++) {
				Tiles.renderTile(g, tileTypes[curX][curY], curX - baseX, curY
						- baseY, tileHeights[curX][curY], time);
				Iterator<Entity> it = entities.iterator();
				while (it.hasNext()) {
					Entity en = it.next();
					if (en.getX() == curX && en.getY() == curY) {
						en.render(Tile.xy[0], Tile.xy[1], time);
					}
				}
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