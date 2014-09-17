package entitystuff;

import misc.Properties;

public class EntityList {
	private int next = 0;

	public enum Entities {
		PLAYER, HOUSE
	}

	public EntityList() {
		next = 0;
	}

	public Entity createEntity(Entities type, Properties properties) {
		Entity entity = null;
		switch (type) {
		case PLAYER:
			entity = new Player(next, properties);
		}
		throw new RuntimeException("Entity type does not exist");
	}
}
