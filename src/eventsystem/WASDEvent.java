package eventsystem;

import mapstuff.Direction;
import mapstuff.World;
import entitystuff.Entity;
import entitystuff.Player;

public class WASDEvent extends Event {
	private final Direction dir;
	private boolean startedSame = false;
	private long moveTime = 1000;

	public WASDEvent(Player actor, Entity object, long time, Direction dir) {
		super(actor, object, time, null);
		id = 1;
		actor.setAnimation(id);
		this.dir = dir;
		if (actor.getDirection().equals(dir)) {
			startedSame = true;
		}
		if (dir.isEastWest() || dir.isNorthSouth()) {
			moveTime = 1414;
		}
	}

	@Override
	public void process(World world) {
		long time = System.currentTimeMillis();
		// TODO Handle processing of movement
	}

	/**
	 * 
	 * @return The signed value of -1 to 1 of where the player is in relation to
	 *         the next spot
	 */
	public double xOffset() {
		if (!dir.isNorthSouth()) {
			return (System.currentTimeMillis() - startTime) / moveTime;
		}
		return 0;
	}

	/**
	 * See xOffset, same but for
	 * 
	 * @return
	 */
	public double yOffset() {
		return yOffset;
	}
}