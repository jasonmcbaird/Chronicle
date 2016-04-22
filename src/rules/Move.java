package rules;

import mapLogic.GridLogic;
import character.Character;
import enums.Condition;
import enums.Direction;

public class Move extends Rule implements CostsTime {
	
	private int xChange;
	private int yChange;
	private boolean succeeded = false;
	
	public Move(Direction direction, int amount) {
		switch (direction) {
		case LEFT:
			xChange = -amount;
			yChange = 0;
			return;
		case RIGHT:
			xChange = amount;
			yChange = 0;
			return;
		case UP:
			xChange = 0;
			yChange = -amount;
			return;
		case DOWN:
			xChange = 0;
			yChange = amount;
			return;
		}
	}
	
	public Move(int xChange, int yChange) {
		this.xChange = xChange;
		this.yChange = yChange;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		if(GridLogic.checkMove(target, xChange, yChange)) {
			target.move(xChange, yChange);
			succeeded = true;
		} else {
			succeeded = false;
		}
	}
	
	public int getSeconds(Character character) {
		if(Math.abs(xChange) + Math.abs(yChange) <= character.getMoveSpeed() / 2)
			return 1;
		return 2;
	}
	
	public boolean succeeded() {
		return succeeded;
	}
	
	public void overrideSucceeded() {
		succeeded = true;
	}
	
	public Rule getActionCost() {
		return new EndMove();
	}
	
}
