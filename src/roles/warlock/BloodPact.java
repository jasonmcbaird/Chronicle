package roles.warlock;

import queue.Queue;
import rules.ExecuteIfHealthAboveThreshold;
import rules.ExecuteIfHealthBelowThreshold;
import rules.HealPercent;
import rules.TakePercentDamage;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.DamageType;

public class BloodPact extends Ability {

	public BloodPact() {
		super();
		setName("Blood Pact");
		setPassive(true);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		TakePercentDamage damage = new TakePercentDamage(DamageType.BLEED, 10);
		ExecuteIfHealthAboveThreshold damageExecutor = new ExecuteIfHealthAboveThreshold(damage, 80);
		damageExecutor.setPersistent(true);
		Queue.add(damageExecutor, source, target, Condition.END_TURN);
		
		HealPercent heal = new HealPercent(10);
		ExecuteIfHealthBelowThreshold healExecutor = new ExecuteIfHealthBelowThreshold(heal, 70);
		healExecutor.setPersistent(true);
		Queue.add(healExecutor, source, target, Condition.END_TURN);
	}
}