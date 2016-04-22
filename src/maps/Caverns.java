package maps;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import mapLogic.Terrain;
import utilities.Position;
import encounter.Encounter;

public class Caverns extends CustomMap {
	
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
		for(int i = 0; i <= 15; i++) {
			encounter.add(makeWall(new Position(20, i)));
		}
		
		for(int i = 1; i <= 20; i++) {
			encounter.add(makeWall(new Position(i, 0)));
			encounter.add(makeWall(new Position(i, 15)));
			encounter.add(makeWall(new Position(i, 16)));
			encounter.add(makeWall(new Position(i, 17)));
		}
		
		for(int x = 21; x <= 24; x++)
			for(int y = 0; y <= 17; y++)
				encounter.add(makeWall(new Position(x, y)));
		
		encounter.add(makeWall(new Position(1, 1)));
		encounter.add(makeWall(new Position(1, 2)));
		encounter.add(makeWall(new Position(1, 12)));
		encounter.add(makeWall(new Position(1, 13)));
		encounter.add(makeWall(new Position(1, 14)));
		
		encounter.add(makeWall(new Position(2, 1)));
		encounter.add(makeWall(new Position(2, 2)));
		encounter.add(makeWall(new Position(2, 3)));
		encounter.add(makeWall(new Position(2, 4)));
		encounter.add(makeWall(new Position(2, 7)));
		encounter.add(makeWall(new Position(2, 8)));
		encounter.add(makeWall(new Position(2, 9)));
		encounter.add(makeWall(new Position(2, 10)));
		encounter.add(makeWall(new Position(2, 11)));
		encounter.add(makeWall(new Position(2, 12)));
		encounter.add(makeWall(new Position(2, 13)));
		encounter.add(makeWall(new Position(2, 14)));
		
		encounter.add(makeWall(new Position(3, 1)));
		encounter.add(makeWall(new Position(3, 2)));
		encounter.add(makeWall(new Position(3, 9)));
		encounter.add(makeWall(new Position(3, 10)));
		encounter.add(makeWall(new Position(3, 11)));
		encounter.add(makeWall(new Position(3, 12)));
		encounter.add(makeWall(new Position(3, 13)));
		encounter.add(makeWall(new Position(3, 14)));
		
		encounter.add(makeWall(new Position(4, 1)));
		encounter.add(makeWall(new Position(4, 2)));
		encounter.add(makeWall(new Position(4, 11)));
		encounter.add(makeWall(new Position(4, 12)));
		encounter.add(makeWall(new Position(4, 13)));
		encounter.add(makeWall(new Position(4, 14)));

		
		encounter.add(makeWall(new Position(5, 1)));
		encounter.add(makeWall(new Position(5, 11)));
		encounter.add(makeWall(new Position(5, 12)));
		encounter.add(makeWall(new Position(5, 13)));
		encounter.add(makeWall(new Position(5, 14)));
		
		encounter.add(makeWall(new Position(6, 4)));
		encounter.add(makeWall(new Position(6, 5)));
		encounter.add(makeWall(new Position(6, 6)));
		encounter.add(makeWall(new Position(6, 11)));
		encounter.add(makeWall(new Position(6, 12)));
		encounter.add(makeWall(new Position(6, 13)));
		encounter.add(makeWall(new Position(6, 14)));
		
		encounter.add(makeWall(new Position(7, 4)));
		encounter.add(makeWall(new Position(7, 5)));
		encounter.add(makeWall(new Position(7, 6)));
		encounter.add(makeWall(new Position(7, 10)));
		encounter.add(makeWall(new Position(7, 11)));
		encounter.add(makeWall(new Position(7, 12)));
		encounter.add(makeWall(new Position(7, 13)));
		encounter.add(makeWall(new Position(7, 14)));
		
		encounter.add(makeWall(new Position(8, 5)));
		encounter.add(makeWall(new Position(8, 6)));
		encounter.add(makeWall(new Position(8, 7)));
		encounter.add(makeWall(new Position(8, 12)));
		encounter.add(makeWall(new Position(8, 13)));
		encounter.add(makeWall(new Position(8, 14)));
		
		encounter.add(makeWall(new Position(9, 1)));
		encounter.add(makeWall(new Position(9, 5)));
		encounter.add(makeWall(new Position(9, 6)));
		encounter.add(makeWall(new Position(9, 7)));
		encounter.add(makeWall(new Position(9, 8)));
		encounter.add(makeWall(new Position(9, 9)));
		encounter.add(makeWall(new Position(9, 10)));
		encounter.add(makeWall(new Position(9, 12)));
		encounter.add(makeWall(new Position(9, 13)));
		encounter.add(makeWall(new Position(9, 14)));
		
		encounter.add(makeWall(new Position(10, 1)));
		encounter.add(makeWall(new Position(10, 2)));
		encounter.add(makeWall(new Position(10, 5)));
		encounter.add(makeWall(new Position(10, 6)));
		encounter.add(makeWall(new Position(10, 7)));
		encounter.add(makeWall(new Position(10, 8)));
		encounter.add(makeWall(new Position(10, 9)));
		encounter.add(makeWall(new Position(10, 10)));
		encounter.add(makeWall(new Position(10, 12)));
		encounter.add(makeWall(new Position(10, 13)));
		encounter.add(makeWall(new Position(10, 14)));
		
		encounter.add(makeWall(new Position(11, 1)));
		encounter.add(makeWall(new Position(11, 2)));
		encounter.add(makeWall(new Position(11, 5)));
		encounter.add(makeWall(new Position(11, 6)));
		encounter.add(makeWall(new Position(11, 7)));
		encounter.add(makeWall(new Position(11, 8)));
		encounter.add(makeWall(new Position(11, 9)));
		encounter.add(makeWall(new Position(11, 10)));
		encounter.add(makeWall(new Position(11, 14)));
		
		encounter.add(makeWall(new Position(12, 1)));
		encounter.add(makeWall(new Position(12, 2)));
		encounter.add(makeWall(new Position(12, 5)));
		encounter.add(makeWall(new Position(12, 6)));
		encounter.add(makeWall(new Position(12, 7)));
		encounter.add(makeWall(new Position(12, 8)));
		encounter.add(makeWall(new Position(12, 9)));
		
		encounter.add(makeWall(new Position(13, 1)));
		encounter.add(makeWall(new Position(13, 7)));
		
		encounter.add(makeWall(new Position(14, 14)));
		
		encounter.add(makeWall(new Position(15, 7)));
		encounter.add(makeWall(new Position(15, 8)));
		encounter.add(makeWall(new Position(15, 14)));
		
		encounter.add(makeWall(new Position(16, 9)));
		encounter.add(makeWall(new Position(16, 13)));
		encounter.add(makeWall(new Position(16, 14)));
		
		encounter.add(makeWall(new Position(17, 9)));
		encounter.add(makeWall(new Position(17, 10)));
		encounter.add(makeWall(new Position(17, 11)));
		encounter.add(makeWall(new Position(17, 12)));
		encounter.add(makeWall(new Position(17, 13)));
		encounter.add(makeWall(new Position(17, 14)));
		
		encounter.add(makeWall(new Position(18, 1)));
		encounter.add(makeWall(new Position(18, 8)));
		encounter.add(makeWall(new Position(18, 9)));
		encounter.add(makeWall(new Position(18, 10)));
		encounter.add(makeWall(new Position(18, 11)));
		encounter.add(makeWall(new Position(18, 12)));
		encounter.add(makeWall(new Position(18, 13)));
		encounter.add(makeWall(new Position(18, 14)));
		
		encounter.add(makeWall(new Position(19, 1)));
		encounter.add(makeWall(new Position(19, 4)));
		encounter.add(makeWall(new Position(19, 5)));
		encounter.add(makeWall(new Position(19, 8)));
		encounter.add(makeWall(new Position(19, 9)));
		encounter.add(makeWall(new Position(19, 10)));
		encounter.add(makeWall(new Position(19, 11)));
		encounter.add(makeWall(new Position(19, 12)));
		encounter.add(makeWall(new Position(19, 13)));
		encounter.add(makeWall(new Position(19, 14)));
	}
	
	private Terrain makeWall(Position position) {
		Terrain terrain = new Terrain(position, wallImage);
		terrain.setCannotOverlap();
		return terrain;
	}
	
	public ArrayList<Position> getPlayerPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(3, 5));
		positions.add(new Position(3, 6));
		positions.add(new Position(2, 5));
		positions.add(new Position(2, 6));
		positions.add(new Position(1, 5));
		positions.add(new Position(1, 6));
		positions.add(new Position(1, 4));
		positions.add(new Position(1, 7));
		return positions;
	}
	
	public ArrayList<Position> getEnemyPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(13, 11));
		positions.add(new Position(14, 3));
		positions.add(new Position(12, 12));
		positions.add(new Position(15, 2));
		positions.add(new Position(13, 10));
		positions.add(new Position(14, 4));
		positions.add(new Position(13, 12));
		positions.add(new Position(14, 5));
		return positions;
	}
}