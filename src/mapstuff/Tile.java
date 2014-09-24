package mapstuff;

import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Tile {
	private final int id;
	private final String name;
	private final Image texture;

	public Tile(HashMap<String, Object> map) {
		this.id = Integer.parseInt((String) map.get("id"));
		this.name = (String) map.get("name");
		this.texture = (Image) map.get("texture");
	}

	// Cartesian to isometric:

	// isoX = cartX - cartY;
	// isoY = (cartX + cartY) / 2;
	public void render(Graphics g, int x, int y, int elevation) {
		System.out.println("Rendering tile type: " + name + " at x,y: " + x
				+ ", " + y);
		g.drawImage(
				texture,
				(y * Tiles.HALF_WIDTH) + (x * Tiles.HALF_WIDTH / 2),
				(x * Tiles.HALF_HEIGHT) - (y * Tiles.HALF_HEIGHT));
	}

	public int entityOffset(Map.Direction dir, float ratio) {
		return 0;
	}
}
