package encounter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import mapLogic.Entity;
import queue.Queue;
import ui.TitleDisplay;
import ui.UIStack;
import ui.grid.EncounterGrid;
import ui.grid.GridInterface;
import utilities.Logger;
import character.Character;
import character.Team;
import controller.Controller;
import enums.Relationship;
import enums.UIPosition;

public class Encounter {
	
	private static Encounter encounter;
	private GridInterface grid;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Team> finishedTeams = new ArrayList<Team>();
	private EncounterEndResponder responder;
	
	public Encounter(GridInterface grid) {
		this.grid = grid;
		encounter = this;
	}
	
	public Encounter() {
		this.grid = new EncounterGrid();
		encounter = this;
	}
	
	public static Encounter get() {
		if(encounter == null)
			return new Encounter(new EncounterGrid());
		return encounter;
	}
	
	public void startEncounter() {
		Queue.clear();
		updateGrid();
		for(Character character: getCharacters())
			character.startEncounter();
		nextRound();
	}
	
	public void nextRound() {
		if(getTeams().size() <= 1) {
			if(getTeams().get(0).getController() == Controller.getPlayerController())
				victory();
			else
				failure();
			return;
		}
		Logger.print("Trying to start round.", -1);
		if(getUnfinishedTeam(getTeams()) == null) {
			Logger.print("Starting new set of rounds.");
			newRounds();
			return;
		}
		Team team = getUnfinishedTeam(getTeams());
		Logger.print("Starting round for team " + team.getName());
		Controller controller = team.getController();
		controller.setEncounter(this);
		finishedTeams.add(team);
		if(grid.isPlayerTurn())
			Logger.print("Not starting round; player turn.");
		else
			controller.startRound(team, grid);
	}
	
	private void newRounds() {
		finishedTeams = new ArrayList<Team>();
		nextRound();
	}
	
	public ArrayList<Team> getTeams() {
		ArrayList<Team> teams = new ArrayList<Team>();
		for(Character character: getCharacters())
			if(!teams.contains(character.getTeam()))
				teams.add(character.getTeam());
		return sortTeams(teams);
	}
	
	public void add(Entity entity) {
		if(!entities.contains(entity))
			entities.add(entity);
		updateGrid();
	}
	
	private ArrayList<Team> sortTeams(ArrayList<Team> initialTeams) {
		ArrayList<Team> teams = initialTeams;
		Collections.sort(teams, new Comparator<Team>() {
		    @Override
		    public int compare(Team team1, Team team2) {
		        if (getCharacterCount(team1) > getCharacterCount(team2))
		            return 1;
		        if (getCharacterCount(team1) < getCharacterCount(team2))
		            return -1;
		        return 0;
		    }
		});
		return teams;
	}
	
	private int getCharacterCount(Team team) {
		int count = 0;
		for(Character character: getCharacters())
			if(character.getTeam() == team)
				count ++;
		return count;
	}
	
	private Team getUnfinishedTeam(ArrayList<Team> teams) {
		for(Team team: teams)
			if(!finishedTeams.contains(team))
				return team;
		return null;
	}
	
	public ArrayList<Character> getCharacters() {
		ArrayList<Character> characters = new ArrayList<Character>();
		for(Entity entity: entities)
			if(entity instanceof Character)
				characters.add((Character) entity);
		return characters;
	}
	
	public void endMove(Character character) {
		
	}

	public void endTurn(Character character) {
		character.tryToEndTurn(grid);
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
		if(entity instanceof Character) {
			Character character = (Character) entity;
			character.getTeam().getController().skipTurn(character);
		}
		updateGrid();
	}

	public void updateGrid() {
		grid.setEntities(getVisibleEntities());
		grid.repaint();
	}
	
	private ArrayList<Entity> getVisibleEntities() {
		ArrayList<Entity> unstealthed = new ArrayList<Entity>();
		for(Entity entity: entities)
			if(!(entity instanceof Character))
				unstealthed.add(entity);
		for(Character character: getCharacters())
			if(character.getController() == Controller.getPlayerController() || character.getTeam().getController() == Controller.getPlayerController() || !character.attributes.getStealthed())
				unstealthed.add(character);
		return unstealthed;
	}
	
	public GridInterface getGrid() {
		return grid;
	}
	
	private void victory() {
		if(responder != null)
			responder.victory(this);
		else {
			if(UIStack.getMainPanel() != null)
				UIStack.push(new TitleDisplay("Victory!", UIPosition.TOP_LEFT));
		}
	}
	
	private void failure() {
		if(responder != null)
			responder.failure(this);
		else {
			if(UIStack.getMainPanel() != null)
				UIStack.push(new TitleDisplay("Failure...", UIPosition.TOP_LEFT));
		}
	}
	
	public void setResponder(EncounterEndResponder responder) {
		this.responder = responder;
	}
	
	public void gainXP(Character deadCharacter) {
		Logger.print("Trying to gain XP");
		if(!getCharacters().contains(deadCharacter))
			return;
		int amount = deadCharacter.getXPValue();
		for(Character character: getCharacters()) {
			Logger.print("Testing " + character.getName());
			if(character.getRelationship(deadCharacter) == Relationship.ENEMY) {
				character.gainXP(amount);
				Logger.print(character.getName() + " gains " + amount + " XP from " + deadCharacter.getName() + "'s death.");
			}
		}
	}
}