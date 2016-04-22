package controller;

import queue.Queue;
import ui.grid.GridInterface;
import utilities.Logger;
import character.Character;
import character.Team;
import enums.Condition;

public class PlayerController extends Controller {
	
	public void startRound(Team team, GridInterface grid) {
		teamCharacters = getTeamCharacters(team);
		Logger.print("Team characters: " + teamCharacters);
		for(Character character: teamCharacters)
			Queue.run(Condition.START_TURN, character);
		grid.playerTurn();
	}
	
	public void startTurn(Character character, GridInterface grid) {
		character.getTeam().getController().skipTurn(character);
		grid.startTurn(character);
	}
	
	public boolean isReady(Character character) {
		if(teamCharacters.contains(character) && !hasActed.contains(character))
			return true;
		return false;
	}
	
	public void endTurn(Character character, GridInterface grid) {
		Logger.print("Ended player turn");
		if(hasActed.containsAll(teamCharacters)) {
			grid.aiTurn();
			endRound();
		} else
			grid.playerTurn();
	}
}
