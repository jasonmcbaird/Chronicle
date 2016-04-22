package mapLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import mapLogic.pathfinders.AStarPathfinder;
import mapLogic.pathfinders.Path;
import mapLogic.pathfinders.Pathfinder;
import mapLogic.pathfinders.SquareBasedMap;
import utilities.Logger;
import utilities.Position;
import character.Character;
import encounter.Encounter;
import enums.Direction;
import enums.Relationship;

public class GridLogic implements SquareBasedMap {
	
	private final int width = 30;
	private final int height = 25;
	private static GridLogic instance;
	private static AStarPathfinder pathfinder = new AStarPathfinder(get());
	
	private boolean[][] visited = new boolean[width][height];
	
	public static GridLogic get() {
		if(instance == null)
			instance = new GridLogic();
		return instance;
	}
	
	public static Character findNearestTarget(Character source) {
		return findNearestTarget(source, Relationship.ENEMY);
	}
	
	public static Character findNearestTarget(Character source, Relationship relationship) {
		ArrayList<Character> characters = getTargets(source, relationship);
		Logger.print("Targets: " + characters, -1);
		HashMap<Character, Integer> distances = getPathDistances(source, relationship, characters);
		Logger.print("Distances: " + distances, -1);
		if(distances.isEmpty())
			return null;
		return getRandomMinimumDistanceTarget(distances);
	}
	
	private static Character getRandomMinimumDistanceTarget(HashMap<Character, Integer> distances) {
		int minimumDistance = (Collections.min(distances.values()));
		ArrayList<Character> targets = new ArrayList<Character>();
		for(Entry<Character, Integer> character: distances.entrySet())
            if(character.getValue() == minimumDistance)
            	targets.add(character.getKey());
		Collections.shuffle(targets);
		return targets.get(0);
	}
	
	private static HashMap<Character, Integer> getPathDistances(Character source, Relationship relationship, ArrayList<Character> characters) {
		HashMap<Character, Integer> distances = new HashMap<Character, Integer>();
		for(Character target: characters) {
			Path path =  pathfinder.findPath(source, target);
			if(path != null) {
				int distance = path.getDistance();
				distances.put(target, distance);
			}
		}
		return distances;
	}
	
	private static ArrayList<Character> getTargets(Character source, Relationship relationship) {
		ArrayList<Character> characters = getCharacters();
		ArrayList<Character> targets = new ArrayList<Character>();
		for(Character character: characters)
			if(character.getRelationship(source) == relationship && !character.attributes.getStealthed())
				targets.add(character);
		return targets;
	}
	
	public static ArrayList<Character> getTargetsExcept(Character source, Relationship relationship, ArrayList<Character> except) {
		ArrayList<Character> results = new ArrayList<Character>();
		for(Character character: getTargets(source, relationship))
			if(!except.contains(character))
				results.add(character);
		return results;
	}
	
	public static ArrayList<Character> getTargetsInRange(Character source, int range) {
		ArrayList<Character> targets = getCharacters();
		ArrayList<Character> results = new ArrayList<Character>();
		for(Character character: targets)
			if(getDistance(source, character) <= range)
				results.add(character);
		return results;
	}
	
	public static Direction getDirection(Character source, Character target) {
		int xDistance = getXDistance(source, target.getPosition());
		int yDistance = getYDistance(source, target.getPosition());
		Logger.print("Getting direction. xDistance: " + xDistance + ", yDistance: " + yDistance, -1);
		
		if(Math.abs(xDistance) > Math.abs(yDistance)) {
			if(xDistance < 0) {
				return Direction.RIGHT;
			}
			return Direction.LEFT;
		}
		if(Math.abs(xDistance) < Math.abs(yDistance)) {
			if(yDistance < 0) {
				return Direction.DOWN;
			}
			return Direction.UP;
		}
		
		Random rng = new Random();
		int roll = rng.nextInt(2);
		if(yDistance < 0 && xDistance < 0) {
			if(roll == 0) {
				return Direction.RIGHT;
			}
			return Direction.DOWN;
		}
		if(yDistance < 0 && xDistance > 0) {
			if(roll == 0) {
				return Direction.LEFT;
			}
			return Direction.DOWN;
		}
		if(yDistance > 0 && xDistance < 0) {
			if(roll == 0) {
				return Direction.RIGHT;
			}
			return Direction.UP;
		}
		if(yDistance > 0 && xDistance > 0) {
			if(roll == 0) {
				return Direction.LEFT;
			}
			return Direction.UP;
		}
		return null;
	}
	
	public static boolean checkMove(Entity entity, Direction direction, int i) {
		int x = entity.getX();
		int y = entity.getY();
		switch (direction) {
		case UP:
			y--;
			if(y < 0) { y = 0; };
			return isSquareOpen(x, y, entity);
		case RIGHT:
			x++;
			return isSquareOpen(x, y, entity);
		case DOWN:
			y++;
			return isSquareOpen(x, y, entity);
		case LEFT:
			x--;
			if(x < 0) { x = 0; };
			return isSquareOpen(x, y, entity);
		}
		return false;
	}
	
	public static boolean checkMove(Character character, int xChange, int yChange) {
		int x = character.getX() + xChange;
		int y = character.getY() + yChange;
		return isSquareOpen(x, y, character);
	}
	
	public static boolean isSquareOpen(int x, int y, Entity source) {
		ArrayList<Entity> entities = getEntities();
		for(Entity target: entities) {
			if(target != source) {
				if(!target.getCanOverlap() && x == target.getX() && y == target.getY()) {
					Logger.print(source.getName() + " can't move to " + x + "," + y + " because of " + target.getName(), -1);
					return false;
				}
			}
		}
		return true;
	}

	public static int getDistance(Entity source, Entity target) {
		int distance = getDistance(source, target.getPosition());
		Logger.print(source.getName() + " is " + distance + " squares from " + target.getName(), -1);
		return distance;
	}
	
	public static int getPathDistance(Entity source, Position target) {
		int distance = pathfinder.findPath(source, source.getX(), source.getY(), target.getX(), target.getY()).getDistance();
		Logger.print("Distance from " + source.getName() + " to " + target.getX() + ", " + target.getY() + ": " + distance + ".", -1);
		return distance;
	}
	
	public static int getDistance(Entity source, Position target) {
		int distance = Math.abs(getXDistance(source, target)) + Math.abs(getYDistance(source, target));
		return distance;
	}
	
	private static int getXDistance(Entity source, Position target) {
		return source.getX() - target.getX();
	}
	
	private static int getYDistance(Entity source, Position target) {
		return source.getY() - target.getY();
	}
	
	private static ArrayList<Entity> getEntities() {
		return Encounter.get().getEntities();
	}
	
	private static ArrayList<Character> getCharacters() {
		return Encounter.get().getCharacters();
	}

	@Override
	public int getWidthInTiles() {
		// TODO Real width
		return width;
	}

	@Override
	public int getHeightInTiles() {
		// TODO Real height
		return height;
	}

	@Override
	public boolean blocked(Entity entity, int x, int y) {
		return !isSquareOpen(x, y, entity);
	}

	@Override
	public int getCost(Entity entity, int startX, int startY, int targetX, int targetY) {
		// TODO Different costs if terrain is weird.
		return 1;
	}

	@Override
	public void pathFinderVisited(int x, int y) {
		visited[x][y] = true;
	}
}
