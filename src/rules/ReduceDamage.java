package rules;

import java.util.ArrayList;

import queue.Event;
import queue.EventModifier;
import queue.Queue;
import character.Character;
import enums.Condition;
import enums.DamageType;

public class ReduceDamage extends Rule implements EventModifier {
	
	private float amount;
	private ArrayList<DamageType> types = new ArrayList<DamageType>();
	private TakeDamage takeDamage;
	
	public ReduceDamage(float i) {
		amount = i;
	}
	
	public ReduceDamage(float i, DamageType type) {
		amount = i;
		types.add(type);
	}
	
	public ReduceDamage(float i, ArrayList<DamageType> types) {
		amount = i;
		this.types.addAll(types);
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
		if(types.contains(takeDamage.getDamageType()) || types.size() == 0) {
			takeDamage.overrideAmount(Math.round(takeDamage.getAmount() * (1-amount)));
		}
	}
}
