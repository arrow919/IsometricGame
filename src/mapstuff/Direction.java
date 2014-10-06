package mapstuff;

import java.util.ArrayList;

public class Direction {
	/**
	 * Both values can NOT be 0. This is not a direction.
	 * 
	 * @param x
	 *            along the axis of the map (positive is down-right, negative is
	 *            up-left)
	 * @param y
	 *            along the axis of the map (positive is down-left, negative is
	 *            up-right)
	 */
	public final int x, y;
	public static final Direction NORTH = new Direction(-1, -1),
			NORTH_EAST = new Direction(0, -1), EAST = new Direction(1, -1),
			SOUTH_EAST = new Direction(1, 0), SOUTH = new Direction(1, 1),
			SOUTH_WEST = new Direction(0, 1), WEST = new Direction(-1, 1),
			NORTH_WEST = new Direction(-1, 0);
	private static ArrayList<Direction> ordinals = new ArrayList<Direction>();
	static {
		ordinals.add(WEST);
		ordinals.add(NORTH_WEST);
		ordinals.add(NORTH);
		ordinals.add(NORTH_EAST);
		ordinals.add(EAST);
		ordinals.add(SOUTH_EAST);
		ordinals.add(SOUTH);
		ordinals.add(SOUTH_WEST);
	}

	private Direction(int x, int y) {
		this.x = toOne(x);
		this.y = toOne(y);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Direction)) {
			return false;
		}
		Direction dir = (Direction) obj;
		return dir.x == x && dir.y == y;
	}

	private int toOne(int num) {
		if (num < 0) {
			return -1;
		}
		if (num > 0) {
			return 1;
		}
		return 0;
	}

	/**
	 * Returns the y index in a sprite sheet of the direction
	 * 0=west, rotating clockwise (north-west =1, north=2, etc.)
	 * @param dir
	 * @return
	 */
	public int ordinal() {
		return ordinals.indexOf(this);
	}

	public static Direction getDir(int x, int y){
		if(x<0){
			if(y<0){
				return NORTH;
			}
			if(y==0){
				return NORTH_WEST;
			}
			if(y>0){
				return WEST;
			}
		}
		if(x==0){
			if(y<0){
				return NORTH_EAST;
			}
			if(y>0){
				return SOUTH_WEST;
			}
		}
		if(x>0){
			if(y<0){
				return EAST;
			}
			if(y==0){
				return SOUTH_EAST;
			}
			if(y>0){
				return SOUTH;
			}
		}
		throw new RuntimeException("This direction does not exist, nerd.");
	}
	
	@Override
	public String toString(){
		return x+", "+y;
	}
	
	public Direction opposite(){
		return getDir(-x,-y);
	}
}
