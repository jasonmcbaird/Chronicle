package ability;

import queue.Queue;
import rules.Attack;
import rules.ChangeEnergy;
import character.Character;
import enums.Condition;
import enums.EnergyType;

public class MutableAbility extends Ability {
	
	public MutableAbility() {
		super();
		setName("Attack");
	}
	
	public int getRange(Character character) {
		return character.inventory.getMainRange();
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Queue.addAndRun(new Attack(source.inventory.getMainDamageBase(), source.inventory.getMainDamageType(), source.inventory.getMainStat()), source, target, Condition.ATTACK);
		Queue.addAndRun(new ChangeEnergy(EnergyType.RHYTHM, 1), source, source, Condition.CHANGE_ENERGY);
	}
	
}
