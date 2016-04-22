package mapLogic.pathfinders;

import mapLogic.Entity;

public interface AStarHeuristic {
	
	public float getCost(SquareBasedMap map, Entity entity, int x, int y, int tx, int ty);
}
