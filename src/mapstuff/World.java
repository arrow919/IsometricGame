package mapstuff;

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
}
