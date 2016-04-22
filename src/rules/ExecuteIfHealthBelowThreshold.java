package rules;

import java.util.ArrayList;

import queue.Event;
import queue.Queue;
import utilities.Logger;
import character.Character;
import enums.Condition;

public class ExecuteIfHealthBelowThreshold extends Rule {
	
	private ArrayList<Event> eventsToExecute = new ArrayList<Event>();
	private int threshold;
	
	public ExecuteIfHealthBelowThreshold(Event event, int thresholdPercent) {
		eventsToExecute.add(event);
		threshold = thresholdPercent;
		Logger.print("Setting an ExecuteIfHealthBelowThreshold on " + event + " if below " + threshold + "% health.", -1);
	}
	
	public ExecuteIfHealthBelowThreshold(ArrayList<Event> events, int thresholdPercent) {
		eventsToExecute = events;
		threshold = thresholdPercent;
		Logger.print("Setting an ExecuteIfHealthBelowThreshold on " + events + " if below " + threshold + "% health.", -1);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Logger.print("Trying to execute " + eventsToExecute);
		if(source.getHealth() < Math.round(threshold * target.getMaxHealth() / 100)) {
			Logger.print("Executing " + eventsToExecute + " because health is below " + threshold + "%.");
			for(Event event: eventsToExecute)
				Queue.addAndRun(event, source, target, Condition.NOW);
		}
	}
}
