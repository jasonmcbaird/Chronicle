package queue;

import enums.Condition;
import character.Character;

public interface Event {
	
	public int getPriority();
	
	public void execute(Character source, Character target, Condition condition);
	
}