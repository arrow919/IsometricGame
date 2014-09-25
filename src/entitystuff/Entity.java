package entitystuff;

import mapstuff.Map;
import mapstuff.World;
import misc.Properties;

public abstract class Entity {
	public static final String KEY_X = "X", KEY_Y = "Y", KEY_DIRECTION = "DIR";
	private final long id;
	protected int x = -1, y = -1;
	protected Map.Direction dir = Map.Direction.SOUTH;

	public Entity(long id, Properties props) {
		this.id = id;
		for (String e : props.keys()) {
			if (e.equals(KEY_X)) {
				x = Integer.parseInt((String) props.getProperty(KEY_X));
			} else if (e.equals(KEY_Y)) {
				y = Integer.parseInt((String) props.getProperty(KEY_Y));
			} else if (e.equals(KEY_DIRECTION)) {
				dir = Map.Direction.valueOf((String) props
						.getProperty(KEY_DIRECTION));
			}
		}
	}

	public Entity(long id) {
		this.id = id;
	}

	public abstract void doLogicalStep(World world, long currentStepCount);

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Map.Direction getDirection() {
		return dir;
	}
}
