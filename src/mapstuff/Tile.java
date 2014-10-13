package mapstuff;

import java.util.HashMap;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Tile {
	public static final int TYPE_OCEAN = 2, TYPE_VOID = 0;
	public static final String KEY_ID = "ID", KEY_NAME = "NAME",
			KEY_TEXTURE = "TEXTURE", KEY_WALKABLE = "WALKABLE",
			KEY_BOAT_ONLY = "BOATONLY";
	private final int id;
	private final String name;
	private final Image texture;
	private boolean[] walkable = new boolean[8];
	private boolean boatOnly;

	public Tile(HashMap<String, Object> map) {
		id = Integer.parseInt((String) map.get(KEY_ID));
		name = (String) map.get(KEY_NAME);
		texture = (Image) map.get(KEY_TEXTURE);
		String tempWalkable = (String) map.get(KEY_WALKABLE);
		String[] split = tempWalkable.split(",");
		walkable[1] = split[0].equals("1");
		walkable[3] = split[1].equals("1");
		walkable[5] = split[2].equals("1");
		walkable[7] = split[3].equals("1");
		walkable[0] = walkable[7] && walkable[1];
		walkable[2] = walkable[1] && walkable[3];
		walkable[6] = walkable[5] && walkable[7];
		walkable[4] = walkable[3] && walkable[5];
		boatOnly = ((String) map.get(KEY_BOAT_ONLY)).equals("1");

	}


	public void render(int x, int y) {
		texture.draw(x, y);

	}

	/**
	 * 
	 * @param dir
	 * @return
	 */
	public boolean isWalkable(Direction dir) {
		return !boatOnly && walkable[dir.ordinal()];
	}

	public boolean isBoatable() {
		return boatOnly;
	}

}
