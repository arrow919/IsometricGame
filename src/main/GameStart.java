package main;

import mapstuff.Map;
import mapstuff.Tile;
import mapstuff.Tiles;
import mapstuff.World;
import misc.Properties;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Input.InputWrapper;
import entitystuff.EntityList;
import entitystuff.EntityList.Entities;
import entitystuff.Player;

public class GameStart extends BasicGame {
	// Size of the screen.
	public static final int screenTileWidth = 40, screenTileHeight = 40;
	// The map of the current game - almost everything is held here.
	private World world;
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
		loadWorld();
		Tiles.loadTiles();
	}

	public void loadWorld() {
		// TODO map loading stuff
		int[][] tileTypes = new int[100][100];
		for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 100; y++) {
				tileTypes[x][y] = 0;
			}
		}
		tileTypes[30][30]=1;
		int[][] tileHeights = new int[100][100];
		EntityList entities = new EntityList();
		Properties props = new Properties();
		props.addProperty(Player.KEY_X, "50");
		props.addProperty(Player.KEY_Y, "50");
		props.addProperty(Player.KEY_CURRENT_HEALTH, "100");
		props.addProperty(Player.KEY_MAX_HEALTH, "100");
		world = new World(new Map(tileTypes, tileHeights),
				(Player) entities.createEntity(Entities.PLAYER, props),
				entities);

	}

	// Called when an update to the game is required.
	private long currentLogicalStep = 0;
	private final int targetUpdatesPerSecond = 1000 / TICK_TIME;
	private long startTime;

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		float updatesPerSecond = currentLogicalStep
				/ ((System.currentTimeMillis() - startTime) / 1000f);
		if (updatesPerSecond < targetUpdatesPerSecond) {
			world.doLogicalStep(currentLogicalStep);
			Input input = container.getInput();
			// TODO do update stuff!!
			currentLogicalStep++;
		}
		//System.out.println("Logical updates per second: " + updatesPerSecond);
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
