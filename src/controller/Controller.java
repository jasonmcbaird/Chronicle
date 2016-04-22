package controller;

import java.util.ArrayList;

import ui.grid.GridInterface;
import character.Character;
import character.Team;
import encounter.Encounter;

public abstract class Controller {
	
	private static Controller playerController;
	private static Controller defaultAIController;
	protected ArrayList<Character> teamCharacters = new ArrayList<Character>();
	protected ArrayList<Character> hasActed = new ArrayList<Character>();
	protected Encounter encounter;
	
	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}
	
	public void startTurn(Character character, GridInterface grid) {
		throw new UnsupportedOperationException("Tried to tell a controller to start a turn, but the controller doesn't have a startTurn method.");
	}
	
	public void startRound(Team team, GridInterface grid) {
		throw new UnsupportedOperationException("Tried to tell a controller to start a round, but the controller doesn't have a startRound method.");
	}
	
	public static Controller getPlayerController() {
		if(playerController == null) {
			playerController = new PlayerController();
		}
		return playerController;
	}
	
	public static Controller getDefaultAIController() {
		if(defaultAIController == null) {
			defaultAIController = new AIController();
		}
		return defaultAIController;
	}
	
	public boolean isReady(Character character) {
		throw new UnsupportedOperationException("Tried to ask a controller if a character is ready, but the controller doesn't have an isReady method.");
	}
	
	protected ArrayList<Character> getTeamCharacters(Team team) {
		ArrayList<Character> characters = Encounter.get().getCharacters();
		ArrayList<Character> teamCharacters = new ArrayList<Character>();
		for(Character character: characters)
			if(character.getTeam() == team)
				teamCharacters.add(character);
		return teamCharacters;
	}
	
	public void endTurn(Character character, GridInterface grid) {
		throw new UnsupportedOperationException("Tried to ask a controller to end turn, but the controller doesn't have an endTurn method.");
	}
	
	protected void endRound() {
		hasActed = new ArrayList<Character>();
		encounter.nextRound();
	}
	
	public void extraTurn(Character character) {
		hasActed.remove(character);
	}
	
	public void skipTurn(Character character) {
		hasActed.add(character);
	}
	
	public boolean checkHasActed(Character character) {
		if(hasActed.contains(character))
			return true;
		return false;
	}

}
