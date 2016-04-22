package enums;

import java.util.ArrayList;


public enum Direction {
	LEFT, RIGHT, DOWN, UP;
	
	public static Direction oppositeDirection(Direction direction) {
		switch (direction) {
		case LEFT: return Direction.RIGHT;
		case RIGHT: return Direction.LEFT;
		case UP: return Direction.DOWN;
		case DOWN: return Direction.UP;
		}
		return null;
	}
	
	public static ArrayList<Direction> perpendicularDirections(Direction direction) {
		ArrayList<Direction> directions = new ArrayList<Direction>();
		switch (direction) {
		case LEFT:
		case RIGHT:
			directions.add(UP);
			directions.add(DOWN);
			return directions;
		case UP:
		case DOWN:
			directions.add(LEFT);
			directions.add(RIGHT);
		}
		return null;
	}
}
