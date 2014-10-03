package eventsystem;

import mapstuff.Direction;
import mapstuff.World;
import entitystuff.Entity;
import entitystuff.Player;

public class WASDEvent extends Event {
	private final Direction dir;
	private boolean startedSame = false;

	public WASDEvent(Player actor, Entity object, long time, Direction dir) {
		super(actor, object, time, null);
		this.dir = dir;
		if (actor.getDirection().equals(dir)) {
			startedSame = true;
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(World world) {
		long time = System.currentTimeMillis();
		// TODO Handle processing of movement
	}
}