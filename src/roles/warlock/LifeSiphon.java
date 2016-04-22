package roles.warlock;

import queue.Queue;
import rules.Attack;
import rules.Heal;
import rules.TakeDamage;
import ui.LogDisplay;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.DamageType;
import enums.Stat;

public class LifeSiphon extends Ability {
	
	private final int cost = 3;
	
	public LifeSiphon() {
		super();
		setName("Life Siphon");
		setRange(3);
		setBaseDamage(9);
		setDamageType(DamageType.NEURO);
		setAttackStat(Stat.MAGIC);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {		
		Attack attack = new Attack(getDamageBase(), getDamageType(), getAttackStat());
		Queue.addAndRun(attack, source, target, Condition.ATTACK);
		if(attack.wasHit()) {
			Queue.addAndRun(new Heal(Math.round(attack.getDamageDealt() / 2)), source, source, Condition.HEAL);
			LogDisplay.log(source.getName() + " siphons " + Math.round(attack.getDamageDealt() / 2) + " health from " + target.getName() + ".");
		}
		Queue.addAndRun(new TakeDamage(DamageType.NEURO, cost), source, source, Condition.TAKE_DAMAGE); // This spell costs 3 HP
	}
}