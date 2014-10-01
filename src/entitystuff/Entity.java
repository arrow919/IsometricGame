package entitystuff;

import mapstuff.Directional;
import mapstuff.Map;
import mapstuff.World;
import misc.Properties;

public abstract class Entity {
	public static final String KEY_X = "X", KEY_Y = "Y", KEY_DIRECTION = "DIR";
	private final long id;
	protected int x = -1, y = -1;
	protected Directional.Dir dir = Directional.Dir.SOUTH;
	public final static String entitySpriteFolder = "res/entitysprites/";
	private int moveSpeed = 1; // Time in millis it takes to move a square
	public static final int ACTION_IDLE = 0, ACTION_WALKING = 1;

	public Entity(long id, Properties props) {
		this.id = id;
		for (String e : props.keys()) {
			if (e.equals(KEY_X)) {
				x = Integer.parseInt((String) props.getProperty(KEY_X));
			} else if (e.equals(KEY_Y)) {
				y = Integer.parseInt((String) props.getProperty(KEY_Y));
			} else if (e.equals(KEY_DIRECTION)) {
				dir = Directional.Dir.valueOf((String) props
						.getProperty(KEY_DIRECTION));
			}
		}
	}

	public abstract void doLogicalStep(World world, long currentStepCount);

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

	public abstract void interact(World world, Entity subject);

	public abstract void render(int x, int y, long time);
}
