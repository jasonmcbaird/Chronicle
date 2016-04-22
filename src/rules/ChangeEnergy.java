package rules;

import queue.Queue;
import utilities.Dice;
import utilities.Logger;
import character.Character;
import enums.Condition;
import enums.EnergyType;

public class ChangeEnergy extends Rule {
	
	private int amount;
	private int minAmount;
	private int maxAmount = -1;
	private int increase = 0;
	private EnergyType energyType;
	private Boolean random;
	private boolean succeeded = false;
	private boolean clear = false;
	
	public ChangeEnergy(EnergyType energyType, int newAmount) {
		this.energyType = energyType;
		amount = newAmount;
		random = false;
	}
	
	public ChangeEnergy(EnergyType energyType, boolean clear) {
		this.energyType = energyType;
		amount = 0;
		this.clear = clear;
		random = false;
	}
	
	public ChangeEnergy(EnergyType energyType, int minAmount, int maxAmount) {
		this.energyType = energyType;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		random = true;
		alterAmount();
	}

	public void subExecute(Character source, Character target, Condition condition) {
		if(condition != Condition.CHANGE_ENERGY) {
			boolean wasPersistent = persistent;
			persistent = false;
			Queue.addAndRun(this, source, target, Condition.CHANGE_ENERGY);
			persistent = wasPersistent;
			return;
		}
		if(clear) {
			target.resetEnergy(energyType);
			succeeded = true;
		}
		if(energyType == EnergyType.STAMINA && amount < 0 && Math.abs(amount) > target.getEnergy(energyType)) {
			int difference = Math.abs(amount) - target.getEnergy(energyType);
			Logger.print("Burning stamina: " + difference);
			Queue.addAndRun(new BurnStamina(difference), target, target, Condition.NOW);
			amount = amount + difference;
		}
		if(target.changeEnergy(energyType, amount)) {
			succeeded = true;
			if(amount >= 0)
				Logger.print(source.getName() + " is gaining " + amount + " of " + energyType + " to " + source.getEnergy(energyType));
			else
				Logger.print(source.getName() + " is spending " + -amount + " of " + energyType + " to " + source.getEnergy(energyType));
		} else
			succeeded = false;
		alterAmount();
	}
	
	public EnergyType getEnergyType() {
		return energyType;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setIncrease(int i) {
		this.increase = i;
	}
	
	public void setCap(int i) {
		this.maxAmount = i;
	}
	
	private void alterAmount() {
		if(random) {
			amount = Dice.rollDie(maxAmount - minAmount + 1) + minAmount - 1;
		}
		if(increase > 0) {
			amount += increase;
			if(maxAmount > -1 && amount > maxAmount) {
				amount = maxAmount;
			}
		}
	}
	
	public boolean succeeded() {
		return succeeded;
	}
	
}
