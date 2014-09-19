package entitystuff;

import misc.Properties;

public class Player extends PersistentEntity {
	private int health;
	public Player(long id, Properties props) {
		super(id, props);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void logicalUpdate() {
		// TODO Handle logical updates for Player
		
	}

	@Override
	public String persist() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load(String data) {
		// TODO Load from string data
		
	}
}
