package misc;

import java.util.HashMap;

public class Properties {
	private HashMap<String, Object> properties;

	public Properties() {
		properties = new HashMap<String, Object>(10);
	}

	public void addProperty(String str, Object obj) {
		properties.put(str, obj);
	}

	public Object getProperty(String str) {
		return properties.get(str);
	}
}
