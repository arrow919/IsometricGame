package input;

import java.util.HashMap;

import mapstuff.Direction;

public class InputWrapper {

	private static HashMap<Integer, Boolean> keys = new HashMap<Integer, Boolean>();
	private static Direction dir;

	public static boolean isKeyDown(int key) {
		Boolean val = keys.get(key);
		if (val == null) {
			return false;
		}
		return val;
	}

	public static void setKey(int key, boolean val) {
		keys.put(key, val);
	}

	public static void setDirection(Direction newDir) {
		dir = newDir;
	}

	public static Direction getDir() {
		return dir;
	}
}
