package eventsystem;

import java.util.HashMap;

import mapstuff.World;

import entitystuff.Entity;

public class MoveEvent extends Event {

	public MoveEvent(Entity actor, Entity object, long time,
			HashMap<String, Object> props) {
		super(actor, object, time, props);

	}

	@Override
	public void process(World world) {
		// TODO Auto-generated method stub
		super.process(world);
	}

}
