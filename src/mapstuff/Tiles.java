package mapstuff;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tiles {
	private Tiles() {

	}
	//TODO Add tile types
	public static final byte OUTSIDE_ID = -1,  GRASS = 1;
	public static Image grassImage; // TODO Template for sprite images

	public static Image getTileImage(int type) {
		switch (type) {
		case GRASS:
			return grassImage;
		default:
			return null;//Should never get here
		}
	}

	public static void loadTiles() {
		try {
			grassImage=new Image("/res/land/PNG/grass.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
