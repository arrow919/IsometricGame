package misc;

import java.util.HashMap;
import java.util.Set;

public class Properties {
	private HashMap<String, String> properties;

	public Properties() {
		properties = new HashMap<String, String>(10);
	}

	public void addProperty(String str, String obj) {
		properties.put(str, obj);
	}

	public String getProperty(String str) {
		return properties.get(str);
	}

	public Set<String> keys() {
		return properties.keySet();
	}
}
