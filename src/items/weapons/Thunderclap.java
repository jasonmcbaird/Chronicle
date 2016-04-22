package items.weapons;

import items.Weapon;
import items.abilities.Throw;
import ability.MutableAbility;
import enums.DamageType;

public class Thunderclap extends Weapon {
	
	public Thunderclap() {
		name = "Thunderclap";
		abilities.add(new MutableAbility());
		abilities.add(new Throw(this, DamageType.LIGHTNING));
		damageBase = 10;
		damageType = DamageType.STAB;
	}
	
	public boolean twoHanded() {
		return true;
	}

}
