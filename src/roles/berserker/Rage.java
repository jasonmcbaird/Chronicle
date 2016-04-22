package roles.berserker;

import ability.Ability;
import queue.Queue;
import rules.Berserk;
import rules.ChangeEnergy;
import character.Character;
import enums.Condition;
import enums.EnergyType;

// Ability:  Rage
// Level:    1
// Passive:  True
// Involves: ATTACK, GAIN_ENERGY, END_TURN, END_ENCOUNTER
//
// Rules:   1. Whenever you use an ability on an enemy, gain a random amount of rage from 2-4.
//			2. When your rage bar fills, go berserk.
//			3. When your rage bar empties, stop being berserk. Note: your health will drop. That drop could kill you.
//			4. While berserk, your strength is increased by (4). This increases your health temporarily, too.
//			5. While berserk, the AI controls you.
//			6. While berserk, you cannot gain rage.
//			7. At end of turn, if berserk, lose 2 rage at the end of every turn if you attacked that turn or lose 4 rage if you didn't.

public class Rage extends Ability {
	
	public Rage() {
		super();
		setName("Rage");
		setPassive(true);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		ChangeEnergy gain = new ChangeEnergy(EnergyType.RAGE, 2, 4);
		gain.setPersistent(true);
		Queue.add(gain, target, target, Condition.ATTACK);
		Queue.add(new Berserk(), target, target, Condition.CHANGE_ENERGY);
	}
}
