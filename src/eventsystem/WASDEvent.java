package eventsystem;

import mapstuff.Directional;
import mapstuff.World;
import entitystuff.Entity;
import entitystuff.Player;

public class WASDEvent extends Event {
	private final Directional.Dir dir;
	private boolean startedSame = false;

	public WASDEvent(Player actor, Entity object, long time, Directional.Dir dir) {
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
		if (time - startTime >= 1000) {
			Player player = (Player) actor;
			if (startedSame) {
				switch (dir) {
				case NORTH:
					player.move(0, -1);
				case EAST:
					player.move(1, 0);
				case SOUTH:
					player.move(0, -1);
				case WEST:
					player.move(-1, 0);
				}
			} else {
				player.setDirection(dir);
			}
		}
	}
}