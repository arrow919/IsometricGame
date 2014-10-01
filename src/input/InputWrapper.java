package input;

import mapstuff.Directional;
import mapstuff.Directional.Dir;

import org.newdawn.slick.Input;

/**
 * This class is for holding the last time any boolean input took place. A
 * positive long number (the timestamp of the event) is stored if the input is
 * currently true, and a negative value is stored if it is false.
 * 
 * @author RevNate
 * 
 */
public class InputWrapper {
	public static long KEY_A = -1, KEY_D = -1, KEY_W = -1, KEY_S = -1;

	public static void keyEvent(boolean down, int key) {
		long val = -1;
		if (down) {
			val = System.currentTimeMillis();
		}
		switch (key) {
		case Input.KEY_W:
			KEY_W = val;
		case Input.KEY_A:
			KEY_A = val;
		case Input.KEY_S:
			KEY_S = val;
		case Input.KEY_D:
			KEY_D = val;

		}
	}

	public static boolean isDirectionDown() {
		if (KEY_A >= 0 || KEY_D >= 0 || KEY_W >= 0 || KEY_S >= 0) {
			return true;
		}
		return false;
	}

	public static Directional.Dir keyToDirection(int key) {
		if (key == Input.KEY_W) {
			return Dir.NORTH;
		} else if (key == Input.KEY_D) {
			return Dir.EAST;
		} else if (key == Input.KEY_S) {
			return Dir.SOUTH;
		} else if (key == Input.KEY_A) {
			return Dir.WEST;
		}
		return Dir.NORTH;
	}

	public static int getMostRecentDirectional() {
		int key = Input.KEY_W;
		long val = KEY_W;
		if (KEY_D > val) {
			key = Input.KEY_D;
			val = KEY_D;
		}
		if (KEY_S > val) {
			key = Input.KEY_S;
			val = KEY_S;
		}
		if (KEY_A > val) {
			key = Input.KEY_A;
			val = KEY_A;
		}
		return key;
	}
}
