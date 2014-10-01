package eventsystem;

import java.util.HashMap;

import entitystuff.Entity;

public class Event {
	protected Entity actor;
	protected Entity object;
	public static final Event DEAD_EVENT = new Event(null, null,
			System.currentTimeMillis(), null);
	protected long startTime;

	protected Event(Entity actor, Entity object, long time,
			HashMap<String, Object> props) {
		this.actor = actor;
		this.object = object;
	}

	public boolean process(long frameTime) {
		return true;
	}

	public long getStartTime() {
		return startTime;
	}
}
