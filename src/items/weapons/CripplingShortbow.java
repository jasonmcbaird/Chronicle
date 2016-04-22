package items.weapons;

import items.Weapon;
import items.abilities.Cripple;
import ability.MutableAbility;
import enums.DamageType;

public class CripplingShortbow extends Weapon {
	
	public CripplingShortbow() {
		name = "Crippling Shortbow";
		abilities.add(new MutableAbility());
		abilities.add(new Cripple());
		damageBase = 8;
		damageType = DamageType.STAB;
		range = 3;
	}
	
	public boolean twoHanded() {
		return true;
	}

}
