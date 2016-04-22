package roles.mage;

import java.util.ArrayList;

import ability.Ability;
import queue.Queue;
import rules.Attack;
import rules.Blast;
import rules.ClearEvent;
import rules.ExecuteOnNextTarget;
import rules.Meteor;
import rules.Rule;
import ui.LogDisplay;
import character.Character;
import enums.Condition;
import enums.DamageType;
import enums.EnergyType;
import enums.Stat;

// Ability:  Armageddon
// Level:    10
// Passive:  False
// Involves: START_TURN, LOSE_ENERGY
//
// Rules:   1. When you use this ability, lose (10) mana. If you can't, the ability fails.
//			2. Deal flame damage to a target enemy based on your Magic.
//			3. Deal flame damage to every character surrounding that character based on your Magic.
//			4. At the beginning of your next (3) turns, target a random enemy that you haven't targeted yet.
//			5. Deal flame damage to that enemy based on your Magic.
//			6. Deal flame damage to every character surrounding that enemy based on your Magic.

public class Armageddon extends Ability {
	
	private final int manaCost = 10;
	private final int duration = 3;
	
	public Armageddon() {
		super();
		setName("Armageddon");
		setRange(3);
		setBaseDamage(11);
		setDamageType(DamageType.FLAME);
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
			Queue.addAndRun(new Blast(this, 1), source, target, Condition.NOW);
			LogDisplay.log(getName() + " deals blast damage!");
		}
		ArrayList<Character> targetsHit = new ArrayList<Character>();
		targetsHit.add(target);
		Rule execute = new ExecuteOnNextTarget(new Meteor(this, 1), targetsHit);
		ClearEvent clear = new ClearEvent(execute);
		clear.delay(duration);
		Queue.add(clear, source, source, Condition.START_TURN);
		Queue.add(execute, source, source, Condition.START_TURN);
	}
}
