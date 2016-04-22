package rules;

import queue.Queue;
import utilities.Logger;
import character.Character;
import enums.Condition;

public class Pin extends Rule {
	
	private int percent;
	
	public Pin(int percent) {
		this.percent = percent;
	}
		
	public void subExecute(Character source, Character target, Condition condition) {
		int priorMoveSpeed = target.attributes.getMoveSpeed();
		int difference = Math.round(priorMoveSpeed * (percent) / 100);
		Logger.print("Reducing " + target.getName() + "'s move speed by " + difference);
		target.attributes.changeMoveSpeed(-difference);
		UnPin un = new UnPin(difference);
		Queue.add(un, source, target, Condition.END_TURN);
		Queue.add(un, source, target, Condition.END_ENCOUNTER);
	}
}
