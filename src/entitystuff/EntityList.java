package entitystuff;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

import mapstuff.Tile;
import mapstuff.World;
import misc.Properties;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class EntityList {
	private int next = 0;
	private final ArrayList<Entity> entities;

	public enum Entities {
		PLAYER, HOUSE
	}

	public EntityList() {
		entities = new ArrayList<Entity>();
	}

	public Entity createEntity(Entities type, Properties properties) {
		Entity entity = null;
		switch (type) {
		case PLAYER:
			entity = new Player(next, properties);
		default:
			break;
		}
		if (entity != null) {
			next++;
			entities.add(entity);
			return entity;
		}
		throw new RuntimeException("Entity type does not exist");
	}

	public void doLogicalStep(World world, long currentLogicalFrame) {
		for (Entity e : entities) {
			e.doLogicalStep(world, currentLogicalFrame);
		}
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

}
