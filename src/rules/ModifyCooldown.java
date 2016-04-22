package rules;

import queue.Event;
import queue.EventModifier;
import queue.Queue;
import character.Character;
import enums.Condition;

public class ModifyCooldown extends Rule implements EventModifier {
	
	private int amount;
	private Cooldown cooldown;
	
	public ModifyCooldown(int i) {
		amount = i;
	}
	
	public void executeOnEvent(Event event, Character source, Character target) {
		cooldown = (Cooldown) event;
		Queue.addAndRun(event, source, target, Condition.NOW);
	}
	
	public void executeAfterOnEvent(Event event, Character source, Character target) {
		cooldown = (Cooldown) event;
		Queue.add(event, source, target, Condition.AFTER);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		cooldown.modifyCooldown(amount, target);
	}
}
