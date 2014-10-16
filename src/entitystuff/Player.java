package entitystuff;

import io.EntitySpriteLoader;

import java.util.ArrayList;
import java.util.HashMap;

import main.GameStart;
import mapstuff.Direction;
import mapstuff.World;

import org.newdawn.slick.SpriteSheet;

import eventsystem.IdleEvent;

public class Player extends Entity {
	public static final String KEY_CURRENT_HEALTH = "CURRENT_HEALTH",
			KEY_MAX_HEALTH = "MAX_HEALTH";
	private static HashMap<Integer, ArrayList<Animation>> animations;
	static {
		animations = new HashMap<Integer, ArrayList<Animation>>();
		SpriteSheet armorSheet = EntitySpriteLoader.loadSheet(
				"isometric_heroine/clothes.png", 128, 128);
		ArrayList<Animation> idleAnimations = new ArrayList<Animation>();
		for (int count = 0; count < 8; count++) {
			idleAnimations.add(new Animation(armorSheet, new int[] { 0, 1, 2,
					3, 3, 2, 1, 0 }, count, 200));
		}
		animations.put(Entity.ACTION_IDLE, idleAnimations);

		ArrayList<Animation> walkAnimations = new ArrayList<Animation>();
		for (int count = 0; count < 8; count++) {
			walkAnimations.add(new Animation(armorSheet, new int[] { 4, 5, 6,
					7, 8, 9, 10, 11 }, count, 125));
		}
		animations.put(Entity.ACTION_WALKING, walkAnimations);

	}

	public Player(Direction dir, int x, int y) {
		super(dir, x, y);
		event = new IdleEvent(this, null, System.currentTimeMillis(), null);
	}

	public void move(Direction dir) {
		this.x += dir.x;
		this.y += dir.y;
	}

	public void teleport(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void render(int x, int y, long time) {
		animations
				.get(animation)
				.get(dir.ordinal())
				.getFrame(time, event.getStartTime())
				.draw(GameStart.WINDOW_WIDTH / 2 - 64,
						GameStart.WINDOW_HEIGHT / 2 - 100);
	}

	private boolean canMove(Direction dir, World world) {

		boolean currentEvent = event instanceof IdleEvent;
		boolean entityAt = world.isEntityAtTile(x + dir.x, y + dir.y);
		boolean tileWalkable = world.walkable(x, y, dir);
		return currentEvent && !entityAt && tileWalkable;// TODO Add other
															// qualifiers to
															// move check here
	}

	public void wasdInput(Direction dir) {

	}
}
