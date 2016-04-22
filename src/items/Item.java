package items;

import java.util.ArrayList;

import character.Character;
import ability.Ability;

public interface Item {
	
	public ArrayList<Ability> getAbilities();
	public String getName();
	public void startEncounter(Character character);

}
