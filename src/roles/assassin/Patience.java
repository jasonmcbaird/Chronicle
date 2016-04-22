package roles.assassin;

import ability.Ability;
import queue.Queue;
import rules.ChangeEnergy;
import rules.NullifyNext;
import character.Character;
import enums.Condition;
import enums.EnergyType;

//Ability:  Patience
//Level:    0
//Passive:  True
//
//Rules:    1. At the end of your turn, if you didn't attack, you gain 1 patience.
//			2. Whenever you attack, you lose all your patience.
//			3. Spend patience like any other energy source.
//			4. Keep in mind: abilities that are also attacks will cost patience and then reset your patience back to 0.

public class Patience extends Ability {
	
	public Patience() {
		setName("Patience");
		setPassive(true);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		ChangeEnergy gain = new ChangeEnergy(EnergyType.PATIENCE, 1);
		gain.setPersistent(true);
		Queue.add(gain, source, target, Condition.END_TURN);
		NullifyNext nullify = new NullifyNext(gain);
		nullify.setPersistent(true);
		Queue.add(nullify, source, target, Condition.ATTACK);
		ChangeEnergy clear = new ChangeEnergy(EnergyType.PATIENCE, true);
		clear.setPersistent(true);
		Queue.add(clear, source, target, Condition.ATTACK);
	}

}
