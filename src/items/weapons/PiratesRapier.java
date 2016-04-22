package items.weapons;

import items.Weapon;
import ability.MutableAbility;
import enums.DamageType;

public class PiratesRapier extends Weapon {
	
	public PiratesRapier() {
		name = "Pirate's Rapier";
		abilities.add(new MutableAbility());
		damageBase = 8;
		damageType = DamageType.STAB;
	}

}
