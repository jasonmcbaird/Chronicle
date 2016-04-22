package items.accessories;

import items.Accessory;
import character.Character;
import enums.Stat;

public class Warhorse extends Accessory {
	
	private final int speedBonus = 3;
	private final int healthBonus = 3;
	private final int dodgePenalty = 7;
	
	public Warhorse() {
		name = "Warhorse";
	}
	
	public void applyEquip(Character character) {
		character.attributes.changeMoveSpeed(speedBonus);
		character.attributes.changeStat(Stat.HEALTH, healthBonus);
		character.attributes.changeStat(Stat.DODGE, -dodgePenalty);
	}
	
	public void applyUnequip(Character character) {
		character.attributes.changeMoveSpeed(-speedBonus);
		character.attributes.changeStat(Stat.HEALTH, -healthBonus);
		character.attributes.changeStat(Stat.DODGE, dodgePenalty);
	}

}
