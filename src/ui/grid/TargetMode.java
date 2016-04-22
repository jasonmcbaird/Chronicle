package ui.grid;

import javax.imageio.ImageIO;

import mapLogic.Entity;
import mapLogic.GridLogic;
import ui.CharacterDisplay;
import ui.CharacterInterface;
import ui.Cursor;
import ui.UIStack;
import utilities.Logger;
import utilities.Position;
import enums.Direction;

public class TargetMode implements GridMode {
	
	private Cursor cursor;
	private EncounterGrid grid;
	private CharacterInterface source;
	private String ability;
	
	public TargetMode(EncounterGrid grid, CharacterInterface source, String ability) {
		cursor = new Cursor();
		cursor.setX(source.getX());
		cursor.setY(source.getY());
		try {
			cursor.setImage(ImageIO.read(this.getClass().getResource("/res/weapons/redSword.png")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.grid = grid;
		this.source = source;
		this.ability = ability;
	}

	public void inputDirection(Direction direction) {
		UIStack.popCharacterDisplay();
		if(checkRange(direction))
			cursor.move(direction, 1);
		CharacterInterface character = grid.getCharacterAtPosition(cursor.getPosition());
		if(character != null)
			characterDisplay(character);
	}

	public void inputAccept() {
		UIStack.popCharacterDisplay();
		CharacterInterface target = grid.getCharacterAtPosition(cursor.getPosition());
		if(target == null)
			return;
		if(canBeTarget(source, target))
			activateAbility(target);
	}
	
	public boolean canBeTarget(CharacterInterface source, CharacterInterface target) {
		if(source.isValidAbilityTarget(ability, target))
			return true;
		Logger.print("Not valid ability target");
		return false;
	}

	public void inputCancel() {
		UIStack.popCharacterDisplay();
		grid.combatMenu(source);
	}

	public Entity getModeEntity() {
		return cursor;
	}
	
	public void activateAbility(CharacterInterface target) {
		source.activateAbility(ability, target);
		if(!source.tryToEndTurn(grid))
			grid.combatMenu(source);
	}
	
	private boolean checkRange(Direction direction) {
		Position position = new Position(cursor.getX(), cursor.getY());
		position.move(direction);
		return GridLogic.getDistance((Entity) source, position) <= source.getRange(ability);
	}
	
	private void characterDisplay(CharacterInterface character) {
		CharacterDisplay display = new CharacterDisplay(character);
		UIStack.push(display);
	}
}
