package entitystuff;

import misc.Properties;

public abstract class PersistentEntity extends Entity {

	public PersistentEntity(long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public PersistentEntity(long id, Properties props) {
		super(id, props);
	}

	public abstract String persist();

	public abstract void load(String data);
}
