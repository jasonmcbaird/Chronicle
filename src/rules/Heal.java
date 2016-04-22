package rules;

import utilities.Dice;
import utilities.Logger;
import character.Character;
import enums.Condition;

public class Heal extends Rule {
	
	private int amount;
	private int minAmount;
	private int maxAmount;
	private Boolean random;
	
	public Heal(int newAmount) {
		amount = newAmount;
		random = false;
	}
	
	public Heal(int minAmount, int maxAmount) {
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		random = true;
		randomizeAmount();
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Logger.get().print(this + " heals " + amount);
		target.heal(amount);
	}
	
	public int getAmount() {
		return amount;
	}
	
	private void randomizeAmount() {
		if(random) {
			amount = Dice.rollDie(maxAmount - minAmount + 1) + minAmount - 1;
		}
	}
	
}
