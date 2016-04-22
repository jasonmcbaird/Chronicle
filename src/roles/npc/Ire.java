package roles.npc;

import queue.Queue;
import rules.StatBuff;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.Stat;

public class Ire extends Ability {

	public Ire() {
		super();
		setName("Ire");
		setPassive(true);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		StatBuff buff = new StatBuff(Stat.STRENGTH, 1);
		buff.setPersistent(true);
		Queue.add(buff, source, target, Condition.TAKE_DAMAGE);
	}
}
