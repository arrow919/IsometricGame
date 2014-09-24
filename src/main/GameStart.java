package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import mapstuff.Map;
import mapstuff.Tile;
import mapstuff.Tiles;
import mapstuff.World;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Input.InputWrapper;

public class GameStart extends BasicGame {
	// Size of the screen.
	public static final int screenTileWidth = 40, screenTileHeight = 40;
	// The map of the current game - almost everything is held here.
	private World world;
	// The map buffer.
	private Image background;
	// The graphics of the map buffer.
	private Graphics bgGraphics;
	private double moveRatio = 0;
	public static final int TICK_TIME = 100;

	// Only constructor, calls super().
	public GameStart() {
		super("First 2D Game");
		// TODO Auto-generated constructor stub
	}

	// Render the game world.
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		world.render(g);
	}

	// Method is called before any rendering or updating happens.
	@Override
	public void init(GameContainer container) throws SlickException {
		startTime = System.currentTimeMillis();
		try {
			loadMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tiles.loadTiles();
	}

	public void loadMap() throws IOException {
		// TODO map loading stuff
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("res/maps/1.dat"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<int[]> lines = new ArrayList<int[]>();
		String curLine;
		while ((curLine = br.readLine()) != null) {
			String[] numString = curLine.split(" ");
			int[] nums = new int[numString.length];
			for (int count = 0; count < nums.length; count++) {
				nums[count] = Integer.parseInt(numString[count]);
			}
			lines.add(nums);
		}
		int[][] data = new int[lines.size()][lines.get(0).length];
		for (int count = 0; count < data.length; count++) {
			data[count] = lines.get(count);
		}
		map = new Map(data);
	}

	// Called when an update to the game is required.
	private long logicalUpdates = 0;
	private final int targetUpdatesPerSecond = 1000 / TICK_TIME;
	private long startTime;

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		float updatesPerSecond = logicalUpdates
				/ ((System.currentTimeMillis() - startTime) / 1000f);
		if (updatesPerSecond < targetUpdatesPerSecond) {
			map.entities.updateAll(map);
			Input input = container.getInput();
			// TODO do update stuff!!
			logicalUpdates++;
		}
		System.out.println("Logical updates per second: " + updatesPerSecond);
	}

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		// TODO Insert more key presses here!!
		InputWrapper.keyEvent(true, key);
	}

	@Override
	public void keyReleased(int key, char c) {
		super.keyReleased(key, c);
		// TODO Handle more key releases here!!
		InputWrapper.keyEvent(false, key);
	}

	// The main method of the whole thing.
	public static void main(String[] args) {
		System.out.println(Tile.class.getName());
		AppGameContainer app = null;
		try {
			app = new AppGameContainer(new GameStart());
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// app.setMinimumLogicUpdateInterval(TICK_TIME);
			// app.setMaximumLogicUpdateInterval(TICK_TIME);
			app.setDisplayMode(1000, 640, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
