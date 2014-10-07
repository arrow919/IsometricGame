package eventsystem;

import mapstuff.Direction;
import mapstuff.World;
import entitystuff.Entity;
import entitystuff.Player;

public class WASDEvent extends Event {
	private final Direction dir;
	private boolean startedSame = false;
	private long moveTime = 500;

	public WASDEvent(Player actor, Entity object, long time, Direction dir) {
		super(actor, object, time, null);
		id = 1;
		this.dir = dir;
		if (dir.isEastWest() || dir.isNorthSouth()) {
			moveTime = (int) (moveTime * 1.414f);
		}
		if (actor.getDirection().equals(dir)) {
			startedSame = true;
			actor.setAnimation(id);
		} else {
			actor.setDirection(dir);
			moveTime/=5;
		}
	}

	@Override
	public void process(World world) {
		if (System.currentTimeMillis() - startTime >= moveTime) {
			if (!startedSame) {
				actor.setEvent(new IdleEvent(actor, null, 0, null));
			} else {
				((Player) actor).move(dir);
				actor.setEvent(new IdleEvent(actor, null, 0, null));
				System.out.println("Here 12345");
			}
		}
	}

	/**
	 * 
	 * @return The signed value of -1 to 1 of where the player is in relation to
	 *         the next spot
	 */
	public double xOffset(long time) {
		if (!dir.isNorthSouth()) {
			return (time - startTime) / moveTime * (dir.x > 0 ? 1 : -1);
		}
		return 0;
	}

	/**
	 * See xOffset, same but for
	 * 
	 * @return
	 */
	public double yOffset(long time) {
		if (!dir.isEastWest()) {
			return (time - startTime) / moveTime * (dir.y > 0 ? 1 : -1);
		}
		return 0;
	}
}