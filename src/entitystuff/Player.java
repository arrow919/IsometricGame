package entitystuff;

import mapstuff.Map;
import mapstuff.Map.Direction;
import mapstuff.World;
import misc.Properties;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player extends Entity {
	public static final String KEY_CURRENT_HEALTH = "CURRENT_HEALTH",
			KEY_MAX_HEALTH = "MAX_HEALTH";
	private int currentHealth, maxHealth;
	private static SpriteSheet armor;
	{
		try {
			armor = new SpriteSheet(Entity.entitySpriteFolder
					+ "leather_armor.png", 128, 128);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Player(long id, Properties props) {
		super(id, props);
		for (String e : props.keys()) {
			if (e.equals(KEY_CURRENT_HEALTH)) {
				currentHealth = Integer.valueOf((String) props
						.getProperty(KEY_CURRENT_HEALTH));
			} else if (e.equals(KEY_MAX_HEALTH)) {
				maxHealth = Integer.valueOf((String) props
						.getProperty(KEY_MAX_HEALTH));
			}
		}
	}

	@Override
	public void doLogicalStep(World world, long currentLogicalFrame) {
		// TODO Handle logical updates for Player

	}

	public void move(int x, int y) {
		this.x += x;
		this.y += y;
	}

	public void teleport(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void interact(World world, Entity subject) {

	}

	@Override
	public void render(int x, int y, long time) {
		// TODO currently doesn't have images heh...
		int action = getAction();
		long actionStart = getActionStart();
		if (action == ACTION_WALKING) {
			armor.getSprite(4 + (int) (time - getActionStart()) % 125,
					(dir.ordinal() + 1) * 2).draw(x - 64, y - 100);
		} else if (action == ACTION_IDLE) {
			armor.getSprite((int) ((time - getActionStart()) % 250),
					(dir.ordinal() + 1) * 2).draw(x - 64, y - 100);
		}
	}
}
