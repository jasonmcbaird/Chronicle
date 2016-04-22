package rules;

import queue.Queue;
import utilities.Logger;
import character.Character;
import enums.Condition;
import enums.Stat;

public class StatBuffClear extends Rule {
	
	private int amount;
	private Stat stat;
	private int duration;
	private int timer = 1;
	
	public StatBuffClear(Stat stat, int amount, int duration) {
		this.stat = stat;
		this.amount = amount;
		this.duration = duration;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		if(timer < duration) {
			timer++;
			Logger.print("Failing to clear stat buff. Duration: " + duration + ", timer: " + timer, -1);
			return;
		}
		Logger.print("Clearing stat buff.", -1);
		target.attributes.changeStat(stat, amount);
		if(target.attributes.getCurrentHealth() <= 0)
			Queue.addAndRun(new Die(), source, target, Condition.DIE);
		Queue.remove(this);
	}
	
}
