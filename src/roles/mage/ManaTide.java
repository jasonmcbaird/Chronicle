package roles.mage;

import ability.Ability;
import queue.Queue;
import rules.ChangeEnergy;
import character.Character;
import enums.Condition;
import enums.EnergyType;

//Ability:  Mana Tide
//Level:    7
//Passive:  True
//Involves: START_TURN, GAIN_ENERGY
//
//Rules:    1. At the beginning of your turn, regenerate mana based on the turns that have passed since you joined the encounter.
//				Regenerate 1/2 of the turn count, rounded down. Regeneration caps at 3 mana per turn.

public class ManaTide extends Ability {
	
	public ManaTide() {
		setPassive(true);
		setName("Mana Tide");
	}

	public void subExecute(Character source, Character target, Condition condition) {
		ChangeEnergy gain = new ChangeEnergy(EnergyType.MANA, 0);
		gain.setIncrease(1);
		gain.setCap(3);
		gain.setPersistent(true);
		Queue.add(gain, source, target, Condition.END_TURN);
	}
}
