package rules;

import character.Character;
import enums.Condition;
import queue.Event;
import queue.Queue;

public abstract class Rule implements Event {
	
	protected boolean persistent = false;
	protected int delay = 0;
	protected int priority = 0;
	
	public Rule() {
		// Rule is an abstract class. No instantiating.
	}
	
	public void execute(Character source, Character target, Condition condition) {
		if(delay > 0) {
			delay --;
			returnToQueue(source, target, condition);
			return;
		}
		subExecute(source, target, condition);
		if(persistent)
			returnToQueue(source, target, condition);
	}
	
	protected void subExecute(Character source, Character target, Condition condition) {
		throw new UnsupportedOperationException(this + " cannot respond to subExecute.");
	}
	
	public void nullifyNextExecute() {
		nullifyNextExecute(true);
	}
	
	public void nullifyNextExecute(Boolean bool) {
		if(bool) {
			if(delay < 1) {
				delay(1);
			}
		} else {
			delay(-1);
		}
	}
	
	public void returnToQueue(Character source, Character target, Condition condition) {
		Queue.add(this, source, target, condition);
	}
	
	public void setPersistent(boolean bool) {
		persistent = bool;
	}
	
	public void delay(int delay) {
		this.delay += delay;
		if(delay < 0) {
			delay = 0;
		}
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int i) {
		priority = i;
	}

}
