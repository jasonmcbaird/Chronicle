package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import mapLogic.GridLogic;
import mapLogic.pathfinders.AStarPathfinder;
import mapLogic.pathfinders.Path;
import mapLogic.pathfinders.Path.Step;
import queue.Queue;
import rules.EndTurn;
import rules.Move;
import ui.grid.GridInterface;
import utilities.Dice;
import utilities.Logger;
import ability.Ability;
import character.Character;
import character.Team;
import encounter.Encounter;
import enums.Condition;
import enums.Direction;

public class AIController extends Controller implements ActionListener {
	
	private AStarPathfinder pathfinder = new AStarPathfinder(GridLogic.get());
	private Timer timer = new Timer(1000, this);

	public void startTurn(Character character, GridInterface grid) {
		grid.aiTurn();
		character.getTeam().getController().skipTurn(character);
		Ability ability = getAbility(character);
		Logger.print("Ability: " + ability.getName(), -1);
		Character target =  GridLogic.findNearestTarget(character, ability.getRelationship());
		if(target == null) {
			Queue.addAndRun(new EndTurn(Encounter.get().getGrid()), character, character, Condition.NOW);
			return;
		}
		Logger.print("Target: " + target.getName(), -1);
		moveToTarget(character, target);
		if(checkActSucceeds(character, ability, target))
			useAbility(character, target, ability);
		else
			Queue.addAndRun(new EndTurn(Encounter.get().getGrid()), character, character, Condition.NOW);
		grid.repaint();
	}
	
	public void startRound(Team team, GridInterface grid) {
		teamCharacters = getTeamCharacters(team);
		Logger.print(teamCharacters + " is team characters");
		for(Character character: teamCharacters)
			Queue.run(Condition.START_TURN, character);
		nextTurn(grid);
	}
	
	public void nextTurn(GridInterface grid) {
		grid.aiTurn();
		Character character = selectCharacter(teamCharacters);
		if(character == null) {
			endRound();
			return;
		}
		character.getController().startTurn(character, grid);
		hasActed.add(character);
	}
	
	private void moveToTarget(Character character, Character target) {
		if(target == null)
			return;
		Path path = pathfinder.findPath(character, target);
		for(int i = 1; i <= character.getMoveSpeed(); i++) {
			tryToMove(character, path, i);
		}
	}
	
	private void tryToMove(Character character, Path path, int index) {
		if(index > path.getLength() - 1)
			return;
		Step step = path.getStep(index);
		int xChange = step.getX() - character.getX();
		int yChange = step.getY() - character.getY();
		move(character, xChange, yChange);
		Logger.print(character.getName() + " moving " + xChange + ", " + yChange + ".", -1);
	}
	
	private Boolean move(Character character, int xChange, int yChange) {
		if(GridLogic.checkMove(character, xChange, yChange)) {
			Move move = new Move(xChange, yChange);
			Queue.addAndRun(move, character, character, Condition.MOVE);
			if(move.succeeded())
				return true;
		}
		return false;
	}
	
	private Boolean move(Character character, Direction direction, int i) {
		if(GridLogic.checkMove(character, direction, i)) {
			Move move = new Move(direction, i);
			Queue.addAndRun(move, character, character, Condition.MOVE);
			if(move.succeeded())
				return true;
		}
		return false;
	}
	
	private Boolean move(Character character, Direction direction) {
		return move(character, direction, 1);
	}
	
	public boolean isReady(Character character) {
		//Logger.print(teamCharacters + "");
		//Logger.print(character + "");
		//Logger.print("Testing for AI readiness");
		if(teamCharacters.contains(character) && !hasActed.contains(character))
			return true;
		return false;
	}
	
	private Ability getAbility(Character character) {
		ArrayList<Ability> abilities = character.getActivatedAbilities();
		Random rng = new Random();
		int roll = rng.nextInt(abilities.size());
		return abilities.get(roll);
	}
	
	private boolean checkActSucceeds(Character source, Ability ability, Character target) {
		if(target == null)
			return false;
		if(GridLogic.getDistance(source, target) > ability.getRange(source))
			return false;
		return true;
	}
	
	private void useAbility(Character source, Character target, Ability ability) {
		Queue.addAndRun(ability, source, target, Condition.ACTIVATE_ABILITY);
		if(!source.tryToEndTurn(Encounter.get().getGrid()))
			Queue.addAndRun(new EndTurn(Encounter.get().getGrid()), source, source, Condition.NOW);
		// TODO: AI with Seconds will just end their turn like this after acting.
		// TODO: Make them use as many seconds as they can.
	}
	
	private Character selectCharacter(ArrayList<Character> teamCharacters) {
		ArrayList<Character> readyCharacters = new ArrayList<Character>();
		for(Character character: teamCharacters)
			if(isReady(character))
				readyCharacters.add(character);
		if(readyCharacters.size() <= 0)
			return null;
		int i = Dice.rollDie(readyCharacters.size()) - 1;
		return readyCharacters.get(i);
	}
	
	public void endTurn(Character character, GridInterface grid) {
		Logger.print("Ending AI turn");
		grid.repaint();
		sleep(2000);
	}
	
	public void sleep(int milli) {
		timer.setDelay(milli);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.stop();
		nextTurn(Encounter.get().getGrid());
	}
}
