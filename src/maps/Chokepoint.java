package maps;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import mapLogic.Terrain;
import utilities.Position;
import encounter.Encounter;

public class Chokepoint extends CustomMap {
	
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
		
		for(int i = 0; i<= 14; i++)
			for(int i2 = 0; i2<=19; i2++)
				addGrass(i2, i);
		
		for(int i = 0; i <= 14; i++) {
			addTree(0, i);
			addTree(1, i);
			addTree(19, i);
		}
		for(int i = 0; i <= 19; i++) {
			addTree(i, 0);
			addTree(i, 1);
			addTree(i, 14);
		}
		
		addTree(2, 2);
		addTree(2, 3);
		addTree(2, 7);
		addTree(2, 8);
		addTree(2, 9);
		addTree(2, 10);
		addTree(2, 11);
		addTree(2, 12);
		addTree(2, 13);
		
		addTree(3, 2);
		addTree(3, 7);
		addTree(3, 8);
		addTree(3, 9);
		addTree(3, 10);
		addTree(3, 11);
		addTree(3, 12);
		addTree(3, 13);
		
		addTree(4, 2);
		addTree(4, 8);
		addTree(4, 9);
		addTree(4, 10);
		addTree(4, 11);
		addTree(4, 12);
		addTree(4, 13);
		
		addTree(5, 8);
		addTree(5, 9);
		addTree(5, 10);
		addTree(5, 11);
		addWall(5, 12);
		addWall(5, 13);
		
		addTree(6, 10);
		addWall(6, 11);
		addWall(6, 12);
		addWall(6, 13);
		
		addWall(7, 10);
		addWall(7, 11);
		addWall(7, 12);
		addTree(7, 13);
		
		addWall(8, 9);
		addWall(8, 10);
		addWall(8, 11);
		addTree(8, 12);
		addTree(8, 13);
		
		addWall(9, 8);
		addWall(9, 9);
		addWall(9, 10);
		addTree(9, 12);
		addTree(9, 13);
		
		addTree(10, 2);
		addWall(10, 9);
		addTree(10, 12);
		addTree(10, 13);
		
		addTree(11, 2);
		addWall(11, 6);
		addTree(11, 13);
		
		addTree(12, 2);
		addTree(12, 3);
		addTree(12, 4);
		addWall(12, 5);
		addWall(12, 6);
		addWall(12, 7);
		addTree(12, 13);
		
		addTree(13, 2);
		addTree(13, 3);
		addWall(13, 4);
		addWall(13, 5);
		addWall(13, 6);
		addTree(13, 13);
		
		addTree(14, 2);
		addWall(14, 3);
		addWall(14, 4);
		addWall(14, 5);
		
		addWall(15, 2);
		addWall(15, 3);
		addWall(15, 4);
		addTree(15, 5);
		
		addWall(16, 2);
		addWall(16, 3);
		addTree(16, 4);
		addTree(16, 5);
		
		addTree(17, 2);
		addTree(17, 3);
		addTree(17, 4);
		addTree(17, 5);
		addTree(17, 6);
		addTree(17, 7);
		
		addTree(18, 2);
		addTree(18, 3);
		addTree(18, 4);
		addTree(18, 5);
		addTree(18, 6);
		addTree(18, 7);
		addTree(18, 8);
	}
	
	private void addWall(int x, int y) {
		encounter.add(makeWall(new Position(x, y)));
	}
	
	private void addTree(int x, int y) {
		encounter.add(makeTree(new Position(x, y)));
	}
	
	private void addGrass(int x, int y) {
		encounter.add(makeGrass(new Position(x, y)));
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
		positions.add(new Position(6, 5));
		positions.add(new Position(4, 5));
		positions.add(new Position(6, 3));
		positions.add(new Position(5, 4));
		positions.add(new Position(4, 4));
		positions.add(new Position(5, 3));
		positions.add(new Position(6, 2));
		positions.add(new Position(3, 5));
		return positions;
	}
	
	public ArrayList<Position> getEnemyPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(15, 10));
		positions.add(new Position(15, 12));
		positions.add(new Position(17, 10));
		positions.add(new Position(16, 11));
		positions.add(new Position(16, 12));
		positions.add(new Position(17, 11));
		positions.add(new Position(18, 10));
		positions.add(new Position(16, 13));
		return positions;
	}
}