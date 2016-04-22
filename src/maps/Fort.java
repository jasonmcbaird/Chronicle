package maps;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import mapLogic.Terrain;
import utilities.Position;
import encounter.Encounter;

public class Fort extends CustomMap {
	
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
		for(int i = 1; i<=13; i++) {
			encounter.add(makeWall(new Position(0, i)));
			encounter.add(makeWall(new Position(18, i)));
		}
		
		for(int i = 1; i<=18; i++) {
			encounter.add(makeWall(new Position(i, 1)));
			encounter.add(makeWall(new Position(i, 13)));
		}
		
		encounter.add(makeWall(new Position(1, 2)));
		encounter.add(makeWall(new Position(1, 3)));
		encounter.add(makeWall(new Position(1, 4)));
		encounter.add(makeWall(new Position(1, 10)));
		encounter.add(makeWall(new Position(1, 11)));
		encounter.add(makeWall(new Position(1, 12)));
		
		encounter.add(makeWall(new Position(2, 2)));
		encounter.add(makeWall(new Position(2, 3)));
		encounter.add(makeWall(new Position(2, 11)));
		encounter.add(makeWall(new Position(2, 12)));
		
		encounter.add(makeWall(new Position(3, 2)));
		encounter.add(makeWall(new Position(3, 12)));
		
		encounter.add(makeWall(new Position(5, 2)));
		encounter.add(makeWall(new Position(5, 3)));
		encounter.add(makeWall(new Position(5, 4)));
		encounter.add(makeWall(new Position(5, 7)));
		encounter.add(makeWall(new Position(5, 10)));
		encounter.add(makeWall(new Position(5, 11)));
		encounter.add(makeWall(new Position(5, 12)));
		
		encounter.add(makeWall(new Position(8, 2)));
		encounter.add(makeWall(new Position(8, 3)));
		encounter.add(makeWall(new Position(8, 4)));
		encounter.add(makeWall(new Position(8, 7)));
		encounter.add(makeWall(new Position(8, 10)));
		encounter.add(makeWall(new Position(8, 11)));
		encounter.add(makeWall(new Position(8, 12)));
		
		encounter.add(makeWall(new Position(9, 7)));
		
		encounter.add(makeWall(new Position(10, 5)));
		encounter.add(makeWall(new Position(10, 6)));
		encounter.add(makeWall(new Position(10, 7)));
		encounter.add(makeWall(new Position(10, 8)));
		encounter.add(makeWall(new Position(10, 9)));
		
		encounter.add(makeWall(new Position(11, 5)));
		encounter.add(makeWall(new Position(11, 6)));
		encounter.add(makeWall(new Position(11, 7)));
		encounter.add(makeWall(new Position(11, 8)));
		encounter.add(makeWall(new Position(11, 9)));
		
		addWall(14, 2);
		addWall(14, 4);
		addWall(14, 5);
		addWall(14, 9);
		addWall(14, 10);
		addWall(14, 12);
		
		addWall(15, 5);
		addWall(15, 9);
		
		addWall(16, 5);
		addWall(16, 9);
		
		addWall(17, 5);
		addWall(17, 9);
		
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
		positions.add(new Position(3, 7));
		positions.add(new Position(2, 6));
		positions.add(new Position(2, 8));
		positions.add(new Position(2, 7));
		positions.add(new Position(1, 7));
		positions.add(new Position(1, 6));
		positions.add(new Position(1, 8));
		positions.add(new Position(1, 9));
		positions.add(new Position(1, 5));
		return positions;
	}
	
	public ArrayList<Position> getEnemyPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(16, 7));
		positions.add(new Position(15, 6));
		positions.add(new Position(15, 8));
		positions.add(new Position(13, 5));
		positions.add(new Position(13, 9));
		positions.add(new Position(13, 11));
		positions.add(new Position(13, 3));
		positions.add(new Position(17, 6));
		positions.add(new Position(17, 8));
		return positions;
	}
}