package maps;

import java.util.ArrayList;

import utilities.Position;
import character.Character;

public abstract class CustomMap implements MapEnforcer {
	
	public void setPositions(ArrayList<Character> heroTeam, ArrayList<Character> enemyTeam) {
		ArrayList<Position> playerPositions = getPlayerPositions();
		int i = 0;
		for(Character character: heroTeam) {
			character.setPosition(playerPositions.get(i));
			i++;
		}
		
		ArrayList<Position> enemyPositions = getEnemyPositions();
		i = 0;
		for(Character character: enemyTeam) {
			character.setPosition(enemyPositions.get(i));
			i++;
		}
	}

}
