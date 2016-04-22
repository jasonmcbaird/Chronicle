package items.consumable;

import java.util.ArrayList;

import ability.Ability;
import character.Character;
import items.Item;

public class Diamond implements Item {
	
	private String name = "Diamond";
	
	public Diamond() {
	}
	
	public String getName() {
		return name;
	}

	public ArrayList<Ability> getAbilities() {
		return new ArrayList<Ability>();
	}
	
	public void startEncounter(Character character) {
		
	}

}
