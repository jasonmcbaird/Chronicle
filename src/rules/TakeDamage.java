package rules;

import queue.Queue;
import utilities.Dice;
import utilities.Logger;
import character.Character;
import enums.Condition;
import enums.DamageType;

public class TakeDamage extends Rule {
	
	private int amount;
	private int minAmount;
	private int maxAmount;
	private Boolean random;
	private DamageType damageType;
	
	public TakeDamage(DamageType damageType, int newAmount) {
		this.damageType = damageType;
		amount = newAmount;
		random = false;
	}
	
	public TakeDamage(DamageType damageType, int minAmount, int maxAmount) {
		this.damageType = damageType;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		random = true;
		randomizeAmount();
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Logger.print(target.getName() + " has " + target.attributes.getCurrentHealth() + " health.", -1);
		Logger.print(target.getName() + " takes " + amount + " " + damageType + " damage from " + source.getName() + ".");
		target.takeDamage(amount, damageType);
		Logger.print(target.getName() + " has " + target.attributes.getCurrentHealth() + " health.", -1);
		if(source != target)
			Queue.run(Condition.DEAL_DAMAGE, source);
		if(target.attributes.getCurrentHealth() <= 0)
			Queue.addAndRun(new Die(), source, target, Condition.DIE);
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
	
	private void randomizeAmount() {
		if(random)
			amount = Dice.rollDie(maxAmount - minAmount + 1) + minAmount - 1;
	}
	
}
