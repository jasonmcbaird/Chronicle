package rules;

import queue.Queue;
import ui.LogDisplay;
import utilities.Logger;
import character.Character;
import enums.Condition;

public class Revive extends Rule {
	
	public void subExecute(Character source, Character target, Condition condition) {
		Logger.print(source.getName() + " returns from the land of the dead!");
		LogDisplay.log(target.getName() + " returns from the land of the dead!");
		Queue.addAndRun(new ExecuteOnEventType(Die.class, new ClearEvent(), Condition.DIE), source, target, Condition.NOW);
		Queue.addAndRun(new Heal(target.attributes.getMaxHealth()/2), source, target, Condition.HEAL);
	}
	
}
