package mapstuff;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tiles {
	private Tiles() {

	}

	// TODO Add tile types
	public static final int OUTSIDE_ID = -1, GRASS_BASE = 1;
	public static Image grassBaseImage; // TODO Template for sprite images

	public static Image getTileImage(int type) {
		switch (type) {
		case GRASS_BASE:
			return grassBaseImage;
		default:
			return null;// Should never get here
		}
	}

	public static void loadTiles() {
		try {
			grassBaseImage = new Image("/res/land/grass.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int yOffsetFor(int tileType) {
		switch (tileType) {
		case GRASS_BASE:
			return 0;
		}
		return 0;
	}
}
