package entitystuff;

import java.util.HashMap;

import mapstuff.Direction;
import mapstuff.World;
import eventsystem.Event;

public abstract class Entity {
	public static final String KEY_X = "X", KEY_Y = "Y", KEY_DIRECTION = "DIR";
	private final long id;
	protected int x = 0, y = 0;
	protected Direction dir = new Direction(0, 1);
	public static final int ACTION_IDLE = 0, ACTION_WALKING = 1;
	protected Event event = Event.DEAD_EVENT;

	public Entity(long id, Direction dir, int x, int y) {
		this.id = id;
		this.dir = dir;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Direction getDirection() {
		return dir;
	}

	public void setDirection(Direction newdir) {
		dir = newdir;
	}

	public abstract void render(int x, int y, long time);

	public long getID() {
		return id;
	}

	public void processEvent(World world) {
		event.process(world);
	}

}
