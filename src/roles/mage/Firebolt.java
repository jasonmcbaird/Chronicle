package roles.mage;

import queue.Queue;
import rules.Attack;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.DamageType;
import enums.EnergyType;
import enums.Stat;

//Ability:  Firebolt
//Level:    0
//Passive:  False
//Involves: TAKE_DAMAGE, LOSE_ENERGY
//
//Rules:    1. Pay 3 mana. If you can't, the spell fizzles.
//			2. Deal elemental damage based on your Magic.

public class Firebolt extends Ability {
	
	private final int manaCost = 3;
	
	public Firebolt() {
		super();
		setName("Firebolt");
		setRange(4);
		setBaseDamage(12);
		setDamageType(DamageType.FLAME);
		setAttackStat(Stat.MAGIC);
	}
	
	public boolean getCanBeActivated(Character character) {
		if(!super.getCanBeActivated(character))
			return false;
		// TODO: This code gets repeated.
		if(canBeActivated) {
			if(character.getEnergy(EnergyType.MANA) < manaCost) {
				return false;
			}
		}
		return true;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		if(!payEnergy(source, EnergyType.MANA, manaCost)) {
			return;
		}
		Queue.addAndRun(new Attack(getDamageBase(), getDamageType(), getAttackStat()), source, target, Condition.ATTACK);
	}
	
}
