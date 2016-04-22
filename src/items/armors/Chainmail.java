package items.armors;

import items.Armor;
import character.Character;
import enums.Stat;

public class Chainmail extends Armor {
	
	private final int defenseBonus = 6;
	private final int agilityPenalty = 2;
	
	public Chainmail() {
		name = "Chainmail";
	}
	
	public void applyEquip(Character character) {
		character.attributes.changeStat(Stat.DEFENSE, defenseBonus);
		character.attributes.changeStat(Stat.AGILITY, -agilityPenalty);
	}
	
	public void applyUnequip(Character character) {
		character.attributes.changeStat(Stat.DEFENSE, -defenseBonus);
		character.attributes.changeStat(Stat.AGILITY, agilityPenalty);
	}

}
