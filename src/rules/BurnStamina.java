package rules;

import queue.Queue;
import character.Character;
import enums.Condition;
import enums.Stat;

public class BurnStamina extends Rule {
	
	int amount;
	
	public BurnStamina(int amount) {
		this.amount = amount;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		for(Stat stat: Stat.getMajorStats())
			Queue.addAndRun(new StatBuff(stat, -amount, Condition.END_ENCOUNTER), source, target, Condition.NOW);
	}

}
