package roles.psion;

import ability.Ability;
import queue.Queue;
import rules.Cooldown;
import rules.ExecuteOnEventType;
import rules.ModifyCooldown;
import rules.NullifyNext;
import character.Character;
import enums.Condition;

//Ability:  Focus
//Level:    5
//Passive:  True
//
//Rules:    1. At the end of each of your turns, if you didn't attack, reduce the cooldowns on each of your abilities by one.

public class Focus extends Ability {
	
	public Focus() {
		super();
		setName("Focus");
		setPassive(true);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		ExecuteOnEventType modify = new ExecuteOnEventType(Cooldown.class, new ModifyCooldown(-1), Condition.END_TURN);
		modify.setPersistent(true);
		Queue.add(modify, source, target, Condition.END_TURN);
		NullifyNext nullify = new NullifyNext(modify);
		nullify.setPersistent(true);
		Queue.add(nullify, source, target, Condition.ATTACK);
		NullifyNext nullify2 = new NullifyNext(modify);
		Queue.addAndRun(nullify2, source, target, Condition.NOW);
	}
}
