package entitystuff;

import java.util.HashMap;

import mapstuff.Directional;
import mapstuff.World;
import eventsystem.Event;

public abstract class Entity {
	public static final String KEY_X = "X", KEY_Y = "Y", KEY_DIRECTION = "DIR";
	private final long id;
	protected int x = 0, y = 0;
	protected Directional.Dir dir = Directional.Dir.SOUTH;
	public static final int ACTION_IDLE = 0, ACTION_WALKING = 1;
	protected Event event = Event.DEAD_EVENT;

	public Entity(long id, Directional.Dir dir, HashMap<String, Object> props) {
		this.id = id;
		for (String e : props.keySet()) {
			if (e.equals(KEY_X)) {
				x = Integer.parseInt((String) props.get(KEY_X));
			} else if (e.equals(KEY_Y)) {
				y = Integer.parseInt((String) props.get(KEY_Y));
			} else if (e.equals(KEY_DIRECTION)) {
				dir = (Directional.Dir) props.get(KEY_DIRECTION);
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Directional.Dir getDirection() {
		return dir;
	}

	public void setDirection(Directional.Dir newdir) {
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
