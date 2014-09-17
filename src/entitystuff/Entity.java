package entitystuff;

import misc.Properties;

public abstract class Entity {
	private final long id;

	public Entity(long id, Properties props) {
		this.id = id;
	}

	public Entity(long id) {
		this.id=id;
	}

	public abstract void doStep();
}
