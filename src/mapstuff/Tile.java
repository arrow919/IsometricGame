package mapstuff;

import java.util.HashMap;

import main.GameStart;
import mapstuff.Map.Direction;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Tile {
	public static final String KEY_ID = "ID", KEY_NAME = "NAME",
			KEY_TEXTURE = "TEXTURE", KEY_WALKABLE = "WALKABLE";
	private final int id;
	private final String name;
	private final Image texture;
	private boolean[] walkable = new boolean[4];

	public Tile(HashMap<String, Object> map) {
		id = Integer.parseInt((String) map.get(KEY_ID));
		name = (String) map.get(KEY_NAME);
		texture = (Image) map.get(KEY_TEXTURE);
		String tempWalkable = (String) map.get(KEY_WALKABLE);
		String[] split = tempWalkable.split("");
		walkable[0] = split[0].equals("1");
		walkable[1] = split[1].equals("1");
		walkable[2] = split[2].equals("1");
		walkable[3] = split[3].equals("1");

	}

	// Cartesian to isometric:

	// isoY = (cartX + cartY) / 2;
	public void render(Graphics g, int x, int y, int elevation) {
		g.drawImage(texture, (x - y + 9) * Tiles.HALF_WIDTH, (x + y - 11)
				* Tiles.HALF_HEIGHT - elevation * 5);
	}

	public int entityOffset(Map.Direction dir, float ratio) {
		return 0;
	}

	public boolean isWalkable(Direction dir) {
		return walkable[dir.ordinal()];
	}
}
