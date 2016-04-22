package rules;

import mapLogic.GridLogic;
import queue.Queue;
import utilities.Logger;
import ability.Ability;
import character.Character;
import enums.Condition;

public class Blast extends Rule {
	
	private Ability ability;
	private int range;
	
	public Blast(Ability ability, int range) {
		this.ability = ability;
		this.range = range;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		for(Character character: GridLogic.getTargetsInRange(target, range)) {
			Queue.addAndRun(new Attack(ability.getDamageBase(), ability.getDamageType(), ability.getAttackStat()), source, character, Condition.ATTACK);
			Logger.print(character.getName());
		}
	}
	
}
