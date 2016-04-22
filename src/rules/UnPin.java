package rules;

import queue.Queue;
import character.Character;
import enums.Condition;

public class UnPin extends Rule {
	
	private int priorMoveSpeed;
	
	public UnPin(int priorMoveSpeed) {
		this.priorMoveSpeed = priorMoveSpeed;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		target.attributes.changeMoveSpeed(priorMoveSpeed);
		Queue.remove(this);
	}

}
