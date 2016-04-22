package ui.party;

import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashMap;

import enums.Direction;
import enums.RoleName;

public class RoleHex {
	
	private Polygon hexagon;
	private RoleName name;
	private HashMap<Direction, RoleHex> relationships = new HashMap<Direction, RoleHex>();

	public RoleHex(Polygon hexagon, RoleName name) {
		this.hexagon = hexagon;
		this.name = name;
	}
	
	public RoleName getName() {
		return name;
	}
	
	public Polygon getHexagon() {
		return hexagon;
	}
	
	public void setRelationship(RoleHex hex, Direction direction) {
		relationships.put(direction, hex);
	}
	
	public void setRelationships(HashMap<Direction, RoleHex> hash) {
		this.relationships = hash;
	}
	
	public RoleHex getRelationship(Direction direction) {
		return relationships.get(direction);
	}
	
	public void setRelationships(RoleHex top, RoleHex left, RoleHex right, RoleHex bottom) {
		relationships.put(Direction.UP, top);
		relationships.put(Direction.LEFT, left);
		relationships.put(Direction.RIGHT, right);
		relationships.put(Direction.DOWN, bottom);
	}
	
	public ArrayList<RoleHex> getRelationships() {
		return new ArrayList<RoleHex>(relationships.values());
	}
}
