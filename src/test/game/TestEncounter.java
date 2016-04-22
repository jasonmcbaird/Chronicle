package test.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import ui.grid.EncounterGrid;
import ui.grid.GridInterface;
import character.Character;
import character.Team;
import encounter.Encounter;
import enums.Stat;

public class TestEncounter {
	
	@Test
	public void testGetTeams() {
		EncounterGrid grid = new EncounterGrid();
		Encounter encounter = new Encounter(grid);
		
		setupFourCharacters(encounter);
		
		
		ArrayList<Team> teams = encounter.getTeams();
		
		assertEquals(teams.size(), 3);
		
		ArrayList<String> teamNames = new ArrayList<String>();
		for(Team team: teams) {
			teamNames.add(team.getName());
		}
		
		assertTrue(teams.contains(Team.getPlayerTeam()));
		assertTrue(teams.contains(Team.getEnemyTeam()));
		assertTrue(teams.contains(Team.getNeutralTeam()));
	}
	
	@Test
	public void testSort() {
		EncounterGrid grid = new EncounterGrid();
		Encounter encounter = new Encounter(grid);
		setupFourCharacters(encounter);
		
		ArrayList<Team> teams = encounter.getTeams();
		assertEquals(teams.size(), 3);
		assertEquals(teams.get(0), Team.getPlayerTeam());
		assertEquals(teams.get(1), Team.getNeutralTeam());
		assertEquals(teams.get(2), Team.getEnemyTeam());
	}
	
	@Test
	public void testStart() {
		EncounterGrid grid = new EncounterGrid();
		Encounter encounter = new Encounter(grid);
		setupFourCharacters(encounter);
		ArrayList<Character> characters = encounter.getCharacters();
		
		for(Character character: characters)
			character.attributes.changeStat(Stat.ACCURACY, 10000);
		encounter.startEncounter();
		
		boolean injured = false;
		for(Character character: characters)
			if(character.attributes.getCurrentHealth() < character.attributes.getMaxHealth())
				injured = true;
		assertTrue(injured);
	}
	
	private void setupFourCharacters(Encounter encounter) {
		Character cody = new Character();
		cody.setTeam(Team.getPlayerTeam());
		cody.setName("Cody");
		Character drew = new Character();
		drew.setTeam(Team.getPlayerTeam());
		drew.setName("Drew");
		Character jason = new Character();
		jason.setTeam(Team.getEnemyTeam());
		jason.setName("Jason");
		Character cheyenne = new Character();
		cheyenne.setTeam(Team.getEnemyTeam());
		cheyenne.setName("Cheyenne");
		Character jody = new Character();
		jody.setTeam(Team.getNeutralTeam());
		jody.setName("Jody");
		
		encounter.add(cody);
		encounter.add(drew);
		encounter.add(jason);
		encounter.add(cheyenne);
		encounter.add(jody);
	}

}
