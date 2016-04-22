package items.weapons;

import items.Weapon;
import ability.MutableAbility;
import enums.DamageType;

public class Greataxe extends Weapon {
	
	public Greataxe() {
		name = "Greataxe";
		abilities.add(new MutableAbility());
		damageBase = 13;
		damageType = DamageType.SLASH;
	}
	
	public boolean twoHanded() {
		return true;
	}

}
