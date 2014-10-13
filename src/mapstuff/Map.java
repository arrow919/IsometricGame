package mapstuff;

import java.util.Iterator;

import main.GameStart;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entitystuff.Entity;
import entitystuff.EntityList;
import entitystuff.Player;

public class Map {

	private int[][] tileTypes;
	private int[][] tileHeights;

	public Map(int[][] types, int[][] heights) {
		this.tileTypes = types;
		this.tileHeights = heights;
	}

	private final static int RANGE = 24;

	public void render(int xLoc, int yLoc, double xRatio, double yRatio,
			Graphics g, EntityList entities, long time) {
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
				int additionalHeight = 0;
				if (tileTypes[curX][curY] == Tile.TYPE_OCEAN) {
					additionalHeight += (Math.sin(curX + (time / 10) % 512
							/ 256.0 * Math.PI) + 1) * 7;
				}
				int xiso = (curX - curY + 18) * Tiles.HALF_WIDTH;
				int yiso = (curX + curY - 28) * Tiles.HALF_HEIGHT
						- tileHeights[curX][curY] * 10 + additionalHeight;
				Tiles.renderTile(tileTypes[curX][curY], curX - baseX, curY
						- baseY, tileHeights[curX][curY], time);
				Iterator<Entity> it = entities.iterator();
				while (it.hasNext()) {
					Entity en = it.next();
					if (en.getX() == curX && en.getY() == curY) {
						if (en instanceof Player) {
							en.render(g, curX, curY, time);
						}
						en.render(mapGraphics, Tile.xy[0], Tile.xy[1], time);
					}
				}
			}

		}
	}

	/**
	 * Returns whether the tile is walkable in the direction dir E.g. a dir of
	 * SOUTH_WEST will check the lower left side, and a dir of WEST will check
	 * both SOUTH_WEST and NORTH_WEST. It checks both the current tile, the tile
	 * you're moving into, and if it's a diagonal (through 4 tiles), it will
	 * check all four.
	 * 
	 * @param x
	 *            The x of the tile
	 * @param y
	 *            The y of the tile
	 * @param dir
	 *            The side of the tile to check.
	 * @return
	 */
	public boolean walkable(int x, int y, Direction dir) {
		int changeX = x + dir.x;
		int changeY = y + dir.y;
		if (Tiles.isTileWalkable(getTileType(x, y), dir)
				&& Tiles.isTileWalkable(getTileType(changeX, changeY),
						dir.opposite())) {
			if (dir.x == 0 || dir.y == 0) {
				return true;
			}
			if (dir.equals(Direction.EAST)) {
				return Tiles.isTileWalkable(getTileType(changeX, y),
						Direction.SOUTH)
						&& Tiles.isTileWalkable(getTileType(x, changeY),
								Direction.NORTH);
			}
			if (dir.equals(Direction.WEST)) {
				return Tiles.isTileWalkable(getTileType(changeX, y),
						Direction.NORTH)
						&& Tiles.isTileWalkable(getTileType(x, changeY),
								Direction.SOUTH);
			}
			if (dir.equals(Direction.NORTH)) {
				return Tiles.isTileWalkable(getTileType(changeX, y),
						Direction.EAST)
						&& Tiles.isTileWalkable(getTileType(x, changeY),
								Direction.WEST);
			}
			if (dir.equals(Direction.SOUTH)) {
				return Tiles.isTileWalkable(getTileType(changeX, y),
						Direction.WEST)
						&& Tiles.isTileWalkable(getTileType(x, changeY),
								Direction.EAST);
			}
		}
		return false;
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