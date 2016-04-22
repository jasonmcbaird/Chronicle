package mapLogic.pathfinders;

import mapLogic.Entity;

public interface SquareBasedMap {
	
	public int getWidthInTiles();
    public int getHeightInTiles();
    public boolean blocked(Entity entity, int x, int y);
    public int getCost(Entity entity, int startX, int startY, int targetX, int targetY);
    public void pathFinderVisited(int x, int y);
    
}
