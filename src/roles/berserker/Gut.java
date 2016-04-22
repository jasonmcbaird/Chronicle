package roles.berserker;

import queue.Queue;
import rules.Attack;
import rules.Bleed;
import rules.ClearEvent;
import rules.WeaponAttack;
import ui.LogDisplay;
import ability.Ability;
import character.Character;
import enums.Condition;

//Ability:  Gut
//Level:    2
//Passive:  False
//Involves: TAKE_DAMAGE, HEAL, START_TURN
//
//Rules:    1. Deal physical damage at melee range using your Power.
//			2. When you hit an enemy, they begin to bleed.
//			3. At the start of each turn, if a character is bleeding, they lose 2 health.
//			4. The next time a bleeding character is healed, they stop bleeding.

public class Gut extends Ability {
	
	final private int bleedAmount = 2;

	public Gut() {
		super();
		setName("Gut");
	}
	
	public int getRange(Character character) {
		return character.inventory.getMainRange();
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Attack attack = new WeaponAttack(source, -2);
		Queue.addAndRun(attack, source, target, Condition.ATTACK);
		if(attack.wasHit()) {
			Bleed bleed = new Bleed(bleedAmount);
			Queue.add(bleed, source, target, Condition.START_TURN);
			Queue.add(new ClearEvent(bleed), source, target, Condition.HEAL);
			LogDisplay.log(target.getName() + " begins to bleed.");
		}
	}
}
