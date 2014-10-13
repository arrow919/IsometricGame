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
			moveTime /= 5;
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
		double val = 0;
		if (!dir.isNorthSouth()) {
			System.out.println((time-startTime)/(double)moveTime);
			val = (time - startTime) / (double)moveTime * (dir.x > 0 ? 1 : -1);
			//System.out.println("xOffset: " + val);
		}
		return val;
	}

	/**
	 * See xOffset, same but for
	 * 
	 * @return
	 */
	public double yOffset(long time) {
		double val = 0;
		if (!dir.isEastWest()) {
			val = (time - startTime) / moveTime * (dir.y > 0 ? 1 : -1);
			//System.out.println("yOffset: " + val);
		}
		return val;
	}
}