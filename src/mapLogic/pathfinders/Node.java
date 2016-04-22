package mapLogic.pathfinders;

import utilities.Position;

public class Node extends Position implements Comparable<Node> {
	
	private Node parent;
	private int cost;
	private float heuristicCost;
	private int depth;

	public Node(int x, int y) {
		super(x, y);
	}
	
	public void setParent(Node node) {
		if(parent != null)
			depth = parent.depth + 1;
		parent = node;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public void setCost(int i) {
		cost = i;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setHeuristicCost(float heuristic) {
		this.heuristicCost = heuristic;
	}
	
	public float getHeuristic() {
		return heuristicCost;
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public int compareTo(Node node) {		
		float f = heuristicCost + cost;
		float nodeF = node.heuristicCost + node.cost;
		
		if (f < nodeF) {
			return -1;
		} else if (f > nodeF) {
			return 1;
		} else {
			return 0;
		}
	}

}
