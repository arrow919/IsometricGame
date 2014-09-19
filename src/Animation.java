import java.util.ArrayList;

import org.newdawn.slick.Image;

public class Animation {
	private ArrayList<Image> frames;

	public void addFrame(Image frame) {
		frames.add(frame);
	}

	public Image getFrame(int index) {
		return frames.get(index);
	}
}
