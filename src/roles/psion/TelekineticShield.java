package roles.psion;

import java.util.ArrayList;

import ability.Ability;
import queue.Queue;
import rules.ClearEvent;
import rules.Cooldown;
import rules.ExecuteOnEventType;
import rules.ReduceDamage;
import rules.TakeDamage;
import ui.LogDisplay;
import character.Character;
import enums.Condition;
import enums.DamageType;
import enums.Relationship;

//Ability:  Telekinetic Shield
//Level:    5
//Passive:  False
//
//Rules:   1. While this ability is active, reduce the physical damage you take by 30%.
//			2. Four turns after you use this ability, turn it off.
//			2. When you use this ability, disable its future use.
//			3. Five turns after you use this ability, enable it again.

public class TelekineticShield extends Ability {
	
	private final int duration = 4;
	private final int cooldown = 6;
	
	public TelekineticShield (){
		super();
		setName("Telekinetic Shield");
		setTargetRelationship(Relationship.SELF);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		ArrayList<DamageType> types = new ArrayList<DamageType>();
		types.add(DamageType.SMASH);
		types.add(DamageType.SLASH);
		types.add(DamageType.STAB);
		ExecuteOnEventType reduce = new ExecuteOnEventType(TakeDamage.class, new ReduceDamage((float) .3, types));
		reduce.setPersistent(true);
		Queue.add(reduce, source, target, Condition.TAKE_DAMAGE);
		LogDisplay.log(target.getName() + " is protected by a reflective shield for " + duration + " turns.");
		
		ClearEvent eventClear = new ClearEvent(reduce);
		eventClear.delay(duration);
		Queue.add(eventClear, source, target, Condition.START_TURN);
		
		Queue.add(new Cooldown(cooldown, this), source, target, Condition.END_TURN);
	}
}
