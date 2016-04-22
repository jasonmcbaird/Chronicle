package items;

import java.util.ArrayList;

import ability.Ability;
import character.Character;

public abstract class Equipment implements EquipmentEnforcer, Item {
	
	protected ArrayList<Ability> abilities = new ArrayList<Ability>();
	protected String name;
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Ability> getAbilities() {
		return abilities;
	}
	
	public void startEncounter(Character character) {
		
	}

}
