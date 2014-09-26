package entitystuff;

import java.util.ArrayList;

import mapstuff.World;
import misc.Properties;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Entity {
	public static final String KEY_CURRENT_HEALTH = "CURRENT_HEALTH",
			KEY_MAX_HEALTH = "MAX_HEALTH";
	private int currentHealth, maxHealth;
	private double moveRatio;
	private static ArrayList<Image> frames;

	{
		frames = new ArrayList<Image>();
		try {
			frames.add(new Image(Entity.entitySpriteFolder + "player.png"));
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

	public double getMoveRatio() {
		return moveRatio;
	}

	public void move(int x, int y) {
		this.x -= x;
		this.y += y;
	}

	public void teleport(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void interact(World world, Entity subject) {

	}
}
