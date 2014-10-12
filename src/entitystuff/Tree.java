package entitystuff;

import org.newdawn.slick.Graphics;

import mapstuff.Direction;

public class Tree extends Entity {
	private static final String TREE_IMAGE = ""
	private static Image image = new Image(TREE_IMAGE);
	public Tree( Direction dir, int x, int y) {
		super(dir, x , y);

	}

	@Override
	public void render(Graphics mapGraphics, int x, int y, long time) {
		// TODO Figure out the rendering for Tree
		mapGraphics.drawImage(image, x-64,y-100);
		animations.get(animation).get(dir.ordinal())
				.getFrame(time, event.getStartTime()).draw(x - 64, y - 100);
		
	}


}
