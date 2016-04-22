package rules;

import mapLogic.GridLogic;
import queue.Queue;
import utilities.Logger;
import character.Character;
import enums.Condition;
import enums.Direction;

public class Fear extends Rule {
	
	private int amount;
	
	public Fear(int amount) {
		this.amount = amount;
	}
	
	public void setUntilTurnEnd(Character source, Character target) {
		setPersistent(true);
		Queue.add(new ClearEvent(this), source, target, Condition.END_TURN);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		ExecuteOnEventType executeSucceeded = new ExecuteOnEventType(Move.class, new ForceSucceeded(), Condition.MOVE);
		executeSucceeded.selfRestriction(true);
		Queue.add(executeSucceeded, source, target, Condition.NOW);
		ExecuteOnEventType execute = new ExecuteOnEventType(Move.class, new ClearEvent(), Condition.MOVE);
		execute.selfRestriction(true);
		Queue.addAndRun(execute, source, target, Condition.NOW);
		if(execute.didFireThisRun()) {
			Logger.print(target.getName() + " flees from " + source.getName() + "!");
			Direction direction = GridLogic.getDirection(source, target);
			Queue.addAndRun(new Move(direction, amount), source, target, Condition.MOVE);
		}
	}
}