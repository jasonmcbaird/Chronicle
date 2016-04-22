package mapLogic.pathfinders;

import mapLogic.Entity;

public class ManhattanHeuristic implements AStarHeuristic {
	/**
	 * @see AStarHeuristic#getCost(TileBasedMap, Mover, int, int, int, int)
	 */
	public float getCost(SquareBasedMap map, Entity entity, int x, int y, int targetX, int targetY) {		
		
		float result = (float) Math.abs(x - targetX) + Math.abs(y - targetY);
		
		return result;
	}

}
