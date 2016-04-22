package roles.ranger;

import queue.Queue;
import rules.Unstealth;
import ui.LogDisplay;
import ability.Ability;
import character.Character;
import encounter.Encounter;
import enums.Condition;

public class Ambush extends Ability {
	
	private final int duration = 5;
	
	public Ambush() {
		setName("Vanish");
		setPassive(true);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		source.attributes.stealth();
		Unstealth unstealth = new Unstealth();
		Queue.add(unstealth, source, target, Condition.ATTACK);
		Queue.add(unstealth, source, target, Condition.TAKE_DAMAGE);
		Queue.add(unstealth, source, target, Condition.END_ENCOUNTER);
		Unstealth timedUnstealth = new Unstealth(duration);
		timedUnstealth.setPersistent(true);
		Queue.add(timedUnstealth, source, target, Condition.START_TURN);
		Encounter.get().updateGrid();
		LogDisplay.log(target.getName() + " vanishes!");
	}
}
