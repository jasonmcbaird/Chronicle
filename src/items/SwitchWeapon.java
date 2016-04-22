package items;

import queue.Queue;
import rules.ClearEvent;
import rules.EndTurn;
import rules.ExecuteOnEventType;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.Relationship;

public class SwitchWeapon extends Ability {
	
	public SwitchWeapon() {
		setName("Switch Weapon");
		setTargetRelationship(Relationship.SELF);
		setRange(0);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		target.inventory.switchWeapon();
		ExecuteOnEventType clearEndTurn = new ExecuteOnEventType(EndTurn.class, new ClearEvent());
		Queue.add(clearEndTurn, source, target, Condition.ACTIVATE_ABILITY);
	}

}
