package entitystuff;

import java.util.HashMap;

import org.newdawn.slick.SpriteSheet;

public class EntityRenderer {
	private HashMap<String, SpriteSheet> spriteSheets;
	private HashMap<Integer, Animation> animations;

	public EntityRenderer(int x, int y) {
		animations = new HashMap<Integer, Animation>();
	}

	public void addAnimation(String name)
	
}