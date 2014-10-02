package entitystuff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import mapstuff.World;

public class EntityList {
	private int next = 0;
	private final ArrayList<Entity> entities;
	public static final String ENTITY_PLAYER = "PLAYER", ENTITY_TREE = "TREE";

	public EntityList() {
		entities = new ArrayList<Entity>();
	}

	public Entity createEntity(String type, HashMap<String, Object> props) {
		Entity entity = null;
		switch (type) {
		case ENTITY_PLAYER:
			entity = new Player(next, props);
			break;
		case ENTITY_TREE:
			entity = new Tree(next, props);
			break;
		}
		if (entity != null) {
			next++;
			entities.add(entity);
			return entity;
		}
		throw new RuntimeException("Entity does not exist");
	}

	public Entity getEntity(int id) {
		return entities.get(id);
	}

	public Entity entityAtLocation(int x, int y) {
		for (Entity e : entities) {
			if (e.getX() == x && e.getY() == y) {
				return e;
			}
		}
		return null;
	}

	public Iterator<Entity> iterator() {
		return entities.iterator();
	}
	public void processEvents(World world){
		for(Entity e: entities){
			e.processEvent(world);
		}
	}
}
