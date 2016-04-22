package rules;

import queue.Queue;
import utilities.Dice;
import character.Character;
import enums.Condition;

public class RegenerateHealth extends Rule {
	
	int amount;
	int minAmount;
	int maxAmount;
	Boolean random;
	
	public RegenerateHealth(int newAmount) {
		amount = newAmount;
		random = false;
		persistent = true;
	}
	
	public RegenerateHealth(int minAmount, int maxAmount) {
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		random = true;
		randomizeAmount();
		persistent = true;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Queue.addAndRun(new Heal(amount), source, target, Condition.HEAL);
		randomizeAmount();
	}
	
	private void randomizeAmount() {
		if(random) {
			amount = Dice.rollDie(maxAmount - minAmount + 1) + minAmount - 1;
		}
	}
	
}
