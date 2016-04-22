package roles.warlock;

import queue.Queue;
import rules.Attack;
import rules.Fear;
import rules.TakeDamage;
import utilities.Dice;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.DamageType;
import enums.Stat;

public class Dreadbolt extends Ability {
	
	private final int cost = 6;
	private final int fearAmount = 3;
	
	public Dreadbolt() {
		setName("Dreadbolt");
		setRange(3);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Attack attack = new Attack(10, DamageType.NEURO, Stat.MAGIC);
		Queue.addAndRun(attack, source, target, Condition.ATTACK);
		if(attack.wasHit()) {
			if(Dice.rollAgainstDC(6, 0, 3))
				fear(source, target);
		}
		Queue.addAndRun(new TakeDamage(DamageType.NEURO, cost), source, source, Condition.TAKE_DAMAGE);
	}
	
	private void fear(Character source, Character target) {
		Fear fear = new Fear(fearAmount);
		fear.setUntilTurnEnd(source, target);
		Queue.add(fear, source, target, Condition.MOVE);
	}
}