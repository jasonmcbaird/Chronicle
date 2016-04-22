package rules;

import queue.Event;
import queue.Queue;
import utilities.Logger;
import character.Character;
import enums.Condition;

public class ExecuteIfHealthAboveThreshold extends Rule {
	
	private Event eventToExecute;
	private int threshold;
	
	public ExecuteIfHealthAboveThreshold(Event event, int thresholdPercent) {
		eventToExecute = event;
		threshold = thresholdPercent;
		Logger.print("Setting an ExecuteIfHealthAboveThreshold on " + event + " if above " + threshold + "% health.", -1);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Logger.print("Trying to execute " + eventToExecute);
		if(source.getHealth() >= Math.round(threshold * target.getMaxHealth() / 100)) {
			Logger.print("Executing " + eventToExecute + " because health is above " + threshold + "%.");
			Queue.addAndRun(eventToExecute, source, target, Condition.NOW);
		}
	}
}
