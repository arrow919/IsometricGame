package input;

import java.util.HashMap;

public class InputWrapper {

	private static HashMap<Integer, Boolean> keys = new HashMap<Integer, Boolean>();

	public static boolean isKeyDown(int key){
		Boolean val = keys.get(key);
		if(val==null){
			return false;
		}
		return val;
	}
	public static void setKey(int key, boolean val) {
		keys.put(key, val);
	}
	
}
