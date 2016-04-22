package rules;

import queue.Queue;
import ability.Ability;
import character.Character;
import enums.Condition;

public class Meteor extends Rule {
	
	private Ability ability;
	private int range;
	
	public Meteor(Ability ability, int range) {
		this.ability = ability;
		this.range = range;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Queue.addAndRun(new Attack(ability.getDamageBase(), ability.getDamageType(), ability.getAttackStat()), source, target, Condition.ATTACK);
		Queue.addAndRun(new Blast(ability, range), source, target, Condition.NOW);
	}
	
}
