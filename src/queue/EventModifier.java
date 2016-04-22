package queue;

import enums.Condition;
import character.Character;

public interface EventModifier extends Event {
	public void executeOnEvent(Event event, Character source, Character target);
	public void executeAfterOnEvent(Event event, Character source, Character target);
	public void subExecute(Character source, Character target, Condition condition);
}