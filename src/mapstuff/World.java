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
		player.setAction(Entity.ACTION_IDLE, System.currentTimeMillis());
		this.entities = entities;
		this.map = map;
	}

	public void doLogicalStep(long currentLogicalFrame) {
		entities.doLogicalStep(this, currentLogicalFrame);
	}

	public boolean movePlayer(Directional.Dir dir) {
		int testX = 0, testY = 0;
		switch (dir) {
		case NORTH:
			testX = player.getX();
			testY = player.getY() - 1;
		case EAST:
			testX = player.getX() + 1;
			testY = player.getY();
		case SOUTH:
			testX = player.getX();
			testY = player.getY() + 1;
		case WEST:
			testX = player.getX() - 1;
			testY = player.getY();
		}
		if (entities.entityAtLocation(testX, testY) == null) {
			if (Tiles.isTileWalkable(
					map.getTileType(player.getX(), player.getY()), dir)) {
				// TODO finish move player method
			}
		}
		player.move(1, 0);
		return false; // TODO move player in direction if possible, and return
						// whether or not player was moved
	}

	public void render(Graphics g) {
		map.render(
				player.getX(),
				player.getY(),
				player.getActionStart(), player.getDirection(), g,
				entities, System.currentTimeMillis());
		// TODO handle the move ratio of the player
	}
	public Player getPlayer(){
		return player;
	}
}
