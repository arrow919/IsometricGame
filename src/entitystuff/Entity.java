package entitystuff;

import mapstuff.Direction;
import mapstuff.World;
import eventsystem.Event;

public abstract class Entity {
	public static final String KEY_X = "X", KEY_Y = "Y", KEY_DIRECTION = "DIR";
	private long id;
	protected int x = 0, y = 0;
	protected Direction dir = Direction.SOUTH_WEST;
	public static final int ACTION_IDLE = 0, ACTION_WALKING = 1;
	protected Event event = Event.DEAD_EVENT;
	private int width = 1, height = 1;

	public Entity(Direction dir, int x, int y) {
		this.dir = dir;
		this.x = x;
		this.y = y;
	}

	public boolean usesTile(int x, int y) {
		if (x >= this.x && y >= this.y) {
			return x < this.x + width && y < this.y + height;
		}
		return false;
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

	protected void setID(long id) {
		this.id = id;
	}

	protected int animation;

	public void setAnimation(int ani) {
		animation = ani;
	}
	public void setEvent(Event e){
		event=e;
	}
}
