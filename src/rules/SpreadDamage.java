package rules;

import queue.Event;
import queue.EventModifier;
import queue.Queue;
import character.Character;
import enums.Condition;

public class SpreadDamage extends Rule implements EventModifier {
	
	private Character target;
	private int percent;
	private TakeDamage takeDamage;
	private final int minimum = 1;
	
	public SpreadDamage(Character target, int percent) {
		this.target = target;
		this.percent = percent;
	}
	
	public void executeOnEvent(Event event, Character source, Character target) {
		takeDamage = (TakeDamage) event;
		Queue.addAndRun(this, source, target, Condition.NOW);
	}
	
	public void executeAfterOnEvent(Event event, Character source, Character target) {
		takeDamage = (TakeDamage) event;
		Queue.add(this, source, this.target, Condition.AFTER);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		int amount = takeDamage.getAmount() * percent / 100;
		if(amount < minimum)
			amount = minimum;
		Queue.addAndRun(new TakeDamage(takeDamage.getDamageType(), amount), Queue.getSource(takeDamage), this.target, Condition.TAKE_DAMAGE);
	}

}
