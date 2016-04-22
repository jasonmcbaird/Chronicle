package roles.assassin;

import mapLogic.GridLogic;
import queue.Queue;
import rules.Move;
import rules.Attack;
import ui.LogDisplay;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.Direction;
import enums.EnergyType;

//Ability:  Rush
//Level:    2
//Passive:  False
//Involves: TAKE_DAMAGE, ATTACK, MOVE
//
//Rules:    1. Move one square towards the target.
//			2. Make a physical attack based on Power.

public class Rush extends Ability {
	
	private final int cost = 1;
	
	public Rush() {
		setName("Rush");
	}
	
	public int getRange(Character character) {
		return character.inventory.getMainRange() + 1;
	}
	
	public boolean getCanBeActivated(Character character) {
		if(!super.getCanBeActivated(character))
			return false;
		// TODO: This code gets repeated.
		if(canBeActivated) {
			if(character.getEnergy(EnergyType.PATIENCE) < cost) {
				return false;
			}
		}
		return true;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		if(!payEnergy(source, EnergyType.PATIENCE, cost))
			return;
		Direction direction = GridLogic.getDirection(source, target);
		Move move = new Move(direction, 1);
		Queue.addAndRun(move, source, source, Condition.NOW);
		if(move.succeeded()) {
			LogDisplay.log(source.getName() + " dashes toward " + target.getName());
		}
		if(GridLogic.getDistance(source, target) <= source.inventory.getMainRange())
			Queue.addAndRun(new Attack(source.inventory.getMainDamageBase(), source.inventory.getMainDamageType(), source.inventory.getMainStat()), source, target, Condition.ATTACK);
	}
}
