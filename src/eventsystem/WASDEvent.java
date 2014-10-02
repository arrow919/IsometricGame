package eventsystem;

import mapstuff.Directional;
import entitystuff.Entity;

public class WASDEvent extends Event{
	private final Directional.Dir dir;
	protected WASDEvent(Entity actor, Entity object, long time, Directional.Dir dir) {
		super(actor, object, time, null);
		this.dir=dir;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean process(long frameTime) {
		// TODO Auto-generated method stub
		return super.process(frameTime);
	}

	
}
