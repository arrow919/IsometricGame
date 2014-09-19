package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import mapstuff.Map;
import mapstuff.Tiles;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
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
	private Map map;
	// The x and y location of the camera.
	private int xLoc, yLoc;
	// The map buffer.
	private Image background;
	// The graphics of the map buffer.
	private Graphics bgGraphics;

	public static final int TICK_TIME = 50;

	// Only constructor, calls super().
	public GameStart() {
		super("First 2D Game");
		// TODO Auto-generated constructor stub
	}

	// Render the game world.
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		map.render(xLoc, yLoc, g);
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
		background = new Image(640, 640);
		bgGraphics = background.getGraphics();
		bgGraphics.setColor(Color.white);
		redrawBackground();
	}

	public void loadMap() throws IOException {
		// TODO map loading stuff
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("res/maps/1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> lines = new ArrayList<String>();
		String curLine;
		while ((curLine = br.readLine()) != null) {
			lines.add(curLine);
		}
		String[] chars = lines.get(0).split(" ");
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

	// Called when the background needs to be redrawn.
	private void redrawBackground() {
		bgGraphics.clear();
		for (int xCount = 0; xCount < 30; xCount++) {
			for (int yCount = 0; yCount < 30; yCount++) {
				// TODO Figure out how I'm gonna draw the map haha
				int tileType = map.getTile(xCount + xLoc, yCount + yLoc);
				// bgGraphics.drawImage(Tiles.getTileImage(tileType), xCount *
				// 16, yCount * 16);

			}
		}
		bgGraphics.flush();

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
			app.setDisplayMode(640, 640, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
