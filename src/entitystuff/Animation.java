package entitystuff;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Animation {
	private ArrayList<Image> frames;
	private int runtime;

	/**
	 * 
	 * @param sheet
	 *            The spritesheet to use for frames
	 * @param frames
	 *            Count should divide into runtime evenly
	 * @param runtime
	 */
	public Animation(SpriteSheet sheet, int[] frames, int y, int runtime) {
		this.runtime = runtime;
		this.frames = new ArrayList<Image>();
		for (int count = 0; count < frames.length; count++) {
			this.frames.add(sheet.getSprite(frames[count], y));
		}
	}

	public Image getFrame(long time, long startTime) {
		System.out.println("getting frame "+startTime);
		return frames.get((int) ((time - startTime) / runtime % frames.size()));
	}
}
