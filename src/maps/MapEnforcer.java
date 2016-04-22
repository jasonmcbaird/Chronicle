package maps;

import java.util.ArrayList;

import utilities.Position;
import encounter.Encounter;

public interface MapEnforcer {
	
	public ArrayList<Position> getPlayerPositions();
	public ArrayList<Position> getEnemyPositions();
	
	public void setup(Encounter encounter);

}
