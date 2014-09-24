package mapstuff;

import org.newdawn.slick.Graphics;

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

	public void doLogicalStep(long currentLogicalFrame) {
		entities.doLogicalStep(this, currentLogicalFrame);
	}

	public boolean movePlayer(Map.Direction dir) {
		return false; // TODO move player in direction if possible, and return
						// whether or not player was moved
	}

	public void render(Graphics g) {
		map.render(player.getX(), player.getY(), player.getMoveRatio(),
				player.getDirection(), g);
	}
}
