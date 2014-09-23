package mapstuff;

import misc.Properties;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entitystuff.EntityList;
import entitystuff.EntityList.Entities;
import entitystuff.Player;

public class Map {
	public static enum Direction {
		NORTH, SOUTH, EAST, WEST
	};

	public final Player player;
	public final EntityList entities;
	private int[][] tiles;

	public Map(int[][] data, Player player) {
		this.tiles = data;
		entities = new EntityList();
		entities.createEntity(Entities.PLAYER, new Properties());
		player = entities.getEntity(0);
		init();
	}

	private void init() {
		try {
			mapImage = new Image(1000, 640);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chunks = new Chunk[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				chunks[i][j] = new Chunk(null, -1, -1);
			}
		}
	}

	private Chunk[][] chunks;
	private Image mapImage;

	public void render(int xLoc, int yLoc, double moveRatio, Direction facing,
			Graphics g) {
		// TODO write the render method

	}

	public int getTile(int x, int y) {
		return tiles[x][y];
	}

	public int getWidth() {
		return tiles.length;
	}

	public int getHeight() {
		return tiles[0].length;
	}

	/**
	 * The class used in Map to determine the x and y boundaries that the player
	 * and other entities can walk within.
	 * 
	 * @author RevNate
	 * 
	 */

	private class Chunk {
		public Image img;
		public int chunkx;
		public int chunky;

		public Chunk(Image img, int chunkx, int chunky) {
			this.img = img;
			this.chunkx = chunkx;
			this.chunky = chunky;
		}
	}
}