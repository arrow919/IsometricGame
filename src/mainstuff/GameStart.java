package mainstuff;
import java.awt.Point;
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

import entitystuff.Entity;


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
	public void render(GameContainer container, Graphics g) throws SlickException {
		if (!(xLoc <= -40 || yLoc <= -40 || xLoc > map.getWidth() || yLoc >= map.getHeight())) {
			background.draw();
		}
		map.entities.renderEntities(xLoc, yLoc, g);
	}

	// Method is called before any rendering or updating happens.
	@Override
	public void init(GameContainer container) throws SlickException {
		startTime = System.currentTimeMillis();
		//TODO Initialize the map!!
		Tiles.loadTiles();
		background = new Image(640, 640);
		bgGraphics = background.getGraphics();
		bgGraphics.setColor(Color.white);
		redrawBackground();
	}

	// Called when an update to the game is required.
	private long logicalUpdates = 0;
	private final int targetUpdatesPerSecond = 1000 / TICK_TIME;
	private long startTime;

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		float updatesPerSecond = logicalUpdates / ((System.currentTimeMillis() - startTime) / 1000f);
		if (updatesPerSecond < targetUpdatesPerSecond) {
			map.entities.updateAll(map);
			Input input = container.getInput();
			//TODO do update stuff!!
			logicalUpdates++;
		}
		System.out.println("Logical updates per second: " + updatesPerSecond);
	}

	// Called when the background needs to be redrawn.
	private void redrawBackground() {
		bgGraphics.clear();
		for (int xCount = 0; xCount < 30; xCount++) {
			for (int yCount = 0; yCount < 30; yCount++) {
				//TODO Figure out how I'm gonna draw the map haha
				int tileType = map.getTile(xCount + xLoc, yCount + yLoc);
				//bgGraphics.drawImage(Tiles.getTileImage(tileType), xCount * 16, yCount * 16);

			}
		}
		bgGraphics.flush();

	}

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		//TODO Handle key press
	}

	@Override
	public void keyReleased(int key, char c) {
		super.keyReleased(key, c);
		//TODO Handle key release
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		// TODO Auto-generated method stub
		super.mouseClicked(button, x, y, clickCount);
		//TODO I'm doubtful I'll use mouse clicks
		
	}

	private ArrayList<Point> dragTempSelect = new ArrayList<Point>();
	private Color dragTempSelectColor;
	private Color orange = new Color(255, 100, 0, 120);
	private Color yellow = new Color(255, 255, 0, 120);

	


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
