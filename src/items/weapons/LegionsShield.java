package items.weapons;

import items.Weapon;
import items.abilities.Shelter;
import utilities.Logger;
import character.Character;
import enums.Stat;

public class LegionsShield extends Weapon {
	
	private final int dodgeBonus = 4;
	private final int agilityPenalty = 1;
	
	public LegionsShield() {
		name = "Legion's Shield";
		abilities.add(new Shelter());
		damageBase ++;
	}
	
	public boolean offhand() {
		return true;
	}
	
	public void applyEquip(Character character) {
		Logger.print("Applying Legion's Shield");
		character.attributes.changeStat(Stat.DODGE, dodgeBonus);
		character.attributes.changeStat(Stat.AGILITY, -agilityPenalty);
	}
	
	public void applyUnequip(Character character) {
		character.attributes.changeStat(Stat.DODGE, -dodgeBonus);
		character.attributes.changeStat(Stat.AGILITY, agilityPenalty);
	}
}
