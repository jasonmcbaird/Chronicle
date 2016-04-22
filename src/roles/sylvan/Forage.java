package roles.sylvan;

import queue.Queue;
import rules.ChangeEnergy;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.EnergyType;

public class Forage extends Ability {

	public Forage() {
		super();
		setName("Forage");
		setPassive(true);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		// TODO: Randomized results
		// TODO: Different results in different terrain
		Queue.addAndRun(new ChangeEnergy(EnergyType.HERBS, 3, 5), source, target, Condition.CHANGE_ENERGY);
	}
}
