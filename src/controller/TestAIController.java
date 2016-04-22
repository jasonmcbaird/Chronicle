package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import ui.grid.EncounterGrid;
import character.Character;
import character.Team;
import encounter.Encounter;
import enums.Stat;

public class TestAIController {
	
	@Test
	public void testStartRound() {
		AIController controller = new AIController();
		Character cody = new Character();
		cody.setTeam(Team.getPlayerTeam());
		cody.setX(7);
		cody.setY(7);
		cody.setName("Cody");
		cody.attributes.changeStat(Stat.ACCURACY, 10000);
		Character jason = new Character();
		jason.setX(5);
		jason.setY(7);
		jason.setName("Jason");
		Character jody = new Character();
		jody.setX(5);
		jody.setY(5);
		jody.setName("Jody");
		EncounterGrid grid = new EncounterGrid();
		
		Encounter encounter = new Encounter(grid);
		controller.setEncounter(encounter);
		
		ArrayList<Character> characters = new ArrayList<Character>();
		characters.add(cody);
		characters.add(jason);
		characters.add(jody);
		
		assertEquals(cody.getX(), 7);
		controller.startRound(Team.getPlayerTeam(), grid);
		assertEquals(cody.getX(), 6);
		assertTrue(jason.attributes.getCurrentHealth() < jason.attributes.getMaxHealth());
	}

}
