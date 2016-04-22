package maps;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import mapLogic.Terrain;
import utilities.Position;
import encounter.Encounter;

public class Ruins extends CustomMap {
	
	private Encounter encounter;
	private BufferedImage wallImage;
	private BufferedImage treeImage;
	private BufferedImage backgroundImage;
	
	public void setup(Encounter encounter) {
		this.encounter = encounter;
		try {
			wallImage = ImageIO.read(this.getClass().getResource("/res/terrain/wall.png"));
			treeImage = ImageIO.read(this.getClass().getResource("/res/terrain/tree.png"));
			//backgroundImage = ImageIO.read(this.getClass().getResource("/res/terrain/rollingHills.jpg"));
			backgroundImage = ImageIO.read(this.getClass().getResource("/res/terrain/grassTile.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setUpWalls();
	}
	
	private void setUpWalls() {
		/**Terrain terrain = new Terrain(new Position(12, 17), backgroundImage);
		terrain.setCanOverlap();
		encounter.add(terrain);*/
		
		for(int i = 0; i<= 17; i++) {
			for(int i2 = 0; i2<=24; i2++) {
				encounter.add(makeGrass(new Position(i2, i)));
			}
		}
		
		for(int i = 0; i <= 15; i++) {
			encounter.add(makeTree(new Position(24, i)));
		}
		
		for(int i = 0; i <= 24; i++) {
			encounter.add(makeTree(new Position(i, 0)));
			encounter.add(makeTree(new Position(i, 15)));
			encounter.add(makeTree(new Position(i, 16)));
			encounter.add(makeTree(new Position(i, 17)));
		}
		
		encounter.add(makeWall(new Position(2, 12)));
		
		encounter.add(makeWall(new Position(3, 3)));
		encounter.add(makeWall(new Position(3, 10)));
		encounter.add(makeWall(new Position(3, 12)));
		
		encounter.add(makeWall(new Position(4, 3)));
		encounter.add(makeWall(new Position(4, 4)));
		encounter.add(makeWall(new Position(4, 5)));
		encounter.add(makeWall(new Position(4, 10)));
		encounter.add(makeWall(new Position(4, 12)));
		
		encounter.add(makeWall(new Position(5, 3)));
		encounter.add(makeWall(new Position(5, 10)));
		encounter.add(makeWall(new Position(5, 11)));
		encounter.add(makeWall(new Position(5, 12)));
		
		encounter.add(makeWall(new Position(6, 3)));
		encounter.add(makeWall(new Position(6, 4)));
		encounter.add(makeWall(new Position(6, 5)));
		
		encounter.add(makeWall(new Position(12, 3)));
		encounter.add(makeWall(new Position(12, 4)));
		encounter.add(makeWall(new Position(12, 5)));
		encounter.add(makeWall(new Position(12, 10)));
		encounter.add(makeWall(new Position(12, 11)));
		encounter.add(makeWall(new Position(12, 12)));
		
		encounter.add(makeWall(new Position(13, 3)));
		encounter.add(makeWall(new Position(13, 4)));
		encounter.add(makeWall(new Position(13, 5)));
		encounter.add(makeWall(new Position(13, 10)));
		encounter.add(makeWall(new Position(13, 11)));
		encounter.add(makeWall(new Position(13, 12)));
		
		encounter.add(makeWall(new Position(14, 3)));
		encounter.add(makeWall(new Position(14, 4)));
		encounter.add(makeWall(new Position(14, 5)));
		encounter.add(makeWall(new Position(14, 10)));
		encounter.add(makeWall(new Position(14, 11)));
		encounter.add(makeWall(new Position(14, 12)));
		
		encounter.add(makeWall(new Position(15, 3)));
		encounter.add(makeWall(new Position(15, 4)));
		encounter.add(makeWall(new Position(15, 5)));
		encounter.add(makeWall(new Position(15, 10)));
		encounter.add(makeWall(new Position(15, 11)));
		encounter.add(makeWall(new Position(15, 12)));
		
		encounter.add(makeWall(new Position(16, 3)));
		encounter.add(makeWall(new Position(16, 4)));
		encounter.add(makeWall(new Position(16, 5)));
		encounter.add(makeWall(new Position(16, 10)));
		encounter.add(makeWall(new Position(16, 11)));
		encounter.add(makeWall(new Position(16, 12)));
		
		encounter.add(makeWall(new Position(17, 3)));
		encounter.add(makeWall(new Position(17, 12)));
		
		encounter.add(makeWall(new Position(18, 3)));
		encounter.add(makeWall(new Position(18, 12)));
		
		encounter.add(makeWall(new Position(19, 3)));
		encounter.add(makeWall(new Position(19, 12)));
		
		encounter.add(makeWall(new Position(20, 3)));
		encounter.add(makeWall(new Position(20, 12)));
		
		encounter.add(makeWall(new Position(21, 3)));
		encounter.add(makeWall(new Position(21, 4)));
		encounter.add(makeWall(new Position(21, 5)));
		encounter.add(makeWall(new Position(21, 6)));
		encounter.add(makeWall(new Position(21, 7)));
		encounter.add(makeWall(new Position(21, 8)));
		encounter.add(makeWall(new Position(21, 9)));
		encounter.add(makeWall(new Position(21, 10)));
		encounter.add(makeWall(new Position(21, 11)));
		encounter.add(makeWall(new Position(21, 12)));
	}
	
	private Terrain makeWall(Position position) {
		Terrain terrain = new Terrain(position, wallImage);
		terrain.setCannotOverlap();
		return terrain;
	}
	
	private Terrain makeTree(Position position) {
		Terrain terrain = new Terrain(position, treeImage);
		terrain.setCannotOverlap();
		return terrain;
	}
	
	private Terrain makeGrass(Position position) {
		Terrain terrain = new Terrain(position, backgroundImage);
		terrain.setCanOverlap();
		return terrain;
	}
	
	public ArrayList<Position> getPlayerPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(4, 7));
		positions.add(new Position(3, 8));
		positions.add(new Position(2, 11));
		positions.add(new Position(3, 5));
		positions.add(new Position(5, 5));
		positions.add(new Position(2, 7));
		positions.add(new Position(2, 9));
		positions.add(new Position(1, 8));
		return positions;
	}
	
	public ArrayList<Position> getEnemyPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(18, 7));
		positions.add(new Position(17, 6));
		positions.add(new Position(17, 9));
		positions.add(new Position(19, 4));
		positions.add(new Position(19, 11));
		positions.add(new Position(11, 10));
		positions.add(new Position(11, 5));
		positions.add(new Position(19, 7));
		return positions;
	}
}