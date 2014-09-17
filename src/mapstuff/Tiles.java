package mapstuff;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Tiles {
	private Tiles() {

	}

	public static final byte OUTSIDE_ID = -1, EMPTY_ID = 0, DIRT_ID = 1, STONE_ID = 2, COAL_ID = 3, IRON_ID = 4,
			COPPER_ID = 5;
	public static SpriteSheet textures;
	public static Image dirtImage;
	public static Image coalImage;
	public static Image ironImage;
	public static Image stoneImage;
	public static Image getTileTexture(int type) {
		switch (type) {
		case EMPTY_ID:
			return textures.getSprite(15, 15);
		case DIRT_ID:
			return dirtImage;
		case COAL_ID:
			return coalImage;
		case IRON_ID:
			return ironImage;
		case STONE_ID:
			return stoneImage;
		default:
			return textures.getSprite(15, 15);
		}
	}

	public static void setImages(SpriteSheet sheet) {
		dirtImage = sheet.getSprite(2, 0);
		coalImage = sheet.getSprite(2, 2);
		ironImage = sheet.getSprite(1, 2);
		stoneImage = sheet.getSprite(1,0);
		textures = sheet;
	}

	public static int getTileResistance(byte type) {
		switch (type) {
		case DIRT_ID:
			return 10;
		case STONE_ID:
			return 50;
		case COAL_ID:
			return 70;
		case IRON_ID:
			return 100;
		case COPPER_ID:
			return 100;
		default:
			return 0;
		}
	}

	public static int getTileResourceAmount(byte type) {
		switch (type) {
		case DIRT_ID:
			return 5;
		case STONE_ID:
			return 5;
		case COAL_ID:
			return 4;
		case IRON_ID:
			return 3;
		case COPPER_ID:
			return 3;
		default:
			return 0;
		}
	}
	public static boolean mineable(byte type){
		switch (type) {
		case DIRT_ID:
			return true;
		case STONE_ID:
			return true;
		case COAL_ID:
			return true;
		case IRON_ID:
			return true;
		case COPPER_ID:
			return true;
		default:
			return false;
		}
	}
	public static boolean isTileWalkable(byte type) {
		return type == EMPTY_ID;
	}
}
