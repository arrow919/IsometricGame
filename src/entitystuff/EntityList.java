package entitystuff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import mapstuff.Direction;
import mapstuff.World;

public class EntityList {
	private int next = 0;
	private final ArrayList<Entity> entities;
	public static final String ENTITY_PLAYER = "PLAYER", ENTITY_TREE = "TREE";

	public EntityList(int start) {
		next = start;
		entities = new ArrayList<Entity>();
	}

	public int addEntity(Entity addEntity) {
		addEntity.setID(next);
		next++;
		entities.add(addEntity);
		return next - 1;
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

	public void processEvents(World world) {
		for (Entity e : entities) {
			e.processEvent(world);
		}
	}
}
