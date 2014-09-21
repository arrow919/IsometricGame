package mapstuff;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tile {
	private final int id;
	private final String name;
	private final int northM, northB;
	private final int westM, westB;
	private final int offset;
	private final Image texture;

	public Tile(HashMap<String, Object> map) {
		this.id = Integer.parseInt((String)map.get("id"));
		this.name =(String) map.get("name");
		String[] north = ((String)map.get("northline")).split(",");
		String[] west = ((String)map.get("westline")).split(",");
		northM = Integer.parseInt(north[0]);
		northB = Integer.parseInt(north[1]);
		westM = Integer.parseInt(west[0]);
		westB = Integer.parseInt(west[1]);
		this.offset = Integer.parseInt((String)map.get("offset"));
		this.texture = (Image) map.get("texture");
	}

	public void render(Image img, int x, int y) {
		try {
			img.getGraphics().drawImage(texture,
					x * Tiles.TILE_WIDTH / 2 + y * Tiles.TILE_WIDTH / 2,
					y * Tiles.TILE_HEIGHT / 2 + -x * Tiles.TILE_HEIGHT / 2);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int entityOffset(Map.Direction dir, float ratio) {
		return 0;
	}
}
