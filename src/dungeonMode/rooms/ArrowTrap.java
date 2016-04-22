package dungeonMode.rooms;

import queue.Queue;
import rules.TakeDamage;
import ui.LogDisplay;
import ui.grid.EncounterGrid;
import utilities.Dice;
import character.Character;
import dungeonMode.Dungeon;
import dungeonMode.Party;
import dungeonMode.Room;
import enums.Condition;
import enums.DamageType;
import enums.Stat;

public class ArrowTrap extends Room {
	
	private final int damageAmount = 7;
	private Dungeon dungeon;
	
	public ArrowTrap(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	public void enter(EncounterGrid grid) {
		Character trap = new Character();
		trap.setName("Arrow Trap");
		for(Character character: Party.getCharacters()) {
			if(Dice.rollAgainstDC(12, 5, character.attributes.getStat(Stat.DODGE))) {
				Queue.addAndRun(new TakeDamage(DamageType.STAB, damageAmount), trap, character, Condition.TAKE_DAMAGE);
				LogDisplay.log(character.getName() + " is impaled by a volley of arrows from a trap!");
			} else {
				LogDisplay.log(character.getName() + " dodges the arrows from a trap!");
			}
		}
		dungeon.nextRoom();
	}
}
