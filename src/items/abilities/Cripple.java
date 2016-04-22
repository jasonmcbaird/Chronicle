package items.abilities;

import queue.Queue;
import rules.Attack;
import rules.Pin;
import rules.WeaponAttack;
import ui.LogDisplay;
import utilities.Logger;
import ability.Ability;
import character.Character;
import enums.Condition;

public class Cripple extends Ability {
	
	public Cripple() {
		setName("Cripple");
	}
	
	public int getRange(Character character) {
		return character.inventory.getMainRange();
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Attack attack = new WeaponAttack(source, -3);
		Queue.addAndRun(attack, source, target, Condition.ATTACK);
		if(attack.wasHit()) {
			Queue.addAndRun(new Pin(50), source, target, Condition.NOW);
			Logger.print(target.getName() + " is crippled!", 1);
			LogDisplay.log(target.getName() + " is crippled!");
		}
	}
}
