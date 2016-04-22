package rules;

import character.Character;
import queue.Event;
import queue.EventModifier;
import queue.Queue;
import enums.Condition;
import enums.DamageType;

public class ModifyDamage extends Rule implements EventModifier {
	
	private DamageType damageType;
	private int percent;
	private TakeDamage takeDamage;
	
	public ModifyDamage(int percent) {
		this.percent = percent;
	}
	
	public ModifyDamage(DamageType damageType, int percent) {
		this.damageType = damageType;
		this.percent = percent;
	}
	
	public void executeOnEvent(Event event, Character source, Character target) {
		takeDamage = (TakeDamage) event;
		Queue.addAndRun(this, source, target, Condition.NOW);
	}
	
	public void executeAfterOnEvent(Event event, Character source, Character target) {
		takeDamage = (TakeDamage) event;
		Queue.add(this, source, target, Condition.AFTER);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		if(damageType == null || damageType == takeDamage.getDamageType())
			takeDamage.overrideAmount(Math.round(takeDamage.getAmount() * percent / 100));
	}

}
