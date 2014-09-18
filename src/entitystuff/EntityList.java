package entitystuff;

import java.util.ArrayList;

import mapstuff.Map;
import misc.Properties;

import org.newdawn.slick.Graphics;

public class EntityList {
	private int next = 0;
	private final ArrayList<Entity> entities;
	public enum Entities {
		PLAYER, HOUSE
	}

	public EntityList() {
		next = 0;
		entities = new ArrayList<Entity>();
	}

	public Entity createEntity(Entities type, Properties properties) {
		Entity entity = null;
		switch (type) {
		case PLAYER:
			entity = new Player(next, properties);
		}
		if(entity!=null){
			next++;
			entities.add(entity);
			return entity;
		}
		throw new RuntimeException("Entity type does not exist");
	}

	public void renderEntities(int xLoc, int yLoc, Graphics g) {
		// TODO Program this method!!
	}

	public void updateAll(Map map) {
		// TODO Program the update method!!
		for(Entity e: entities){
			e.logicalUpdate();
		}
	}
	
}
