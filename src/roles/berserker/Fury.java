package roles.berserker;

import ability.Ability;
import queue.Queue;
import rules.ChangeEnergy;
import character.Character;
import enums.Condition;
import enums.EnergyType;

// Ability:  Fury
// Level:    5
// Passive:  True
// Involves: TAKE_DAMAGE, GAIN_ENERGY
//
// Rules:    1. When damage is taken increase rage energy by 2

public class Fury extends Ability {

	public Fury() {
		super();
		setName("Fury");
		setPassive(true);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		ChangeEnergy gain = new ChangeEnergy(EnergyType.RAGE, 2);
		gain.setPersistent(true);
		Queue.add(gain, source, target, Condition.TAKE_DAMAGE);
	}
}
