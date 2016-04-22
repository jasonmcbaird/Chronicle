package items.abilities;

import queue.Queue;
import rules.ClearEvent;
import rules.StatBuff;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.Relationship;
import enums.Stat;

public class Shelter extends Ability {
	
	private final int dodgeBonus = 6;
	
	public Shelter() {
		setName("Shelter");
		setTargetRelationship(Relationship.SELF);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		StatBuff buff = new StatBuff(Stat.DODGE, dodgeBonus);
		ClearEvent clear = new ClearEvent(buff);
		Queue.add(clear, source, target, Condition.START_TURN);
		Queue.add(clear, source, target, Condition.END_ENCOUNTER);
		Queue.addAndRun(buff, source, target, Condition.NOW);
	}
	
}
