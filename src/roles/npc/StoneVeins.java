package roles.npc;

import queue.Queue;
import rules.ExecuteOnEventType;
import rules.ModifyDamage;
import rules.TakeDamage;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.DamageType;

public class StoneVeins extends Ability {
	
	public StoneVeins() {
		setPassive(true);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		ModifyDamage modify = new ModifyDamage(DamageType.BLEED, 0);
		Queue.add(new ExecuteOnEventType(TakeDamage.class, modify), source, target, Condition.TAKE_DAMAGE);
	}
}
