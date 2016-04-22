package roles.mage;

import java.util.ArrayList;

import ability.Ability;
import queue.Event;
import queue.Queue;
import rules.Attack;
import rules.ClearEvent;
import rules.ExecuteOnEventType;
import rules.Move;
import ui.LogDisplay;
import utilities.Dice;
import utilities.Logger;
import character.Character;
import enums.Condition;
import enums.DamageType;
import enums.EnergyType;
import enums.Stat;

//Ability:  Frostbolt
//Level:    4
//Passive:  False
//Involves: TAKE_DAMAGE, LOSE_ENERGY, ACTIVATE_ABILITY, MOVE
//
//Rules:    1. Pay 4 mana. If you can't, the spell fizzles.
//			2. Deal elemental damage based on your Magic.
//			3. If the attack hits, you have a chance to freeze the target.
//			4. While a character is frozen, they cannot move.
//			5. Whenever a frozen character would activate an ability, they roll to thaw out instead.
//			6. Whenever a frozen character takes damage, they roll to thaw out.

public class Frostbolt extends Ability {
	
	final private int freezeChanceDie = 6;
	final private int freezeChanceDC = 4;
	final private int manaCost = 4;
	
	public Frostbolt() {
		super();
		setName("Frostbolt");
		setRange(2);
		setDamageType(DamageType.FROST);
		setBaseDamage(10);
		setAttackStat(Stat.MAGIC);
	}
	
	public boolean getCanBeActivated(Character character) {
		if(!super.getCanBeActivated(character))
			return false;
		if(canBeActivated) {
			if(character.getEnergy(EnergyType.MANA) < manaCost) {
				return false;
			}
		}
		return true;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		if(!payEnergy(source, EnergyType.MANA, manaCost)) {
			return;
		}
		Attack attack = new Attack(getDamageBase(), getDamageType(), getAttackStat());
		Queue.addAndRun(attack, source, target, Condition.ATTACK);
		if(attack.wasHit()) {
			freezeChance(target);
		}
	}
	
	private boolean freezeChance(Character target) {
		if(Dice.rollAgainstDC(freezeChanceDie, 0, freezeChanceDC)) {
			LogDisplay.log(target.getName() + " is frozen!");
			Logger.print(target.getName() + " is frozen!", 1);
			ExecuteOnEventType preventAbility = new ExecuteOnEventType(Ability.class, new ClearEvent());
			preventAbility.setPersistent(true);
			
			ExecuteOnEventType preventMove = new ExecuteOnEventType(Move.class, new ClearEvent());
			preventMove.setPersistent(true);
			
			
			Queue.add(preventAbility, target, target, Condition.ACTIVATE_ABILITY);
			Queue.add(preventMove, target, target, Condition.MOVE);
			
			ArrayList<Event> eventsToClear = new ArrayList<Event>();
			eventsToClear.add(preventAbility);
			eventsToClear.add(preventMove);
			
			Event clearEvent = new ClearEvent(eventsToClear, 75);
			Queue.add(clearEvent, target, target, Condition.ACTIVATE_ABILITY);
			Queue.add(clearEvent, target, target, Condition.TAKE_DAMAGE);
			return true;
		}
		return false;
	}
}
