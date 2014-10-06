package main;

import input.InputWrapper;
import mapstuff.Direction;
import mapstuff.Map;
import mapstuff.Tiles;
import mapstuff.World;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import entitystuff.EntityList;
import entitystuff.Player;

public class GameStart extends BasicGame {
	public static final int WINDOW_WIDTH = 1280, WINDOW_HEIGHT = 640;
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
		g.clear();
		world.render(g);
	}

	// Method is called before any rendering or updating happens.
	@Override
	public void init(GameContainer container) throws SlickException {
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
				if (x > 47 && x < 53 && y > 47 && y < 53) {
					tileTypes[x][y] = 1;
					tileHeights[x][y] = 0;
				}
			}
		}
		EntityList entities = new EntityList(0);
		Player player = new Player(Direction.SOUTH_WEST, 50, 50);
		entities.addEntity(player);
		world = new World(new Map(tileTypes, tileHeights), player, entities);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		inputHandling();
		world.processEvents();
	}

	private void inputHandling() {
		int x = 0;
		int y = 0;
		boolean w = InputWrapper.isKeyDown(Input.KEY_W);
		boolean a = InputWrapper.isKeyDown(Input.KEY_A);
		boolean s = InputWrapper.isKeyDown(Input.KEY_S);
		boolean d = InputWrapper.isKeyDown(Input.KEY_D);
		x -= w || a ? 1 : 0;
		y -= w || d ? 1 : 0;
		x += s || d ? 1 : 0;
		y += s || a ? 1 : 0;
		if (!(x == 0 && y == 0)) {
			world.getPlayer().wasdInput(Direction.getDir(x, y), world);
		}
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

	@Override
	public void keyPressed(int key, char c) {
		InputWrapper.setKey(key, true);
	}

	@Override
	public void keyReleased(int key, char c) {
		InputWrapper.setKey(key, false);
	}

}
