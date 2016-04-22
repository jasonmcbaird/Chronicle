package roles.psion;

import ability.Ability;
import queue.Queue;
import rules.Attack;
import rules.Cooldown;
import character.Character;
import enums.Condition;
import enums.DamageType;
import enums.Stat;

// Ability:  Shatter
// Level:    0
// Passive:  False
//
// Rules:   1. When you use this ability, deal damage to a target enemy character. You deal more damage if your Magic is high and less if the target's Resilience is high.
//			2. When you use this ability, disable it.
//			3. Two turns after you use this ability, enable it again.

public class Shatter extends Ability {

	private final int cooldown = 3;
	
	public Shatter() {
		super();
		setName("Shatter");
		setBaseDamage(13);
		setRange(3);
		setDamageType(DamageType.NEURO);
		setAttackStat(Stat.MAGIC);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Queue.addAndRun(new Attack(getDamageBase(), getDamageType(), getAttackStat()), source, target, Condition.ATTACK);
		Queue.add(new Cooldown(cooldown, this), source, source, Condition.END_TURN);
	}
}
