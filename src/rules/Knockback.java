package rules;

import mapLogic.GridLogic;
import queue.Queue;
import utilities.Logger;
import character.Character;
import enums.Condition;
import enums.Direction;

public class Knockback extends Rule {
	
	private int amount;
	
	public Knockback(int amount) {
		this.amount = amount;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Logger.print(source.getName() + " knocks " + target.getName() + " back!");
		Direction direction = GridLogic.getDirection(source, target);
		Queue.addAndRun(new Move(direction, amount), source, target, Condition.MOVE);
	}
}
