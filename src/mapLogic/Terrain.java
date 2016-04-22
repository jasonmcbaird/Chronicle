package mapLogic;

import java.awt.image.BufferedImage;

import utilities.Position;

public class Terrain extends Entity {
	
	protected boolean canOverlap;
	protected BufferedImage image;
	
	public Terrain(int x, int y, BufferedImage image) {
		position.setX(x);
		position.setY(y);
		this.canOverlap = true;
		this.image = image;
		name = "Wall";
	}
	
	public Terrain(Position position, BufferedImage image) {
		this.position.setX(position.getX());
		this.position.setY(position.getY());
		this.canOverlap = true;
		this.image = image;
	}
	
	public void setCanOverlap() {
		canOverlap = true;
	}
	
	public void setCannotOverlap() {
		canOverlap = false;
	}

	public boolean getCanOverlap() {
		return canOverlap;
	}

	public BufferedImage getImage() {
		return image;
	}

}
