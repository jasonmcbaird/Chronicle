package dungeonMode.rooms;

import java.util.ArrayList;

import maps.CustomMap;
import maps.MapBuilder;
import ui.UIComponent;
import ui.UIStack;
import ui.grid.EncounterGrid;
import utilities.Logger;
import character.Character;
import character.Team;
import dungeonMode.Dungeon;
import dungeonMode.Party;
import dungeonMode.Room;
import encounter.Encounter;
import encounter.EncounterEndResponder;
import encounter.TeamBuilder;

public class CombatRoom extends Room implements EncounterEndResponder {
	
	private Dungeon dungeon;
	private String map = "Ruins";
	private String team = "Brigands";
	private int size = 4;
	
	public CombatRoom(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	public void setOpponents(String string, int size) {
		this.size = size;
	}
	
	public void setMap(String string) {
		map = string;
	}
	
	public void enter(EncounterGrid grid) {
		Logger.print("Entering " + map + ".");
		Encounter encounter = new Encounter(grid);
		encounter.setResponder(this);
		ArrayList<Character> party = Party.getCharacters();
		for(Character character: party)
			encounter.add(character);
		ArrayList<Character> cultists = TeamBuilder.build(team, size, Team.getEnemyTeam());
		for(Character character: cultists)
			encounter.add(character);
		CustomMap shrine = MapBuilder.build(map, encounter);
		shrine.setPositions(party, cultists);
		UIStack.push((UIComponent) encounter.getGrid());
		encounter.startEncounter();
	}
	
	public void victory(Encounter encounter) {
		UIStack.pop((UIComponent) encounter.getGrid());
		dungeon.nextRoom();
	}
	
	public void failure(Encounter encounter) {
		
	}

}
