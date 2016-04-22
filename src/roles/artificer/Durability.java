package roles.artificer;

import ability.Ability;
import character.Character;
import enums.Condition;

public class Durability extends Ability {
	
	private Artificer artificer;
	
	public Durability(Artificer artificer) {
		setName("Durability");
		setPassive(true);
		this.artificer = artificer;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		artificer.resetDurability();
	}
}
