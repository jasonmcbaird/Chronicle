package rules;

import character.Character;
import enums.DamageType;
import enums.Stat;

public class WeaponAttack extends Attack {
	
	public WeaponAttack(Character source) {
		super(source.inventory.getMainDamageBase(), source.inventory.getMainDamageType(), source.inventory.getMainStat());
	}
	
	public WeaponAttack(Character source, int damageModifier) {
		super(source.inventory.getMainDamageBase() + damageModifier, source.inventory.getMainDamageType(), source.inventory.getMainStat());
	}
	
	public WeaponAttack(Character source, DamageType damageType) {
		super(source.inventory.getMainDamageBase(), damageType, source.inventory.getMainStat());
	}
	
	public WeaponAttack(Character source, Stat stat) {
		super(source.inventory.getMainDamageBase(), source.inventory.getMainDamageType(), stat);
	}
	
	public WeaponAttack(Character source, int damageModifier, DamageType damageType) {
		super(source.inventory.getMainDamageBase() + damageModifier, damageType, source.inventory.getMainStat());
	}
	
	public WeaponAttack(Character source, int damageModifier, Stat stat) {
		super(source.inventory.getMainDamageBase() + damageModifier, source.inventory.getMainDamageType(), stat);
	}
	
	public WeaponAttack(Character source, DamageType damageType, Stat stat) {
		super(source.inventory.getMainDamageBase(), damageType, stat);
	}

}
