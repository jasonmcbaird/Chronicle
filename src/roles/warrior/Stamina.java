package roles.warrior;

import queue.Queue;
import rules.ChangeEnergy;
import rules.ExecuteIfLoseEnergy;
import rules.NullifyNext;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.EnergyType;

public class Stamina extends Ability {
	
	public Stamina() {
		setName("Stamina");
		setPassive(true);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		ChangeEnergy regen = new ChangeEnergy(EnergyType.STAMINA, 2);
		regen.setPersistent(true);
		Queue.add(regen, source, target, Condition.END_TURN);
		
		NullifyNext nullify = new NullifyNext(regen);
		ExecuteIfLoseEnergy execute = new ExecuteIfLoseEnergy(nullify, EnergyType.STAMINA);
		execute.setPersistent(true);
		Queue.add(execute, source, target, Condition.CHANGE_ENERGY);
	}
}
