package roles.warrior;

import queue.Queue;
import rules.Attack;
import rules.Pin;
import rules.Knockback;
import rules.WeaponAttack;
import ui.LogDisplay;
import utilities.Logger;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.EnergyType;

public class Crash extends Ability {
	
//Ability:  Crash
//Level:    4
//Passive:  False
//Involves: TAKE_DAMAGE, MOVE
//
//Rules:    1. Deal damage.
//			2. Knock the target back a square.
//			3. Stop the target from moving out of that square during their next turn.
// TODO:	4. Only use this ability in Rush stance.
	
	private final int cost = 3;
	
	public Crash() {
		super();
		setName("Crash");
	}
	
	public int getRange(Character character) {
		return character.inventory.getMainRange();
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		if(!payEnergy(source, EnergyType.STAMINA, cost))
			return;
		Attack attack = new WeaponAttack(source, -1);
		Queue.addAndRun(attack, source, target, Condition.ATTACK);
		if(attack.wasHit()) {
			Queue.addAndRun(new Knockback(1), source, target, Condition.NOW);
			LogDisplay.log(source.getName() + " knocks " + target.getName() + " back.");
			Queue.addAndRun(new Pin(100), source, target, Condition.NOW);
			Logger.print(target.getName() + " becomes pinned!", 1);
			LogDisplay.log(target.getName() + " becomes pinned!");
		}
	}
}
