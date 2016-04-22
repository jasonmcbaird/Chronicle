package roles.psion;

import ability.Ability;
import queue.Queue;
import rules.StatBuff;
import character.Character;
import enums.Condition;
import enums.Stat;

//Ability:  Inspiration
//Level:    1
//Passive:  True
//
//Rules:    1. Whenever an ability's cooldown ends, increase the range of all your non-melee abilities by one until end of turn.

public class Inspiration extends Ability {
	
	public Inspiration() {
		setName("Inspiration");
		setPassive(true);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		StatBuff buff = new StatBuff(Stat.RANGE, 1, Condition.END_TURN);
		buff.setPersistent(true);
		Queue.add(buff, target, target, Condition.COOLDOWN_FINISH);
	}
}
