package rules;

import ability.Ability;
import queue.Queue;
import character.Character;
import enums.Condition;

public class Cooldown extends Rule {
	
	Ability ability;
	int turns;
	
	public Cooldown(int turns, Ability ability) {
		this.ability = ability;
		this.turns = turns;
		ability.setCanBeActivated(false);
		persistent = true;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		turns--;
		checkCooldown(target);
	}
	
	public void modifyCooldown(int i, Character target) {
		turns += i;
		checkCooldown(target);
	}
	
	private void checkCooldown(Character target) {
		if(turns <= 0) {
			ability.setCanBeActivated(true);
			persistent = false;
			Queue.remove(this);
			Queue.run(Condition.COOLDOWN_FINISH, target);
		}
	}
	
}
