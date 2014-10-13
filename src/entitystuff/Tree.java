package entitystuff;

import mapstuff.Direction;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tree extends Entity {
	private static final String TREE_IMAGE = "entitysprites/tree1S.png";
	private static Image image;
	static {
		try {
			image = new Image(TREE_IMAGE);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Tree(Direction dir, int x, int y) {
		super(dir, x, y);

	}

	@Override
	public void render(Graphics mapGraphics, int x, int y, long time) {
		// TODO Figure out the rendering for Tree
		mapGraphics.drawImage(image, x - 64, y - 100);
	}

}
