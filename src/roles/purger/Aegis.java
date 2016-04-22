package roles.purger;

import ability.Ability;
import queue.Queue;
import rules.ClearEvent;
import rules.ExecuteOnEventType;
import rules.TakeDamage;
import ui.LogDisplay;
import utilities.Logger;
import character.Character;
import enums.Condition;

public class Aegis extends Ability {
	
	public Aegis() {
		super();
		setName("Aegis");
		setPassive(true);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		ExecuteOnEventType clearDamage = new ExecuteOnEventType(TakeDamage.class, new ClearEvent());
		Queue.add(clearDamage, source, target, Condition.TAKE_DAMAGE);
		Logger.print(target.getName() + " is protected by an Aegis.", 1);
		LogDisplay.log(target.getName() + " is protected by an Aegis.");
	}
}
