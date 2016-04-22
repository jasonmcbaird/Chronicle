package items.weapons;

import items.Weapon;
import character.Character;
import enums.Stat;

public class Buckler extends Weapon {
	
	private final int dodgeBonus = 2;
	
	public Buckler() {
		name = "Buckler";
	}
	
	public boolean offhand() {
		return true;
	}
	
	public void applyEquip(Character character) {
		character.attributes.changeStat(Stat.DODGE, dodgeBonus);
	}
	
	public void applyUnequip(Character character) {
		character.attributes.changeStat(Stat.DODGE, -dodgeBonus);
	}
}
