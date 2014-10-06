package mapstuff;

import java.util.Iterator;

import org.newdawn.slick.Graphics;

import entitystuff.Entity;
import entitystuff.EntityList;
import entitystuff.Player;

public class World {
	private final Player player;
	private final EntityList entities;
	private Map map;

	public World(Map map, Player player, EntityList entities) {
		this.player = player;
		this.entities = entities;
		this.map = map;
	}

	public void render(Graphics g) {
		map.render(player.getX(), player.getY(), 0, player.getDirection(), g,
				entities, System.currentTimeMillis());
		// TODO handle the move ratio of the player
	}

	public Player getPlayer() {
		return player;
	}

	public void processEvents() {
		entities.processEvents(this);
	}

	public boolean isEntityAtTile(int x, int y) {
		Iterator<Entity> entIterator = entities.iterator();
		while (entIterator.hasNext()) {
			Entity e = entIterator.next();
			if (e.usesTile(x, y)) {
				return true;
			}
		}
		return false;
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
	public boolean walkable(int x, int y, Direction dir) {;
		return map.walkable(x, y, dir);
	}
}
