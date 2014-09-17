package mainstuff;
import java.awt.Point;
import java.util.ArrayList;

import mapstuff.Map;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

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
	// The id of the player at this client.
	private int playerID;

	public static final int TICK_TIME = 100;

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
		map.entities.renderEntities(xLoc, yLoc, g, map.kingdoms[playerID].selectedEntities);
	}

	// Method is called before any rendering or updating happens.
	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		startTime = System.currentTimeMillis();
		//map = new MapMaker(64, 64, 4).createMap();
		Tiles.setImages(new SpriteSheet("/res/terrain.png", 16, 16));
		background = new Image(640, 640);
		bgGraphics = background.getGraphics();
		bgGraphics.setColor(Color.white);
		playerID = 0;
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
			// TODO Auto-generated method stub
			Input input = container.getInput();
			if (input.isKeyDown(Input.KEY_LEFT)) {
				xLoc -= 1;
				map.redrawMap = true;
				updateMouseDragSelect(input.getMouseX()/16+xLoc,input.getMouseY()/16+yLoc);
			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				xLoc += 1;
				map.redrawMap = true;
				updateMouseDragSelect(input.getMouseX()/16+xLoc,input.getMouseY()/16+yLoc);
			}
			if (input.isKeyDown(Input.KEY_DOWN)) {
				yLoc += 1;
				map.redrawMap = true;
				updateMouseDragSelect(input.getMouseX()/16+xLoc,input.getMouseY()/16+yLoc);
			}
			if (input.isKeyDown(Input.KEY_UP)) {
				yLoc -= 1;
				map.redrawMap = true;
				updateMouseDragSelect(input.getMouseX()/16+xLoc,input.getMouseY()/16+yLoc);
			}
			if (map.redrawMap) {
				redrawBackground();
				map.redrawMap = false;
			}
			for (int count = 0; count < map.kingdoms.length; count++) {
				if (map.kingdoms[count] != null) {
					map.kingdoms[count].mineQueue.matchEntitiesToMineQueue(map);
				}
			}
			logicalUpdates++;
		}
		System.out.println("Logical updates per second: " + updatesPerSecond);
	}

	// Called when the background needs to be redrawn.
	private void redrawBackground() {
		bgGraphics.clear();
		for (int xCount = 0; xCount < 40; xCount++) {
			for (int yCount = 0; yCount < 40; yCount++) {
				int tileType = map.getTile(xCount + xLoc, yCount + yLoc);

				if (tileType != Tiles.EMPTY_ID && tileType != Tiles.OUTSIDE_ID) {
					bgGraphics.drawImage(Tiles.getTileTexture(tileType), xCount * 16, yCount * 16);

				} else if (tileType == Tiles.EMPTY_ID) {
					bgGraphics.setColor(Color.white);
					bgGraphics.fillRect(xCount * 16, yCount * 16, 16, 16);
				}
				
				if (dragTempSelect.contains(new Point(xCount + xLoc, yCount + yLoc))) {
					bgGraphics.setColor(dragTempSelectColor);
					bgGraphics.fillRect(xCount * 16, yCount * 16, 16, 16);

				} else if (map.kingdoms[playerID].mineQueue.currentlyMiningContains(new Point(xCount + xLoc, yCount + yLoc))) {

					bgGraphics.setColor(new Color(0, 255, 0, 120));
				} else if (map.kingdoms[playerID].mineQueue.currentlyNotMiningContains(new Point(xCount + xLoc, yCount + yLoc))) {

					bgGraphics.setColor(new Color(255, 0, 0, 120));
					bgGraphics.fillRect(xCount * 16, yCount * 16, 16, 16);
				}
			}
		}
		bgGraphics.flush();

	}

	@Override
	public void keyPressed(int key, char c) {
		// TODO Auto-generated method stub
		super.keyPressed(key, c);
		if (key == Input.KEY_LSHIFT || key == Input.KEY_RSHIFT) {
			InputWrapper.shift = true;
		} else if (key == Input.KEY_M && InputWrapper.shift) {
			map.entities.add(EntityFactory.createEntity(EntityFactory.ENTITY_MINER, 11, 11, playerID));
		} else if (key == Input.KEY_LCONTROL || key == Input.KEY_RCONTROL) {
			InputWrapper.ctrl = true;
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		// TODO Auto-generated method stub
		super.keyReleased(key, c);
		if (key == Input.KEY_LSHIFT || key == Input.KEY_RSHIFT) {
			InputWrapper.shift = false;
		} else if (key == Input.KEY_LCONTROL || key == Input.KEY_RCONTROL) {
			InputWrapper.ctrl = false;
		}
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		// TODO Auto-generated method stub
		super.mouseClicked(button, x, y, clickCount);
		if (button == Input.MOUSE_LEFT_BUTTON) {
			if (!InputWrapper.ctrl) {
				int pointX = x / 16 + xLoc;
				int pointY = y / 16 + yLoc;
				byte tileType = map.getTile(pointX, pointY);
				if (tileType != Tiles.OUTSIDE_ID) {
					if (!Tiles.mineable(tileType)) {
						Entity entityClicked = map.entities.getEntityAt(pointX, pointY);
						if (entityClicked != null) {
							if (map.kingdoms[playerID].selectedEntities.contains(entityClicked)) {
								if (InputWrapper.shift) {
									map.kingdoms[playerID].selectedEntities.remove(entityClicked);
								} else {
									map.kingdoms[playerID].selectedEntities.clear();
									map.kingdoms[playerID].selectedEntities.add(entityClicked);
								}
							} else {
								if (!InputWrapper.shift) {
									map.kingdoms[playerID].selectedEntities.clear();
									map.kingdoms[playerID].selectedEntities.add(entityClicked);
								} else {
									map.kingdoms[playerID].selectedEntities.add(entityClicked);
								}
							}

						}
					}
				}
			} else {
				int pointX = x / 16 + xLoc;
				int pointY = y / 16 + yLoc;
				byte tileType = map.getTile(pointX, pointY);
				if (tileType != Tiles.OUTSIDE_ID && Tiles.mineable(tileType)) {
					map.kingdoms[playerID].mineQueue.click(new Point(pointX, pointY));
					map.redrawMap = true;
				}
			}
		}
	}

	private ArrayList<Point> dragTempSelect = new ArrayList<Point>();
	private Color dragTempSelectColor;
	private Color orange = new Color(255, 100, 0, 120);
	private Color yellow = new Color(255, 255, 0, 120);

	private void updateMouseDragSelect(int currentXTile, int currentYTile) {
		dragTempSelect.clear();
		for (int xCount = InputWrapper.mouseDownTileX; xCount < currentXTile; xCount++) {
			for (int yCount = InputWrapper.mouseDownTileX; yCount < currentYTile; yCount++) {
				byte tileType = map.getTile(xCount, yCount);
				if (tileType != Tiles.EMPTY_ID && tileType != Tiles.OUTSIDE_ID) {
					dragTempSelect.add(new Point(xCount, yCount));
					map.redrawMap = true;
				}
			}
		}
		if (map.kingdoms[playerID].mineQueue.contains(new Point(InputWrapper.mouseDownTileX, InputWrapper.mouseDownTileY))) {
			dragTempSelectColor = yellow;
		} else {
			dragTempSelectColor = orange;
		}
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		// TODO Completely rework this mechanic
		super.mouseDragged(oldx, oldy, newx, newy);
		int currentXTile = newx / 16 + xLoc;
		int currentYTile = newy / 16 + yLoc;
		if (InputWrapper.mouseDownTileX > currentXTile) {
			int temp = InputWrapper.mouseDownTileX;
			InputWrapper.mouseDownTileX = currentXTile;
			currentXTile = temp;
		}
		if (InputWrapper.mouseDownTileY > currentYTile) {
			int temp = InputWrapper.mouseDownTileY;
			InputWrapper.mouseDownTileY = currentYTile;
			currentYTile = temp;
		}
		updateMouseDragSelect(currentXTile, currentYTile);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		super.mouseReleased(button, x, y);
		if (!dragTempSelect.isEmpty()) {
			if (dragTempSelectColor == orange) {
				for (Point point : dragTempSelect) {
					if (!map.kingdoms[playerID].mineQueue.contains(new Point(point.x, point.y))) {
						map.kingdoms[playerID].mineQueue.moveTileToNotBeingMined(point);
					}
				}
			} else {
				for (Point point : dragTempSelect) {
					if (map.kingdoms[playerID].mineQueue.contains(new Point(point.x, point.y))) {
						map.kingdoms[playerID].mineQueue.removeTileFromBoth(point);
					}
				}
			}
			map.redrawMap = true;
			dragTempSelect.clear();
		}
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		InputWrapper.mouseDownX = x;
		InputWrapper.mouseDownY = y;
		InputWrapper.mouseDownTileX = (int) x / 16 + xLoc;
		InputWrapper.mouseDownTileY = (int) y / 16 + yLoc;
		InputWrapper.ctrlDownOnMouseDown = InputWrapper.ctrl;
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
