package roles.purger;

import queue.Queue;
import rules.Heal;
import ui.LogDisplay;
import utilities.Dice;
import utilities.Logger;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.Relationship;
import enums.RoleName;

public class Mend extends Ability {
	
	private final int healAmountDie = 6;
	private final int healAmountBonus = 6;
	
	public Mend() {
		super();
		setName("Mend");
		setRange(3);
		setTargetRelationship(Relationship.ALLY);
	}
	
	public boolean getCanBeActivated(Character character) {
		if(!super.getCanBeActivated(character))
			return false;
		Purger role = (Purger) character.getRole(RoleName.PURGER);
		if(role.getReadyAbilities().contains(this))
			return true;
		return false;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		int amount = Dice.rollDie(healAmountDie) + healAmountBonus;
		Queue.addAndRun(new Heal(amount), source, target, Condition.HEAL);
		LogDisplay.log(source.getName() + " heals " + target.getName() + " for " + amount + ".");
		Logger.print(source.getName() + " heals " + target.getName() + " for " + amount + ".");
		Purger role = (Purger) source.getRole(RoleName.PURGER);
		role.unreadyAbility(this);
	}
	
}
