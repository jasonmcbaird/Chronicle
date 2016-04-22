package roles.fate;

import queue.Queue;
import rules.Revive;
import ability.Ability;
import character.Character;
import enums.Condition;

//Ability:  Twist Fate
//Level:    5
//Passive:  True
//Involves: DIE
//
//Rules:    1. The first time you would die during each encounter, survive and restore yourself to 50% health.

public class TwistFate extends Ability {

	public TwistFate() {
		super();
		setPassive(true);
		setName("Twist Fate");
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Queue.add(new Revive(), target, target, Condition.DIE);
	}
}
