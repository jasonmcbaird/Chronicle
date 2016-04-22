package ui.grid;

import ui.CharacterInterface;
import ui.MenuResponder;
import ui.UIStack;
import utilities.Logger;


public class AbilityResponder implements MenuResponder {
	
	private CharacterInterface character;
	private EncounterGrid grid;
	
	public AbilityResponder(CharacterInterface character, EncounterGrid grid) {
		this.character = character;
		this.grid = grid;
	}
	
	public void menuItemSelected(String string) {
		Logger.print("Selected item " + string);
		grid.setMode(new TargetMode(grid, character, string));
		UIStack.pop();
		UIStack.pop();
	}
	
	public void inputCancel() {
		UIStack.pop();
	}
	
}
