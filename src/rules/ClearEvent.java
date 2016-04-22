package rules;

import java.util.ArrayList;

import queue.Event;
import queue.EventModifier;
import queue.Queue;
import utilities.Dice;
import utilities.Logger;
import character.Character;
import enums.Condition;

public class ClearEvent extends Rule implements EventModifier {
	
	private int percent;
	private ArrayList<Event> eventsToClear = new ArrayList<Event>();
	
	public ClearEvent() {
		percent = -1;
		persistent = true;
	}
	
	public ClearEvent(Event event) {
		eventsToClear.add(event);
		percent = -1;
		persistent = true;
	}
	
	public ClearEvent(ArrayList<Event> eventList) {
		eventsToClear.addAll(eventList);
		percent = -1;
		persistent = true;
	}
	
	public ClearEvent(Event event, int percent) {
		eventsToClear.add(event);
		this.percent = percent;
		persistent = true;
	}
	
	public ClearEvent(ArrayList<Event> eventList, int percent) {
		eventsToClear.addAll(eventList);
		this.percent = percent;
		persistent = true;
	}
	
	public void executeOnEvent(Event event, Character source, Character target) {
		eventsToClear.clear();
		eventsToClear.add(event);
		Queue.addAndRun(this, source, target, Condition.NOW);
	}
	
	public void executeAfterOnEvent(Event event, Character source, Character target) {
		eventsToClear.clear();
		eventsToClear.add(event);
		Queue.add(this, source, target, Condition.AFTER);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Logger.print("Removing events " + eventsToClear);
		if(percent > -1) {
			if(Dice.rollDie(100) <= percent) {
				for(Event event: eventsToClear) {
					Queue.remove(event);
				}
				persistent = false;
				Queue.remove(this);
			}
		} else {
			for(Event event: eventsToClear) {
				Queue.remove(event);
			}
			persistent = false;
			Queue.remove(this);
		}
	}
}
