package items.weapons;

import items.Weapon;
import ability.MutableAbility;
import enums.DamageType;

public class ColdIronLongsword extends Weapon {
	
	public ColdIronLongsword() {
		name = "Cold Iron Longsword";
		abilities.add(new MutableAbility());
		damageBase = 11;
		damageType = DamageType.SLASH;
	}
}
