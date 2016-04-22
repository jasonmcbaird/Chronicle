package rules;

import queue.Event;
import queue.Queue;
import utilities.Logger;
import character.Character;
import enums.Condition;
import enums.EnergyType;

public class ExecuteIfEmptyEnergy extends Rule {
	
	private Event eventToExecute;
	private EnergyType energyType;
	
	public ExecuteIfEmptyEnergy(Event event, EnergyType energyType) {
		eventToExecute = event;
		this.energyType = energyType;
		Logger.print("Setting an ExecuteIfEmptyEnergy on " + event + " if " + energyType + " empties.", -1);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Logger.print("Trying to execute " + eventToExecute);
		if(source.getEnergy(energyType) <= 0) {
			Logger.print("Executing " + eventToExecute + " because energy is empty");
			Queue.addAndRun(eventToExecute, source, target, Condition.NOW);
		}
	}
	
	public int getPriority() {
		return -10;
	}
	
}
