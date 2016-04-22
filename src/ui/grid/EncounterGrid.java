package ui.grid;

import java.util.ArrayList;

import mapLogic.Entity;
import ui.CharacterInterface;
import ui.HealthBar;
import ui.Menu;
import ui.UIStack;
import utilities.Logger;
import utilities.Position;
import character.Character;
import enums.Direction;
import enums.UIPosition;

public class EncounterGrid extends Grid implements GridInterface {
	
	private GridMode mode;
	private ArrayList<HealthBar> healthBars = new ArrayList<HealthBar>();
	
	public EncounterGrid() {
		super();
		setMode(new NoResponseMode());
		healthBars = new ArrayList<HealthBar>();
	}
	
	public EncounterGrid(int w, int h) {
		super(w, h);
		setMode(new NoResponseMode());
		healthBars = new ArrayList<HealthBar>();
	}
	
	public void inputDirection(Direction direction) {
		//Logger.print("Moving cursor");
		mode.inputDirection(direction);
		repaint();
	}
	
	public void inputAccept() {
		mode.inputAccept();
	}
	
	public void inputCancel() {
		mode.inputCancel();
	}
	
	@Override
	public void repaint() {
		super.repaint();
		drawHealthBars();
	}
	
	public CharacterInterface getCharacterAtPosition(Position position) {
		for(Entity entity: entities)
			if(entity instanceof CharacterInterface && entity.getPosition().equalsPosition(position))
				return (CharacterInterface) entity;
		return null;
	}
	
	public void setMode(GridMode mode) {
		if(this.mode != null) {
			Entity modeEntity = this.mode.getModeEntity();
			if(modeEntity != null)
				remove(modeEntity);
		}
		Logger.print("Mode: " + mode, -1);
		this.mode = mode;
		Entity modeEntity = mode.getModeEntity();
		Logger.print("Entities in " + this + ": " + entities, -1);
		if(modeEntity != null)
			add(modeEntity);
		Logger.print("Cursor: " + modeEntity, -1);
		Logger.print("Entities in " + this + ": " + entities, -1);
		revalidate();
		repaint();
	}

	public void playerTurn() {
		setMode(new CursorMode(this));
	}

	public void aiTurn() {
		setMode(new NoResponseMode());
		popToThis();
	}
	
	public void popToThis() {
		if(UIStack.getSize() == 0)
			return;
		while(UIStack.getResponder() != this && UIStack.contains(this) && UIStack.getResponder() != null) {
			Logger.print("Popping responder: " + UIStack.getTop() + " down to EncounterGrid.", -1);
			UIStack.popResponder();
		}
	}
	
	public boolean isPlayerTurn() {
		if(mode instanceof NoResponseMode) {
			//Logger.print("Mode is NoResponse");
			return false;
		}
		return true;
	}
	
	public void combatMenu(CharacterInterface character) {
		setMode(new NoResponseMode());
		Menu combatMenu = new Menu(UIPosition.RIGHT);
		combatMenu.setResponder(new CombatMenuResponder(character, this));
		ArrayList<String> combatItems = new ArrayList<String>();
		//combatItems.add("Attack");
		//combatItems.add("Item");
		combatItems.addAll(character.getAbilityCategoryNames());
		combatItems.add("End Turn");
		combatMenu.setItems(combatItems);
		UIStack.push(combatMenu);
	}
	
	public void abilityMenu(CharacterInterface character, String role) {
		ArrayList<String> abilityNames = character.getAbilityNames(role);
		if(abilityNames.isEmpty())
			return;
		setMode(new NoResponseMode());
		Menu combatMenu = new Menu(UIPosition.LEFT);
		combatMenu.setResponder(new AbilityResponder(character, this));
		ArrayList<String> combatItems = new ArrayList<String>();
		for(String string: abilityNames) {
			combatItems.add(string);
		}
		combatMenu.setItems(combatItems);
		UIStack.push(combatMenu);
	}
	
	public void tellCharToStartTurn(CharacterInterface character) {
		character.startTurn(this);
	}
	
	public void startTurn(CharacterInterface character) {
		setMode(new MoveMode(character, this));
	}
	
	private void drawHealthBars() {
		ArrayList<CharacterInterface> healthBarCharacters = healthBarCharacters();
		if(healthBarCharacters == null)
			return;
		ArrayList<HealthBar> toRemove = new ArrayList<HealthBar>();
		for(HealthBar healthBar: healthBars)
			if(!entities.contains(healthBar.getCharacter())) {
				remove(healthBar);
				toRemove.add(healthBar);
			}
		for(HealthBar bar: toRemove)
			healthBars.remove(bar);
		for(CharacterInterface character: getCharacters())
			if(!healthBarCharacters.contains(character)) {
				HealthBar healthBar = new HealthBar((Character) character);
				add(healthBar);
				healthBars.add(healthBar);
			}
	}
	
	private ArrayList<CharacterInterface> healthBarCharacters() {
		ArrayList<CharacterInterface> characters = new ArrayList<CharacterInterface>();
		if(healthBars == null)
			return null;
		for(HealthBar healthBar: healthBars)
			if(getCharacters().contains(healthBar.getCharacter()))
				characters.add(healthBar.getCharacter());
		return characters;
	}
	
	public void waitMilliseconds(int milliseconds) {
		int seconds = milliseconds / 1000;
		try {
			wait(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
