package rules;

import queue.Event;
import queue.EventModifier;
import queue.Queue;
import character.Character;
import enums.Condition;

public class ForceSucceeded extends Rule implements EventModifier {
	
	private Move moveEvent;
	
	@Override
	public void executeOnEvent(Event event, Character source, Character target) {
		this.moveEvent = (Move) event;
		Queue.addAndRun(this, source, target, Condition.NOW);
	}

	@Override
	public void executeAfterOnEvent(Event event, Character source,
			Character target) {
		this.moveEvent = (Move) event;
		Queue.addAndRun(this, source, target, Condition.AFTER);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		moveEvent.overrideSucceeded();
	}
}