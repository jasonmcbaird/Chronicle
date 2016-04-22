package rules;

import queue.Event;
import queue.EventModifier;
import queue.Queue;
import utilities.Logger;
import character.Character;
import enums.Condition;

public class ModifyAttack extends Rule implements EventModifier {
	
	private float multiplier;
	private Attack attack;
	
	public ModifyAttack(float multiplier) {
		this.multiplier = multiplier;
	}
	
	public ModifyAttack(Attack attack, float multiplier) {
		this.attack = attack;
		this.multiplier = multiplier;
	}
	
	public void executeOnEvent(Event event, Character source, Character target) {
		attack = (Attack) event;
		Queue.addAndRun(this, source, target, Condition.NOW);
	}
	
	public void executeAfterOnEvent(Event event, Character source, Character target) {
		attack = (Attack) event;
		Queue.add(this, source, target, Condition.AFTER);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		if(attack != null) {
			attack.overrideDamageBase(Math.round(attack.getDamageBase() * multiplier));
		} else {
			Logger.print("Executing a ModifyAttack without an attack to execute on.");
		}
	}
	
}
