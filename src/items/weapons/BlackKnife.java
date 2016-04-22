package items.weapons;

import items.Weapon;
import ability.MutableAbility;
import enums.DamageType;

public class BlackKnife extends Weapon {
	
	public BlackKnife() {
		name = "Black Knife";
		abilities.add(new MutableAbility());
		damageBase = 7;
		damageType = DamageType.STAB;
	}

}
