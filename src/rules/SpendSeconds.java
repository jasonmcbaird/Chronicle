package rules;

import queue.Event;
import queue.EventModifier;
import queue.Queue;
import utilities.Logger;
import character.Character;
import enums.Condition;
import enums.EnergyType;

public class SpendSeconds extends Rule implements EventModifier {
	
	private Event event;
	
	public SpendSeconds() {
		
	}

	public SpendSeconds(Event event) {
		this.event = event;
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
		Logger.print("Spending seconds.");
		if(event == null) {
			Logger.print("You tried to execute a " + this + " without setting its event.");
			return;
		}
		if(!(event instanceof CostsTime)) {
			Logger.print("You tried to make an EventTypeSpend with a class that doesn't implement CostsSeconds.");
			return;
		}
		CostsTime costEvent = (CostsTime) event;
		if(!payEnergy(source, EnergyType.SECONDS, costEvent)) {
			Queue.remove(event);
		}
	}
	
	private boolean payEnergy(Character source, EnergyType energyType, CostsTime costs) {
		int amount = costs.getSeconds(source);
		ChangeEnergy modify = new ChangeEnergy(energyType, -amount);
		Queue.addAndRun(modify, source, source, Condition.CHANGE_ENERGY);
		if(modify.succeeded())
			return true;
		return false;
	}
}
