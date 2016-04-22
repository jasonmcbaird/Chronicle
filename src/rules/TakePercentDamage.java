package rules;

import queue.Queue;
import character.Character;
import enums.Condition;
import enums.DamageType;

public class TakePercentDamage extends Rule {
	
	private int percent;
	private int amount;
	private DamageType damageType;
	
	public TakePercentDamage(DamageType damageType, int percent) {
		this.damageType = damageType;
		this.percent = percent;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		amount = Math.round(target.getMaxHealth() * percent / 100);
		Queue.addAndRun(new TakeDamage(damageType, amount), source, target, Condition.TAKE_DAMAGE);
	}
	
	public DamageType getDamageType() {
		return damageType;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void overrideAmount(int i) {
		amount = i;
	}
	
}
