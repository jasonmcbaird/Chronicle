package roles.ranger;

import queue.Queue;
import rules.Attack;
import rules.WeaponAttack;
import utilities.Logger;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.Stat;

public class Snipe extends Ability {
	
	public Snipe() {
		super();
		setName("Snipe");
	}
	
	public boolean getCanBeActivated(Character character) {
		if(!super.getCanBeActivated(character))
			return false;
		if(character.inventory.getMainRange() <= 1) {
			Logger.print(character.getName() + " has " + character.inventory.getMainRange() + " range with their main weapon.");
			return false;
		}
		return true;
	}
	
	public int getRange(Character character) {
		return character.inventory.getMainRange() + 2;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		source.attributes.changeStat(Stat.CRIT, 5);
		Attack attack = new WeaponAttack(source, +1);
		Queue.addAndRun(attack, source, target, Condition.ATTACK);
		source.attributes.changeStat(Stat.CRIT, -5);
	}
}
