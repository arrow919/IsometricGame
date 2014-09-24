package mapstuff;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tiles {
	public static int TILE_WIDTH = 100, TILE_HEIGHT = 50;

	private Tiles() {

	}

	// TODO Add tile types
	public static final int OUTSIDE_ID = -1, GRASS_BASE = 1;
	public static Image grassBaseImage; // TODO Template for sprite images

	public static Image getTileImage(int type) {
		switch (type) {
		case GRASS_BASE:
			return grassBaseImage;
		default:
			return null;// Should never get here
		}
	}

	private static String tileDataFolder = "res/data/tiles.dat";
	private static String tileTexturesFolder = "res/tiletextures/";
	private static HashMap<Integer, Tile> tileObjects;

	public static void loadTiles() {
		tileObjects = new HashMap<Integer, Tile>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(tileDataFolder));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<String, Object> reusable = new HashMap<String, Object>();
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				if (!line.startsWith("#")) {
					reusable.clear();
					String[] pieces = line.split(" ");
					String[] splitPiece = null;
					for (String a : pieces) {
						splitPiece = a.split("=");
						reusable.put(splitPiece[0], splitPiece[1]);
					}
					try {
						reusable.put("texture", new Image(tileTexturesFolder
								+ reusable.get("name") + ".png"));
					} catch (SlickException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String className = (String) reusable.get("classname");
					reusable.remove("classname");
					try {
						Class cl = Class.forName("mapstuff." + className);
						Constructor con = cl.getConstructor(HashMap.class);
						Object obj = con.newInstance(reusable);
						tileObjects.put(
								Integer.parseInt((String) reusable.get("id")),
								(Tile) obj);
					} catch (ClassNotFoundException | NoSuchMethodException
							| SecurityException | InstantiationException
							| IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(reusable);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void renderTile(Graphics g, int type, int x, int y,
			int elevation) {
		tileObjects.get(type).render(g, x, y, elevation);
	}
}
