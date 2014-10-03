package mapstuff;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tiles {
	public static final int TILE_WIDTH = 64, TILE_HEIGHT = 32;
	public static final int HALF_WIDTH = TILE_WIDTH / 2,
			HALF_HEIGHT = TILE_HEIGHT / 2;

	private Tiles() {

	}

	public static final int OCEAN = 2;

	private static String tileDataFile = "res/data/tiles.dat";
	private static String tileTexturesFolder = "res/tiletextures/";
	private static HashMap<Integer, Tile> tileObjects;

	public static void loadTiles() {
		tileObjects = new HashMap<Integer, Tile>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(tileDataFile));
		} catch (FileNotFoundException e) {
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
						reusable.put(
								Tile.KEY_TEXTURE,
								new Image(tileTexturesFolder
										+ reusable.get(Tile.KEY_NAME) + ".png"));
					} catch (SlickException e1) {
						e1.printStackTrace();
					}
					tileObjects.put(Integer.parseInt((String) reusable
							.get(Tile.KEY_ID)), new Tile(reusable));
					System.out.println(reusable);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void renderTile(Graphics g, int type, int x, int y,
			int elevation, long time) {
		tileObjects.get(type).render(g, x, y, elevation, time);
	}

	public static boolean isTileWalkable(int type, Direction dir) {
		return tileObjects.get(type).isWalkable(dir);
	}
}
