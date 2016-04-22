package items.accessories;

import character.Character;
import enums.Stat;
import items.Accessory;

public class GuardianAmulet extends Accessory {
	
	private final int defenseBonus = 2;
	
	public GuardianAmulet() {
		name = "Guardian Amulet";
	}
	
	public void applyEquip(Character character) {
		character.attributes.changeStat(Stat.VITALITY, defenseBonus);
	}
	
	public void applyUnequip(Character character) {
		character.attributes.changeStat(Stat.VITALITY, -defenseBonus);
	}

}
