package rules;

import queue.Event;
import queue.Queue;
import utilities.EventSet;
import utilities.Logger;
import character.Character;
import enums.Condition;
import enums.EnergyType;

public class ExecuteIfLoseEnergy extends Rule {
	
	private Event eventToExecute;
	private EnergyType energyType;
	
	public ExecuteIfLoseEnergy(Event event, EnergyType energyType) {
		eventToExecute = event;
		this.energyType = energyType;
		Logger.print("Setting an ExecuteIfLoseEnergy on " + event + " if " + energyType + " goes down.", -1);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Logger.print("Trying to execute " + eventToExecute);
		for(EventSet set: Queue.getEventSetList(Condition.CHANGE_ENERGY, target)) {
			if(set.getEvent().getClass() == ChangeEnergy.class) {
				ChangeEnergy change = (ChangeEnergy) set.getEvent();
				if(change.getEnergyType() == energyType && change.getAmount() < 0) {
					Logger.print("Executing " + eventToExecute + " because " + target.getName() + " is losing " + energyType + ".");
					Queue.addAndRun(eventToExecute, source, target, Condition.NOW);
				}
			}
		}
	}
}
