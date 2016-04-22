package rules;

import queue.Queue;
import character.Character;
import enums.Condition;
import enums.DamageType;

public class Bleed extends Rule {
	
	int amount;
	
	public Bleed(int newAmount) {
		amount = newAmount;
		persistent = true;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Queue.addAndRun(new TakeDamage(DamageType.BLEED, amount), source, target, Condition.TAKE_DAMAGE);
	}
	
}
