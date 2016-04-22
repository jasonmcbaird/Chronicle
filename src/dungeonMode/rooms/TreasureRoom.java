package dungeonMode.rooms;

import items.Item;
import items.accessories.CrisisGem;
import items.accessories.GuardianAmulet;
import items.accessories.ManaSapphire;

import java.util.ArrayList;

import ui.LogDisplay;
import ui.grid.EncounterGrid;
import utilities.Dice;
import character.Character;
import dungeonMode.Dungeon;
import dungeonMode.Party;
import dungeonMode.Room;

public class TreasureRoom extends Room {
	
	private ArrayList<Item> lootItems = new ArrayList<Item>();
	private Dungeon dungeon;
	
	public TreasureRoom(Dungeon dungeon) {
		lootItems.add(new GuardianAmulet());
		lootItems.add(new CrisisGem());
		lootItems.add(new ManaSapphire());
		this.dungeon = dungeon;
	}
	
	public void enter(EncounterGrid grid) {
		for(Character character: Party.getCharacters()) {
			Item loot = getRandomLoot();
			character.inventory.add(loot);
			LogDisplay.log(character.getName() + " loots a " + loot.getName() + " from a treasure chest.");
		}
		dungeon.nextRoom();
	}
	
	private Item getRandomLoot() {
		int roll = Dice.rollDie(lootItems.size());
		return lootItems.get(roll -1);
	}
}
