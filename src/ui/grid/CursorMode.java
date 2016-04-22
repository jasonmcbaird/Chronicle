package ui.grid;

import mapLogic.Entity;
import ui.CharacterDisplay;
import ui.CharacterInterface;
import ui.Cursor;
import ui.UIStack;
import utilities.Position;
import enums.Direction;

public class CursorMode implements GridMode {
	
	private Cursor cursor;
	private EncounterGrid grid;
	
	public CursorMode(EncounterGrid grid) {
		cursor = new Cursor();
		cursor.setX(8);
		cursor.setY(8);
		this.grid = grid;
	}
	
	public CursorMode(EncounterGrid grid, Position position) {
		cursor = new Cursor();
		cursor.setX(position.getX());
		cursor.setY(position.getY());
		this.grid = grid;
	}

	public void inputDirection(Direction direction) {
		UIStack.popCharacterDisplay();
		cursor.move(direction, 1);
		CharacterInterface character = grid.getCharacterAtPosition(cursor.getPosition());
		if(character != null)
			characterDisplay(character);
	}

	public void inputAccept() {
		selectCharacter(cursor.getPosition());
		UIStack.popCharacterDisplay();
	}
	
	public void selectCharacter(Position position) {
		CharacterInterface character = grid.getCharacterAtPosition(position);
		if(character != null && character.isReady() && character.isPlayer())
			grid.tellCharToStartTurn(character);
	}

	public void inputCancel() {
		UIStack.popCharacterDisplay();
	}
	
	public Entity getModeEntity() {
		return cursor;
	}
	
	private void characterDisplay(CharacterInterface character) {
		CharacterDisplay display = new CharacterDisplay(character);
		UIStack.push(display);
	}
}
