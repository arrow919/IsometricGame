package io;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class EntitySpriteLoader {
	public final static String entitySpriteFolder = "res/entitysprites/";

	public static SpriteSheet loadSheet(String name, int width, int height) {
		try {
			return new SpriteSheet(entitySpriteFolder + name, width, height);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
