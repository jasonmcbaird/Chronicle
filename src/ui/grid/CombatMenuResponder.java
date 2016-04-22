package ui.grid;

import ui.CharacterInterface;
import ui.MenuResponder;
import ui.UIStack;
import utilities.Logger;


public class CombatMenuResponder implements MenuResponder {
	
	private CharacterInterface character;
	private EncounterGrid grid;
	
	public CombatMenuResponder(CharacterInterface character, EncounterGrid grid) {
		this.character = character;
		this.grid = grid;
	}
	
	public void menuItemSelected(String string) {
		Logger.print("Selected item " + string + " from roleMenu.");
		if(string == "Attack") {
			attackTargetMode();
			return;
		}
		if(string == "Item") {
			endTurn();
			return;
		}
		if(string == "End Turn") {
			endTurn();
			return;
		}
		grid.abilityMenu(character, string);
	}
	
	private void attackTargetMode() {
		grid.setMode(new TargetMode(grid, character, "Attack"));
		UIStack.pop();
	}
	
	private void endTurn() {
		character.endTurn(grid);
		grid.popToThis();
	}
	
	public void inputCancel() {
		
	}
	
}
