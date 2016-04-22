package roles.berserker;

import queue.Queue;
import rules.Attack;
import rules.ChangeEnergy;
import rules.WeaponAttack;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.EnergyType;

public class Wrath extends Ability {

	public Wrath() {
		super();
		setName("Wrath");
	}
	
	public int getRange(Character character) {
		return character.inventory.getMainRange();
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		boolean highRage = source.getEnergy(EnergyType.RAGE) >= 5;
		Attack attack = new WeaponAttack(source, -2);
		if(highRage)
			attack = new WeaponAttack(source, 2);
		Queue.addAndRun(attack, source, target, Condition.ATTACK);
		if(attack.wasHit() && !highRage)
			Queue.addAndRun(new ChangeEnergy(EnergyType.RAGE, 2), source, source, Condition.CHANGE_ENERGY);
	}
}
