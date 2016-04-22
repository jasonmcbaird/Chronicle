package maps;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import mapLogic.Terrain;
import utilities.Position;
import encounter.Encounter;

public class Cathedral extends CustomMap {
	
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
		for(int i = 1; i<=22; i++) {
			encounter.add(makeWall(new Position(i, 1)));
			encounter.add(makeWall(new Position(i, 14)));
		}
		for(int i = 1; i<=14; i++) {
			encounter.add(makeWall(new Position(22, i)));
		}

		encounter.add(makeWall(new Position(1, 1)));
		encounter.add(makeWall(new Position(1, 2)));
		encounter.add(makeWall(new Position(1, 3)));
		encounter.add(makeWall(new Position(1, 4)));
		encounter.add(makeWall(new Position(1, 5)));
		encounter.add(makeWall(new Position(1, 6)));
		encounter.add(makeWall(new Position(1, 9)));
		encounter.add(makeWall(new Position(1, 10)));
		encounter.add(makeWall(new Position(1, 11)));
		encounter.add(makeWall(new Position(1, 12)));
		encounter.add(makeWall(new Position(1, 13)));
		
		encounter.add(makeWall(new Position(2, 1)));
		encounter.add(makeWall(new Position(2, 2)));
		encounter.add(makeWall(new Position(2, 3)));
		encounter.add(makeWall(new Position(2, 4)));
		encounter.add(makeWall(new Position(2, 5)));
		encounter.add(makeWall(new Position(2, 6)));
		encounter.add(makeWall(new Position(2, 9)));
		encounter.add(makeWall(new Position(2, 10)));
		encounter.add(makeWall(new Position(2, 11)));
		encounter.add(makeWall(new Position(2, 12)));
		encounter.add(makeWall(new Position(2, 13)));
		
		encounter.add(makeWall(new Position(5, 4)));
		encounter.add(makeWall(new Position(5, 5)));
		encounter.add(makeWall(new Position(5, 6)));
		encounter.add(makeWall(new Position(5, 9)));
		encounter.add(makeWall(new Position(5, 10)));
		encounter.add(makeWall(new Position(5, 11)));

		encounter.add(makeWall(new Position(7, 4)));
		encounter.add(makeWall(new Position(7, 5)));
		encounter.add(makeWall(new Position(7, 6)));
		encounter.add(makeWall(new Position(7, 9)));
		encounter.add(makeWall(new Position(7, 10)));
		encounter.add(makeWall(new Position(7, 11)));
		
		encounter.add(makeWall(new Position(9, 4)));
		encounter.add(makeWall(new Position(9, 5)));
		encounter.add(makeWall(new Position(9, 6)));
		encounter.add(makeWall(new Position(9, 9)));
		encounter.add(makeWall(new Position(9, 10)));
		encounter.add(makeWall(new Position(9, 11)));
		
		encounter.add(makeWall(new Position(11, 4)));
		encounter.add(makeWall(new Position(11, 5)));
		encounter.add(makeWall(new Position(11, 6)));
		encounter.add(makeWall(new Position(11, 9)));
		encounter.add(makeWall(new Position(11, 10)));
		encounter.add(makeWall(new Position(11, 11)));
		
		encounter.add(makeWall(new Position(15, 2)));
		encounter.add(makeWall(new Position(15, 3)));
		encounter.add(makeWall(new Position(15, 12)));
		encounter.add(makeWall(new Position(15, 13)));
		
		encounter.add(makeWall(new Position(17, 7)));
		encounter.add(makeWall(new Position(17, 8)));

		encounter.add(makeWall(new Position(18, 11)));
		
		encounter.add(makeWall(new Position(19, 10)));
		
		encounter.add(makeWall(new Position(20, 2)));
		encounter.add(makeWall(new Position(20, 3)));
		encounter.add(makeWall(new Position(20, 12)));
		encounter.add(makeWall(new Position(20, 13)));
		
		encounter.add(makeWall(new Position(21, 2)));
		encounter.add(makeWall(new Position(21, 3)));
		encounter.add(makeWall(new Position(21, 12)));
		encounter.add(makeWall(new Position(21, 13)));
	}
	
	private Terrain makeWall(Position position) {
		Terrain terrain = new Terrain(position, wallImage);
		terrain.setCannotOverlap();
		return terrain;
	}
	
	public ArrayList<Position> getPlayerPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(3, 7));
		positions.add(new Position(3, 8));
		positions.add(new Position(2, 7));
		positions.add(new Position(2, 8));
		positions.add(new Position(3, 6));
		positions.add(new Position(3, 9));
		positions.add(new Position(1, 7));
		positions.add(new Position(1, 8));
		return positions;
	}
	
	public ArrayList<Position> getEnemyPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(18, 7));
		positions.add(new Position(18, 10));
		positions.add(new Position(15, 11));
		positions.add(new Position(15, 4));
		positions.add(new Position(13, 13));
		positions.add(new Position(13, 2));
		positions.add(new Position(11, 13));
		positions.add(new Position(11, 2));
		return positions;
	}
}