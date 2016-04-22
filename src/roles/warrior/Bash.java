package roles.warrior;

import queue.Queue;
import rules.Attack;
import rules.ClearEvent;
import rules.ExecuteOnEventType;
import rules.WeaponAttack;
import ui.LogDisplay;
import utilities.Dice;
import utilities.Logger;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.DamageType;
import enums.EnergyType;

public class Bash extends Ability {
	
//Ability:  Bash
//Level:    Start
//Passive:  False
//Involves: TAKE_DAMAGE, ACTIVATE_ABILITY
//
//Rules:    	1. Deal damage.
//				2. Chance of stopping the target from acting on their next turn.
	
	private final int stunChanceDie = 6;
	private final int stunChanceDC = 3;
	private final int cost = 2;

	public Bash() {
		super();
		setName("Bash");
		setBaseDamage(8);
	}
	
	public boolean getCanBeActivated(Character character) {
		if(!super.getCanBeActivated(character))
			return false;
		if(character.inventory.getMainRange() > 1)
			return false;
		return true;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		if(!payEnergy(source, EnergyType.STAMINA, cost))
			return;
		Attack attack = new WeaponAttack(source);
		if(source.inventory.getMainDamageType() == DamageType.SMASH)
			attack = new WeaponAttack(source, DamageType.SMASH);
		else
			attack = new WeaponAttack(source, -2, DamageType.SMASH);
		Queue.addAndRun(attack, source, target, Condition.ATTACK);
		if(attack.wasHit()) {
			if(Dice.rollAgainstDC(stunChanceDie, 0, stunChanceDC)) {
				ExecuteOnEventType execute = new ExecuteOnEventType(Ability.class, new ClearEvent(), Condition.ACTIVATE_ABILITY);
				execute.setPersistent(true);
				Queue.add(execute, source, target, Condition.ACTIVATE_ABILITY);
				Logger.print(target.getName() + " becomes stunned!", 1);
				LogDisplay.log(target.getName() + " becomes stunned!");
				ClearEvent clearEvent = new ClearEvent(execute);
				Queue.add(clearEvent, source, source, Condition.END_TURN);
			}
		}
	}
}
