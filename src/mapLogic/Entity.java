package mapLogic;

import java.awt.image.BufferedImage;

import utilities.Position;
import enums.Direction;

public abstract class Entity {
	
	protected Position position = new Position(0, 0);
	protected String name;
	
	public Entity() {
		name = "Bitchy thing";
	}
	
	public void move(Direction direction, int i) {
    	switch (direction) {
    	case LEFT:
    		position.setX(position.getX() - i);
    		break;
    	case RIGHT:
    		position.setX(position.getX() + i);
    		break;
    	case UP:
    		position.setY(position.getY() - i);
    		break;
    	case DOWN:
    		position.setY(position.getY() + i);
    		break;
    	}
    }
    
    public void move(int xChange, int yChange) {
    	position.setX(position.getX() + xChange);
    	position.setY(position.getY() + yChange);
    }
	
	public int getX() {
		return position.getX();
	}
	
	public int getY() {
		return position.getY();
	}
	
	public void setX(int i) {
		position.setX(i);
	}
	
	public void setY(int i) {
		position.setY(i);
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public BufferedImage getImage() {
		// This should be overridden.
		return null;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public boolean getCanOverlap() {
		throw new UnsupportedOperationException(this + " cannot respond to canOverlap.");
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String string) {
		name = string;
	}

}
