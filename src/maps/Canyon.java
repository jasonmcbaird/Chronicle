package maps;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import mapLogic.Terrain;
import utilities.Position;
import encounter.Encounter;

public class Canyon extends CustomMap {
	
	private Encounter encounter;
	private BufferedImage wallImage;
	
	public void setup(Encounter encounter) {
		this.encounter = encounter;
		try {
			wallImage = ImageIO.read(this.getClass().getResource("/res/terrain/wall.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setUpWalls();
	}
	
	private void setUpWalls() {
		for(int i = 16; i<=22; i++)
			for(int i2 = 1; i2<=14; i2++)
				addWall(i, i2);
		
		for(int i = 1; i<=15; i++)
			addWall(i, 14);
		
		addWall(1, 1);
		addWall(1, 2);
		addWall(1, 9);
		addWall(1, 10);
		addWall(1, 11);
		addWall(1, 12);
		addWall(1, 13);
		addWall(1, 14);
		
		addWall(2, 2);
		addWall(2, 3);
		addWall(2, 8);
		addWall(2, 9);
		
		addWall(3, 3);
		addWall(3, 8);
		addWall(3, 13);
		
		addWall(4, 3);
		addWall(4, 8);
		addWall(4, 13);
		
		addWall(5, 3);
		addWall(5, 7);
		addWall(5, 8);
		addWall(5, 12);
		addWall(5, 13);
		
		addWall(6, 2);
		addWall(6, 3);
		addWall(6, 7);
		addWall(6, 8);
		addWall(6, 12);
		addWall(6, 13);
		
		addWall(7, 2);
		addWall(7, 3);
		addWall(7, 4);
		addWall(7, 8);
		addWall(7, 9);
		
		addWall(8, 1);
		addWall(8, 2);
		addWall(8, 3);
		addWall(8, 4);
		
		addWall(8, 8);
		addWall(8, 9);
		
		addWall(9, 3);
		addWall(9, 8);
		
		addWall(10, 3);
		addWall(10, 8);
		addWall(10, 12);
		
		addWall(11, 3);
		addWall(11, 4);
		addWall(11, 7);
		addWall(11, 8);
		addWall(11, 11);
		addWall(11, 12);
		
		addWall(12, 8);
		
		addWall(14, 2);
		addWall(14, 3);
		addWall(14, 4);
		
		addWall(15, 1);
		addWall(15, 2);
		addWall(15, 3);
		addWall(15, 4);
		addWall(15, 5);
		addWall(15, 6);
		addWall(15, 7);
	}
	
	private Terrain makeWall(Position position) {
		Terrain terrain = new Terrain(position, wallImage);
		terrain.setCannotOverlap();
		return terrain;
	}
	
	private void addWall(int x, int y) {
		encounter.add(makeWall(new Position(x, y)));
	}
	
	public ArrayList<Position> getPlayerPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(3, 5));
		positions.add(new Position(3, 6));
		positions.add(new Position(2, 4));
		positions.add(new Position(2, 7));
		positions.add(new Position(1, 5));
		positions.add(new Position(1, 8));
		positions.add(new Position(2, 6));
		positions.add(new Position(1, 4));
		return positions;
	}
	
	public ArrayList<Position> getEnemyPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(3, 10));
		positions.add(new Position(3, 2));
		positions.add(new Position(5, 2));
		positions.add(new Position(5, 9));
		positions.add(new Position(9, 2));
		positions.add(new Position(9, 9));
		positions.add(new Position(12, 4));
		positions.add(new Position(12, 7));
		return positions;
	}
}