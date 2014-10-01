package entitystuff;

import org.newdawn.slick.Graphics;

public abstract class AbstractEntityRenderer {
	private int xOffset, yOffset;

	public AbstractEntityRenderer(int x, int y) {
		xOffset = x;
		yOffset = y;
	}

	public void render(Graphics g, int x, int y) {

	}
}
