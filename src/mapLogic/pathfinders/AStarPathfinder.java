package mapLogic.pathfinders;

import java.util.ArrayList;

import mapLogic.Entity;

public class AStarPathfinder implements Pathfinder {
	
	private ArrayList<Node> closedNodes = new ArrayList<Node>();
	private SortedList openNodes = new SortedList();
	private SquareBasedMap map;
	private Node[][] nodes;
	private AStarHeuristic heuristic;
	private int width;
	private int height;
	
	public AStarPathfinder(SquareBasedMap map) {
		this.map = map;
		this.heuristic = new ManhattanHeuristic();
		width = map.getWidthInTiles();
		height = map.getHeightInTiles();
		nodes = new Node[width][height];
		for (int x=0;x<map.getWidthInTiles();x++) {
			for (int y=0;y<map.getHeightInTiles();y++) {
				nodes[x][y] = new Node(x,y);
			}
		}
	}
	
	public Path findPath(Entity source, Entity target) {
		ArrayList<Path> paths = new ArrayList<Path>();
		if(target.getX() < width)
			paths.add(findPath(source, source.getX(), source.getY(), target.getX() + 1, target.getY()));
		if(target.getY() < height)
			paths.add(findPath(source, source.getX(), source.getY(), target.getX(), target.getY() + 1));
		if(target.getX() > 0)
			paths.add(findPath(source, source.getX(), source.getY(), target.getX() - 1, target.getY()));
		if(target.getY() > 0)
			paths.add(findPath(source, source.getX(), source.getY(), target.getX(), target.getY() - 1));
		int i = -1;
		Path chosenPath = null;
		for(Path path: paths) {
			if(path == null)
				break;
			if(i == -1 || path.getLength() < i) {
				i = path.getLength();
				chosenPath = path;
			}
		}
		return chosenPath;
	}

	public Path findPath(Entity entity, int startX, int startY, int targetX, int targetY) {
		if(map.blocked(entity, targetX, targetY))
			return null;
		if(startX == targetX && startY == targetY) {
			Path path = new Path();
			path.prependStep(startX, startY);
			return path;
		}
		
		setup(startX, startY, targetX, targetY);
		int maxDepth = 0;
		while (openNodes.size() != 0) {
			Node current = getFirstInOpen();
			if (current == nodes[targetX][targetY]) {
			     break;
			}
			openNodes.remove(current);
			closedNodes.add(current);

			for (int x=-1; x<2 ;x++) {
				for (int y=-1; y<2 ;y++) {
					if ((x == 0) && (y == 0))
						continue;
					
					if (isDiagonal(x, y))
						continue;

					int searchX = x + current.getX();
					int searchY = y + current.getY();

					if(isValidLocation(entity, startX, startY, searchX, searchY)) {	
						int nextStepCost = current.getCost() + getMovementCost(entity, current.getX(), current.getY(), searchX, searchY);
						Node neighbor = nodes[searchX][searchY];
						map.pathFinderVisited(searchX, searchY);
						
						if (nextStepCost < neighbor.getCost()) {
							if (inOpenList(neighbor)) {
								openNodes.remove(neighbor);
							}
							if (inClosedList(neighbor)) {
								closedNodes.remove(neighbor);
							}
						}

						if (!inOpenList(neighbor) && !(inClosedList(neighbor))) {
							neighbor.setCost(nextStepCost);
							neighbor.setParent(current);
							maxDepth = Math.max(maxDepth, neighbor.getDepth());
							neighbor.setHeuristicCost(getHeuristicCost(entity, searchX, searchY, targetX, targetY));
							openNodes.add(neighbor);
						}
					}
				}
			}
		}
		// since we've got an empty open list or we've run out of search 
		// there was no path. Just return null
		if (nodes[targetX][targetY].getParent() == null) {
			return null;
		}

		// At this point we've definitely found a path so we can uses the parent
		// references of the nodes to find out way from the target location back
		// to the start recording the nodes on the way.
		Path path = new Path();
		Node target = nodes[targetX][targetY];
		while (target != nodes[startX][startY]) {
			path.prependStep(target.getX(), target.getY());
			target = target.getParent();
		}
		path.prependStep(startX, startY);

		// thats it, we have our path 
		return path;
	}
	
	private void setup(int startX, int startY, int targetX, int targetY) {
		// initial state for A*. The closed group is empty. Only the starting
		// tile is in the open list and its cost is zero, i.e. we're already there
		nodes[startX][startY].setCost(0);
		nodes[startX][startY].setDepth(0);
		closedNodes.clear();
		openNodes.clear();
		openNodes.add(nodes[startX][startY]);
		// set the parent of the target location to null to mark that 
		// we haven't found a route yet
		nodes[targetX][targetY].setParent(null);
	}
	
	private boolean isDiagonal(int x, int y) {
		if ((x != 0) && (y != 0))
			return true;
		return false;
	}
	
	private boolean inOpenList(Node node) {
		return openNodes.contains(node);
	}
	
	private boolean inClosedList(Node node) {
		return closedNodes.contains(node);
	}
	
	private Node getFirstInOpen() {
		return (Node) openNodes.first();
	}
	
	public float getHeuristicCost(Entity entity, int x, int y, int targetX, int targetY) {
		return heuristic.getCost(map, entity, x, y, targetX, targetY);
	}
	
	private boolean isValidLocation(Entity entity, int startX, int startY, int x, int y) {
		boolean invalid = (x < 0) || (y < 0) || (x >= map.getWidthInTiles()) || (y >= map.getHeightInTiles());
		
		if ((!invalid) && ((startX != x) || (startY != y))) {
			invalid = map.blocked(entity, x, y);
		}
		
		return !invalid;
	}
	
	public int getMovementCost(Entity entity, int startX, int startY, int targetX, int targetY) {
		return map.getCost(entity, startX, startY, targetX, targetY);
	}

}
