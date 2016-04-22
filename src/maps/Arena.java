package maps;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import mapLogic.Terrain;
import utilities.Position;
import encounter.Encounter;

public class Arena extends CustomMap {
	
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
		for(int i = 1; i<=15; i++) {
			encounter.add(makeWall(new Position(1, i)));
			encounter.add(makeWall(new Position(15, i)));
			encounter.add(makeWall(new Position(i, 1)));
			encounter.add(makeWall(new Position(i, 15)));
		}
		
		encounter.add(makeWall(new Position(2, 2)));
		encounter.add(makeWall(new Position(2, 3)));
		encounter.add(makeWall(new Position(2, 4)));
		encounter.add(makeWall(new Position(2, 5)));
		encounter.add(makeWall(new Position(2, 6)));
		encounter.add(makeWall(new Position(2, 10)));
		encounter.add(makeWall(new Position(2, 11)));
		encounter.add(makeWall(new Position(2, 12)));
		encounter.add(makeWall(new Position(2, 13)));
		encounter.add(makeWall(new Position(2, 14)));
		
		encounter.add(makeWall(new Position(3, 2)));
		encounter.add(makeWall(new Position(3, 3)));
		encounter.add(makeWall(new Position(3, 4)));
		encounter.add(makeWall(new Position(3, 12)));
		encounter.add(makeWall(new Position(3, 13)));
		encounter.add(makeWall(new Position(3, 14)));
		
		encounter.add(makeWall(new Position(4, 2)));
		encounter.add(makeWall(new Position(4, 3)));
		encounter.add(makeWall(new Position(4, 13)));
		encounter.add(makeWall(new Position(4, 14)));

		
		encounter.add(makeWall(new Position(5, 2)));
		encounter.add(makeWall(new Position(5, 14)));
		
		encounter.add(makeWall(new Position(6, 6)));
		encounter.add(makeWall(new Position(6, 10)));
				
		encounter.add(makeWall(new Position(10, 6)));
		encounter.add(makeWall(new Position(10, 10)));
		
		encounter.add(makeWall(new Position(11, 2)));
		encounter.add(makeWall(new Position(11, 14)));
		
		encounter.add(makeWall(new Position(12, 2)));
		encounter.add(makeWall(new Position(12, 3)));
		encounter.add(makeWall(new Position(12, 13)));
		encounter.add(makeWall(new Position(12, 14)));
		
		encounter.add(makeWall(new Position(13, 2)));
		encounter.add(makeWall(new Position(13, 3)));
		encounter.add(makeWall(new Position(13, 4)));
		encounter.add(makeWall(new Position(13, 12)));
		encounter.add(makeWall(new Position(13, 13)));
		encounter.add(makeWall(new Position(13, 14)));
		
		encounter.add(makeWall(new Position(14, 2)));
		encounter.add(makeWall(new Position(14, 3)));
		encounter.add(makeWall(new Position(14, 4)));
		encounter.add(makeWall(new Position(14, 5)));
		encounter.add(makeWall(new Position(14, 6)));
		encounter.add(makeWall(new Position(14, 10)));
		encounter.add(makeWall(new Position(14, 11)));
		encounter.add(makeWall(new Position(14, 12)));
		encounter.add(makeWall(new Position(14, 13)));
		encounter.add(makeWall(new Position(14, 14)));
	}
	
	private Terrain makeWall(Position position) {
		Terrain terrain = new Terrain(position, wallImage);
		terrain.setCannotOverlap();
		return terrain;
	}
	
	public ArrayList<Position> getPlayerPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(5, 8));
		positions.add(new Position(3, 7));
		positions.add(new Position(3, 9));
		positions.add(new Position(3, 8));
		positions.add(new Position(2, 7));
		positions.add(new Position(2, 9));
		positions.add(new Position(2, 8));
		positions.add(new Position(3, 6));
		positions.add(new Position(3, 10));
		return positions;
	}
	
	public ArrayList<Position> getEnemyPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(11, 8));
		positions.add(new Position(13, 7));
		positions.add(new Position(13, 9));
		positions.add(new Position(13, 8));
		positions.add(new Position(14, 7));
		positions.add(new Position(14, 9));
		positions.add(new Position(14, 8));
		positions.add(new Position(13, 6));
		positions.add(new Position(13, 10));
		return positions;
	}
}