package eventsystem;

import java.util.HashMap;

import mapstuff.World;
import entitystuff.Entity;

public class IdleEvent extends Event {
	public IdleEvent(Entity actor, Entity object, long startTime,
			HashMap<String, Object> props) {
		super(actor, object, startTime, props);
		actor.setAnimation(0);
	}

	@Override
	public void process(World world){
		
	}

}
