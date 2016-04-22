package rules;

import queue.Queue;
import character.Character;
import enums.Condition;

public class HealPercent extends Rule {
	
	private int amount;
	private int percent;
	
	public HealPercent(int percent) {
		this.percent = percent;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		amount = Math.round(target.getMaxHealth() * percent / 100);
		Queue.addAndRun(new Heal(amount), source, target, Condition.HEAL);
	}
	
	public int getAmount() {
		return amount;
	}	
}
