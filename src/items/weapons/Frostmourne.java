package items.weapons;

import items.Weapon;
import roles.warlock.Dreadbolt;
import ability.MutableAbility;
import enums.DamageType;

public class Frostmourne extends Weapon {
	
	public Frostmourne() {
		name = "Frostmourne";
		abilities.add(new Dreadbolt());
		abilities.add(new MutableAbility());
		damageBase = 9;
		damageType = DamageType.FROST;
	}
	
	public boolean twoHanded() {
		return true;
	}

}
