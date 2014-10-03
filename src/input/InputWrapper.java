package input;

import java.util.HashMap;

public class InputWrapper {

	private static HashMap<Integer, Long> keys = new HashMap<Integer, Long>();

	public static boolean isKeyDown(int key) {
		return getKeyTime(key)>=0;
	}
	public static long getKeyTime(int key){
		Long val = keys.get(key);
		if(val==null){
			return -1;
		}
		return val;
	}
	public static void setKey(int key, long val) {
		keys.put(key, val);
	}
}
