package roles.purger;

import queue.Queue;
import rules.DrawRevelation;
import utilities.Logger;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.RoleName;

public class Revelation extends Ability {
	
	public Revelation() {
		setName("Revelation");
		setPassive(true);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		DrawRevelation draw = new DrawRevelation();
		draw.setPersistent(true);
		Queue.add(draw, source, target, Condition.END_TURN);
		Purger role = (Purger) target.getRole(RoleName.PURGER);
		for(Ability ability: role.getAllAbilities())
			role.unreadyAbility(ability);
		
		Logger.print("Abilities available: " + role.getAllAbilities());
		int startingHand = (int) Math.ceil(role.getAllAbilities().size() / 2);
		Logger.print("Starting with " + startingHand + " purger abilities.");
		for(int i = 1; i <= startingHand; i++)
			draw.drawAbility(role, target);
	}
}