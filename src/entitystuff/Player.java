package entitystuff;

import java.util.ArrayList;
import java.util.HashMap;

import mapstuff.World;
import misc.Properties;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player extends Entity {
	public static final String KEY_CURRENT_HEALTH = "CURRENT_HEALTH",
			KEY_MAX_HEALTH = "MAX_HEALTH";
	private int currentHealth, maxHealth;
	private static HashMap<Integer, ArrayList<Animation>> animations;
	{
		animations = new HashMap<Integer, ArrayList<Animation>>();
		SpriteSheet armorSheet = null;
		try {
			armorSheet = new SpriteSheet(Entity.entitySpriteFolder
					+ "leather_armor.png", 128, 128);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Animation> idleAnimations = new ArrayList<Animation>();
		for (int count = 0; count < 4; count++) {
			idleAnimations.add(new Animation(armorSheet, new int[] { 0, 1, 2,
					3, 3, 2, 1,0 }, count, 200));
		}
		animations.put(Entity.ACTION_IDLE, idleAnimations);

		ArrayList<Animation> walkAnimations = new ArrayList<Animation>();
		for (int count = 0; count < 4; count++) {
			walkAnimations.add(new Animation(armorSheet, new int[] { 4, 5, 6,
					7, 8, 9, 10, 11 }, count, 250));
		}
		animations.put(Entity.ACTION_WALKING, walkAnimations);

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
		animations.get(action).get(dir.ordinal())
				.getFrame(time, getActionStart()).draw(x - 64, y - 100);
	}

	@Override
	public boolean canMove() {
		// TODO Add things here to see if player can move
		if (getAction() == Entity.ACTION_IDLE) {
			return true;
		}
		return false;
	}
}
