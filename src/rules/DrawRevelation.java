package rules;

import java.util.ArrayList;

import roles.purger.Purger;
import utilities.Dice;
import utilities.Logger;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.RoleName;

public class DrawRevelation extends Rule {
	
	public DrawRevelation() {
		
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Purger role = (Purger) target.getRole(RoleName.PURGER);
		if(role.getAllAbilities().size() > role.getReadyAbilities().size())
			drawAbility(role, target);
	}
	
	public void drawAbility(Purger role, Character target) {
		role.readyAbility(getRandomUnreadyAbility(role, target));
		Logger.print("Purger abilities ready:");
		for(Ability ability: role.getReadyAbilities())
			Logger.print(ability.getName());
	}
	
	private Ability getRandomUnreadyAbility(Purger role, Character target) {
		ArrayList<Ability> unreadyAbilities = role.getAllAbilities();
		unreadyAbilities.removeAll(role.getReadyAbilities());
		return unreadyAbilities.get(Dice.rollDie(unreadyAbilities.size()) - 1);
	}
}
