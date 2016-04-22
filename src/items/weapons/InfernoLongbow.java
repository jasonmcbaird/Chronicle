package items.weapons;

import items.Weapon;
import ability.MutableAbility;
import enums.DamageType;

public class InfernoLongbow extends Weapon {
	
	public InfernoLongbow() {
		name = "Inferno Longbow";
		abilities.add(new MutableAbility());
		damageBase = 8;
		damageType = DamageType.FLAME;
		range = 4;
	}
	
	public boolean twoHanded() {
		return true;
	}

}
