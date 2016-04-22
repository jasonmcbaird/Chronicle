package items.weapons;

import items.Weapon;
import roles.berserker.Gut;
import ability.MutableAbility;
import enums.DamageType;

public class Dreadaxe extends Weapon {
	
	public Dreadaxe() {
		name = "Dreadaxe";
		abilities.add(new Gut());
		abilities.add(new MutableAbility());
		damageBase = 11;
		damageType = DamageType.FLAME;
	}
	
	public boolean twoHanded() {
		return true;
	}

}
