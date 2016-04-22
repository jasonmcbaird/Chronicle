package rules;

import java.util.ArrayList;

import queue.Event;
import queue.Queue;
import utilities.Logger;
import character.Character;
import enums.Condition;
import enums.Stat;

public class StatBuff extends Rule {
	
	private int amount;
	private Stat stat;
	private ArrayList<Condition> clearConditions = new ArrayList<Condition>();
	private ArrayList<Event> clearEvents = new ArrayList<Event>();
	private int duration = 1;
	
	public StatBuff(Stat stat, int amount) {
		this.stat = stat;
		this.amount = amount;
	}
	
	public StatBuff(Stat stat, int amount, Condition clearCondition) {
		this.stat = stat;
		this.amount = amount;
		clearConditions.add(clearCondition);
	}
	
	public StatBuff(Stat stat, int amount, ArrayList<Condition> clearConditions) {
		this.stat = stat;
		this.amount = amount;
		this.clearConditions.addAll(clearConditions);
	}
	
	public StatBuff(Stat stat, int amount, Condition clearCondition, int duration) {
		this.stat = stat;
		this.amount = amount;
		this.clearConditions.add(clearCondition);
		this.duration = duration;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Rule clearEvent = new StatBuffClear(stat, -amount, duration);
		if(duration > 1)
			clearEvent.setPersistent(true);
		clearEvents.add(clearEvent);
		//Logger.print(stat + "");
		target.attributes.changeStat(stat, amount);
		for(Condition clearCondition: clearConditions) {
			Queue.add(clearEvent, target, target, clearCondition);
		}
		Queue.add(clearEvent, target, target, Condition.END_ENCOUNTER);
	}
	
	public void clearBuff(Character target) {
		if(clearEvents.size() > 0) {
			for(Event clearEvent: clearEvents) {
				Queue.addAndRun(clearEvent, target, target, Condition.NOW);
			}
		} else {
			Logger.print("Trying to clear a buff before running it. It's not working!");
		}
	}
	
}
