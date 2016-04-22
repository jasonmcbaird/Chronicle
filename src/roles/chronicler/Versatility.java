package roles.chronicler;

import ability.Ability;
import character.Character;
import enums.Condition;

public class Versatility extends Ability {

	public Versatility() {
		setPassive(true);
		setName("Versatility");
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		target.inventory.setWeaponLimit(3);
	}
}
