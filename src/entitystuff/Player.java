package entitystuff;

import io.EntitySpriteLoader;

import java.util.ArrayList;
import java.util.HashMap;

import mapstuff.Direction;
import mapstuff.World;

import org.newdawn.slick.SpriteSheet;

import eventsystem.IdleEvent;
import eventsystem.WASDEvent;

public class Player extends Entity {
	public static final String KEY_CURRENT_HEALTH = "CURRENT_HEALTH",
			KEY_MAX_HEALTH = "MAX_HEALTH";

	private static HashMap<Integer, ArrayList<Animation>> animations;
	{
		animations = new HashMap<Integer, ArrayList<Animation>>();
		SpriteSheet armorSheet = EntitySpriteLoader.loadSheet(
				"isometric_heroine/leather_armor.png", 128, 128);
		ArrayList<Animation> idleAnimations = new ArrayList<Animation>();
		for (int count = 0; count < 8; count++) {
			idleAnimations.add(new Animation(armorSheet, new int[] { 0, 1, 2,
					3, 3, 2, 1, 0 }, count, 200));
		}
		animations.put(Entity.ACTION_IDLE, idleAnimations);

		ArrayList<Animation> walkAnimations = new ArrayList<Animation>();
		for (int count = 0; count < 4; count++) {
			walkAnimations.add(new Animation(armorSheet, new int[] { 4, 5, 6,
					7, 8, 9, 10, 11 }, count, 250));
		}
		animations.put(Entity.ACTION_WALKING, walkAnimations);

	}

	public Player(long id, Direction dir, int x, int y) {
		super(id, dir, x, y);
	}

	public void move(int x, int y) {
		this.x += x;
		this.y += y;
	}

	public void teleport(int x, int y) {
		this.x = x;
		this.y = y;
	}

	private int animation;

	@Override
	public void render(int x, int y, long time) {

		animations.get(animation).get(dir.ordinal())
				.getFrame(time, event.getStartTime()).draw(x - 64, y - 100);
	}

	private boolean canMove(int x, int y, World world) {
		boolean currentEvent = event instanceof IdleEvent; // TODO add other
															// events the player
															// can cancel to
															// move

		return currentEvent;
	}

	public void wasdInput(int x, int y, World world) {
		if (canMove(x, y, world)) {
			event = new WASDEvent(this, null, System.currentTimeMillis(), dir);
		}
	}

}
