package mapstuff;

import java.util.HashMap;
import java.util.Random;

import mapstuff.Map.Direction;

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
	private boolean[] walkable = new boolean[4];
	private boolean boatOnly;

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
		boatOnly = ((String) map.get(KEY_BOAT_ONLY)).equals("1");

	}

	public static Random ran = new Random();

	public void render(Graphics g, int x, int y, int elevation, long time) {
		if (id == TYPE_VOID) {
			return;
		}
		int additionalHeight = 0;
		if (id == TYPE_OCEAN) {
			additionalHeight += (Math.sin(x + (time / 15) % 256 / 128.0
					* Math.PI) + 1) * 7;
		}
		int xiso = (x - y + 9) * Tiles.HALF_WIDTH;
		int yiso = (x + y - 11) * Tiles.HALF_HEIGHT - elevation * 10
				+ additionalHeight;
		g.drawImage(texture, xiso, yiso);
	}

	public int entityOffset(Map.Direction dir, float ratio) {
		return 0;
	}

	public boolean isWalkable(Direction dir) {
		return !boatOnly && walkable[dir.ordinal()];
	}
	
	public boolean isBoatable(){
		return boatOnly;
	}
}
