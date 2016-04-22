package dungeonMode.rooms;

import items.Item;
import items.armors.Chainmail;
import items.armors.ScaleMail;
import items.weapons.Buckler;
import items.weapons.ColdIronLongsword;
import items.weapons.CripplingShortbow;
import items.weapons.Greataxe;
import items.weapons.InfernoLongbow;
import items.weapons.Thunderclap;

import java.util.ArrayList;

import ui.LogDisplay;
import ui.grid.EncounterGrid;
import utilities.Dice;
import character.Character;
import dungeonMode.Dungeon;
import dungeonMode.Party;
import dungeonMode.Room;

public class Armory extends Room {
	
	private ArrayList<Item> lootItems = new ArrayList<Item>();
	private Dungeon dungeon;
	
	public Armory(Dungeon dungeon) {
		lootItems.add(new ColdIronLongsword());
		lootItems.add(new Chainmail());
		lootItems.add(new Greataxe());
		lootItems.add(new InfernoLongbow());
		lootItems.add(new CripplingShortbow());
		lootItems.add(new Buckler());
		lootItems.add(new Thunderclap());
		lootItems.add(new ScaleMail());
		this.dungeon = dungeon;
	}
	
	public void enter(EncounterGrid grid) {
		Item loot = getRandomLoot();
		Character character = Party.getCharacters().get(0);
		character.inventory.add(loot);
		LogDisplay.log(character.getName() + " loots a " + loot.getName() + " from a treasure chest.");
		dungeon.nextRoom();
	}
	
	private Item getRandomLoot() {
		int roll = Dice.rollDie(lootItems.size());
		return lootItems.get(roll -1);
	}
}
