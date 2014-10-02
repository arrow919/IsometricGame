package input;

import java.util.HashMap;

public class InputWrapper {

	private static HashMap<Integer, Boolean> keys = new HashMap<Integer, Boolean>();

	public static boolean checkKey(int key) {
		return keys.get(key);
	}

	public static void setKey(int key, boolean val) {
		keys.put(key, val);
	}
}
