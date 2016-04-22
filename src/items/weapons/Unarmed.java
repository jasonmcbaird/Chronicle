package items.weapons;

import items.Weapon;
import ability.MutableAbility;

public class Unarmed extends Weapon {
	
	public Unarmed() {
		name = "Unarmed";
		abilities.add(new MutableAbility());
	}

}
