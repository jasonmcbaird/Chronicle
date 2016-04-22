package rules;

import queue.EventModifier;
import queue.Queue;
import utilities.EventSet;
import utilities.Logger;
import character.Character;
import enums.Condition;

public class ExecuteOnEventType extends Rule {
	
	private Condition clearCondition;
	private Class<?> clearClass;
	private boolean selfRestriction = false;
	private boolean fired = false;
	private EventModifier modifier;
	private boolean after = false;
	
	public ExecuteOnEventType(Class<?> clearClass, EventModifier modifier) {
		Logger.print("Setting ExecuteOnEventType: " + modifier + " on " + clearClass);
		this.clearClass = clearClass;
		this.modifier = modifier;
	}
	
	public ExecuteOnEventType(Class<?> clearClass, EventModifier modifier, Condition condition) {
		Logger.print("Setting ExecuteOnEventType: " + modifier + " on " + clearClass + " with condition: " + condition);
		this.clearClass = clearClass;
		this.modifier = modifier;
		clearCondition = condition;
	}
	
	public void selfRestriction(Boolean bool) {
		this.selfRestriction = bool;
	}
	
	public void setAfter() {
		after = true;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		fired = false;
		if(clearCondition == null) {
			clearCondition = condition;
		}
		for(EventSet eventSet: Queue.getEventSetList(clearCondition, target)) {
			if(clearClass.isInstance(eventSet.getEvent())) {
				if(!selfRestriction || eventSet.getSource() == eventSet.getTarget()) {
					if(after) {
						Logger.print("Executing after " + modifier + " on " + eventSet.getEvent());
						modifier.executeAfterOnEvent(eventSet.getEvent(), source, target);
					} else {
						Logger.print("Executing " + modifier + " on " + eventSet.getEvent());
						modifier.executeOnEvent(eventSet.getEvent(), source, target);
					}
					fired = true;
				}
			}
		}
	}
	
	public boolean didFireThisRun() {
		return fired;
	}
	
}
