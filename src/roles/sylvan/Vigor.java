package roles.sylvan;

import ability.Ability;
import queue.Queue;
import rules.RegenerateHealth;
import character.Character;
import enums.Condition;

public class Vigor extends Ability {

	public Vigor() {
		super();
		setName("Vigor");
		setPassive(true);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Queue.add(new RegenerateHealth(2), source, target, Condition.END_TURN);
	}
}
