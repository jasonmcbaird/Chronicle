package dungeonMode.rooms;

import queue.Queue;
import rules.Heal;
import ui.LogDisplay;
import ui.grid.EncounterGrid;
import character.Character;
import dungeonMode.Dungeon;
import dungeonMode.Party;
import dungeonMode.Room;
import enums.Condition;

public class EverflowingFountain extends Room {
	
	private final int healAmount = 15;
	private Dungeon dungeon;
	
	public EverflowingFountain(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	public void enter(EncounterGrid grid) {
		Character spirit = new Character();
		spirit.setName("Spirit of the Fountain");
		for(Character character: Party.getCharacters()) {
			Queue.addAndRun(new Heal(healAmount), spirit, character, Condition.HEAL);
			LogDisplay.log(character.getName() + " drinks from the fountain, which heals them for " + healAmount + ".");
		}
		dungeon.nextRoom();
	}
}
