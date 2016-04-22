package rules;

import ui.grid.GridInterface;
import utilities.Logger;
import character.Character;
import enums.Condition;

public class EndTurn extends Rule {
	
	private GridInterface grid;
	private boolean succeeded = false;
	
	public EndTurn(GridInterface grid) {
		this.grid = grid;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Logger.print("Running endTurn event on " + target.getName(), -1);
		target.endTurn(grid);
		succeeded = true;
	}
	
	public boolean successful() {
		return succeeded;
	}
}
