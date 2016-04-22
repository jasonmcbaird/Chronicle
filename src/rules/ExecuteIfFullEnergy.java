package rules;

import queue.Event;
import queue.Queue;
import utilities.Logger;
import character.Character;
import enums.Condition;
import enums.EnergyType;

public class ExecuteIfFullEnergy extends Rule {
	
	private Event eventToExecute;
	private EnergyType energyType;
	
	public ExecuteIfFullEnergy(Event event, EnergyType energyType) {
		eventToExecute = event;
		this.energyType = energyType;
		Logger.print("Setting an ExecuteIfEnergy on " + event + " if " + energyType + " fills.", -1);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Logger.print("Trying to execute " + eventToExecute);
		if(source.getEnergy(energyType) >= source.getMaxEnergy(energyType)) {
			Logger.print("Executing " + eventToExecute + " because energy is full");
			Queue.addAndRun(eventToExecute, source, target, Condition.NOW);
		}
	}
	
	public int getPriority() {
		return -10;
	}
	
}
