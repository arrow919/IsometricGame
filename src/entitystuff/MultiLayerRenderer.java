package entitystuff;

import java.util.HashMap;

import org.newdawn.slick.SpriteSheet;

public class MultiLayerRenderer extends AbstractEntityRenderer {
	private HashMap<String, SpriteSheet> spriteSheets;
	private HashMap<Integer, Animation> animations;

	public MultiLayerRenderer(int x, int y) {
		super(x, y);
		animations = new HashMap<Integer, Animation>();
	}

}