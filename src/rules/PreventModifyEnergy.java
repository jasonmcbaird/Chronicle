package rules;

import queue.Event;
import queue.EventModifier;
import queue.Queue;
import character.Character;
import enums.Condition;
import enums.EnergyType;
import enums.Sign;

public class PreventModifyEnergy extends Rule implements EventModifier {
	
	private EnergyType energyType;
	private Sign sign;
	private Event event;
	
	public PreventModifyEnergy(EnergyType energyType) {
		this.energyType = energyType;
		persistent = false;
		sign = Sign.BOTH;
	}
	
	public PreventModifyEnergy(EnergyType energyType, Sign sign) {
		this.energyType = energyType;
		this.persistent = false;
		this.sign = sign;
	}
	
	public void executeOnEvent(Event event, Character source, Character target) {
		this.event = event;
		Queue.addAndRun(this, source, target, Condition.NOW);
	}
	
	public void executeAfterOnEvent(Event event, Character source, Character target) {
		this.event = event;
		Queue.add(this, source, target, Condition.AFTER);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		ChangeEnergy modify = (ChangeEnergy) event;
		if(modify.getEnergyType() == energyType) {
			if(matchesSign(modify.getAmount(), sign)) {
				Queue.remove(modify);
			}
		}
	}
	
	private boolean matchesSign(int amount, Sign sign) {
		switch (sign) {
		case POSITIVE:
			if(amount > 0) {
				return true;
			} else {
				return false;
			}
		case NEGATIVE:
			if(amount < 0) {
				return true;
			} else {
				return false;
			}
		case ZERO:
			if(amount == 0) {
				return true;
			} else {
				return false;
			}
		case BOTH:
			return true;
		default:
			return false;
		}
	}
	
}
