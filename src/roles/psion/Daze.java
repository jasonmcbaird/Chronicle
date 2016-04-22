package roles.psion;

import ability.Ability;
import queue.Queue;
import rules.Attack;
import rules.Cooldown;
import rules.ClearEvent;
import rules.ExecuteOnEventType;
import ui.LogDisplay;
import utilities.Logger;
import character.Character;
import enums.Condition;
import enums.DamageType;
import enums.Stat;

//Ability:  Daze
//Level:    2
//Passive:  False
//
//Rules:    1. Deal physiological damage to a target based on your Magic.
//			2. If you hit, the target becomes stunned.
//			3. When a stunned character would activate an ability, they recover from stun instead.
//			4. When you cast Daze, it becomes disabled.
//			5. (4) turns after you cast Daze, it becomes enabled again.

public class Daze extends Ability {
	
	private final int cooldown = 5;
	
	public Daze() {
		super();
		setName("Daze");
		setRange(2);
		setBaseDamage(7);
		setDamageType(DamageType.NEURO);
		setAttackStat(Stat.MAGIC);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Attack attack = new Attack(getDamageBase(), getDamageType(), getAttackStat());
		Queue.addAndRun(attack, source, target, Condition.ATTACK);
		if(attack.wasHit()) {
			ExecuteOnEventType preventAbility = new ExecuteOnEventType(Ability.class, new ClearEvent());
			Queue.add(preventAbility, source, target, Condition.ACTIVATE_ABILITY);
			Logger.print(target.getName() + " becomes stunned!", 1);
			LogDisplay.log(target.getName() + " is stunned by " + getName());
		}
		Queue.add(new Cooldown(cooldown, this), source, source, Condition.END_TURN);
	}
}
