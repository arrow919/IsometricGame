package mapstuff;

import java.util.Iterator;

import main.GameStart;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entitystuff.Entity;
import entitystuff.EntityList;

public class Map {

	private int[][] tileTypes;
	private int[][] tileHeights;
	private Image mapImg;
	private Graphics mapGraphics;

	public Map(int[][] types, int[][] heights) {
		this.tileTypes = types;
		this.tileHeights = heights;
		try {
			mapImg = new Image(GameStart.WINDOW_WIDTH, GameStart.WINDOW_HEIGHT);
			mapGraphics = mapImg.getGraphics();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private final static int RANGE = 24;
	private boolean rerender = true;

	public void render(int xLoc, int yLoc, double moveRatio, Direction facing,
			Graphics g, EntityList entities, long time) {
		if (rerender) {// TODO setup the rerendering so u don't waste frames
						// dummy
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
					Tiles.renderTile(g, tileTypes[curX][curY], curX - baseX,
							curY - baseY, tileHeights[curX][curY], time);
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
	}

	/**
	 * Returns whether the tile is walkable in the direction dir E.g. a dir of
	 * SOUTH_WEST will check the upper left side
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
		if (Tiles.isTileWalkable(getTileType(x, y), dir)
				&& Tiles.isTileWalkable(getTileType(x + dir.x, y + dir.y), dir.opposite())) {
			if (dir.x == 0 || dir.y == 0) {
				return true;
			}

			int changeX = x + dir.x;
			int changeY = y + dir.y;
			
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