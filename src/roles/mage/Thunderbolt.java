package roles.mage;

import mapLogic.GridLogic;
import queue.Queue;
import rules.Attack;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.DamageType;
import enums.EnergyType;
import enums.Relationship;
import enums.Stat;

// Ability:  Thunderbolt
// Level:    2
// Passive:  False
// Involves: ATTACK, LOSE_ENERGY
//
// Rules:   1. When you use this ability, lose (4) mana. If you can't, the ability fails.
//			2. Deal elemental damage to two target characters based on your Magic.

public class Thunderbolt extends Ability {
	
	private final int manaCost = 4;
	
	public Thunderbolt() {
		super();
		setName("Thunderbolt");
		setRange(3);
		setBaseDamage(9);
		setDamageType(DamageType.LIGHTNING);
		setAttackStat(Stat.MAGIC);
	}
	
	public boolean getCanBeActivated(Character character) {
		if(!super.getCanBeActivated(character))
			return false;
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
		Attack attack = new Attack(getDamageBase(), getDamageType(), getAttackStat());
		Queue.addAndRun(attack, source, target, Condition.ATTACK);
		if(attack.wasHit()) {
			Character secondTarget = GridLogic.findNearestTarget(target, Relationship.ALLY);
			if(secondTarget != null && GridLogic.getDistance(target, secondTarget) <= getRange(source)) {
				Queue.addAndRun(new Attack(getDamageBase(), getDamageType(), getAttackStat()), source, secondTarget, Condition.ATTACK);
			}
		}
	}
}
