package eventsystem;

import java.util.HashMap;

import mapstuff.World;
import entitystuff.Entity;

public class IdleEvent extends Event {
	public IdleEvent(Entity actor, Entity object, long startTime,
			HashMap<String, Object> props) {
		super(actor, object, startTime, props);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(World world){
		
	}

}
