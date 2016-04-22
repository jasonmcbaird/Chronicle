package mapLogic.pathfinders;

import mapLogic.Entity;

public interface Pathfinder {
	
	public Path findPath(Entity entity, int startX, int startY, int targetX, int targetY);

}
