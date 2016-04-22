package roles.mage;

import ability.Ability;
import queue.Queue;
import rules.ChangeEnergy;
import character.Character;
import enums.Condition;
import enums.EnergyType;

//Ability:  ManaRegeneration
//Level:    1
//Passive:  True
//Involves: START_TURN, GAIN_ENERGY
//
//Rules:    1. At the beginning of each of your turns, gain (2) mana.

public class Innovation extends Ability {
	
	private final int regenAmount = 2;
	
	public Innovation() {
		super();
		setName("Innovation");
		setPassive(true);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		ChangeEnergy gain = new ChangeEnergy(EnergyType.MANA, regenAmount);
		gain.setPersistent(true);
		Queue.add(gain, source, target, Condition.END_TURN);
	}
}
