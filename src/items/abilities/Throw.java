package items.abilities;

import items.Weapon;
import queue.Queue;
import rules.Attack;
import rules.WeaponAttack;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.DamageType;

public class Throw extends Ability {
	
	private Weapon weapon;
	private DamageType damageType;
	
	public Throw(Weapon weapon) {
		this.weapon = weapon;
		setName("Throw");
		damageType = weapon.getDamageType();
	}
	
	public Throw(Weapon weapon, DamageType damageType) {
		this.weapon = weapon;
		setName("Throw");
		this.damageType = damageType;
	}
	
	public int getRange(Character character) {
		return weapon.getRange(character) + 1;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Attack attack = new WeaponAttack(source, +2, damageType);
		Queue.addAndRun(attack, source, target, Condition.ATTACK);
		source.inventory.remove(weapon);
	}
}
