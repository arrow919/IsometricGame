package main;

import input.InputWrapper;
import mapstuff.Directional;
import mapstuff.Map;
import mapstuff.Tiles;
import mapstuff.World;
import misc.Properties;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import entitystuff.Entity;
import entitystuff.EntityList;
import entitystuff.EntityList.Entities;
import entitystuff.Player;

public class GameStart extends BasicGame {
	public static final int WINDOW_WIDTH = 1000, WINDOW_HEIGHT = 700;
	// The map of the current game - almost everything is held here.
	private World world;
	public static final int TICK_TIME = 100;

	// Only constructor, calls super().
	public GameStart() {
		super("First 2D Game");
	}

	// Render the game world.
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.setBackground(Color.blue);
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
		int[][] tileHeights = new int[100][100];
		for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 100; y++) {
				tileTypes[x][y] = 2;
				if (x > 45 && x < 55 && y > 45 && y < 55) {
					tileTypes[x][y] = 1;
					tileHeights[x][y] = 0;
				}
			}
		}
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
		long time = System.currentTimeMillis();
		float updatesPerSecond = currentLogicalStep
				/ ((time - startTime) / 1000f);
		if (updatesPerSecond < targetUpdatesPerSecond) {
			world.doLogicalStep(currentLogicalStep);
			currentLogicalStep++;
		}
		Player player = world.getPlayer();
		int lastDirectionPressed = InputWrapper.getMostRecentDirectional();
		if (lastDirectionPressed >= 0) {
			if (player.canMove()) {
				Directional.Dir dir = InputWrapper
						.keyToDirection(lastDirectionPressed);
				if (player.getDirection().equals(dir)) {
					world.getPlayer().setAction(Entity.ACTION_WALKING, time);

				} else {
					world.getPlayer().setDirection(
							InputWrapper.keyToDirection(lastDirectionPressed));
				}
			}

		} else {
			if (time - player.getActionStart() >= 1000) {
				player.setAction(Entity.ACTION_IDLE, time);
			}
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		InputWrapper.keyEvent(true, key);
	}

	@Override
	public void keyReleased(int key, char c) {
		super.keyReleased(key, c);
		InputWrapper.keyEvent(false, key);
	}

	// The main method of the whole thing.
	public static void main(String[] args) {
		// System.out.println(Tile.class.getName());
		AppGameContainer app = null;
		try {
			app = new AppGameContainer(new GameStart());
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			// app.setMinimumLogicUpdateInterval(TICK_TIME);
			// app.setMaximumLogicUpdateInterval(TICK_TIME);
			app.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
