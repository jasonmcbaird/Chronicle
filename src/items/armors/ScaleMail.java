package items.armors;

import items.Armor;
import queue.Queue;
import rules.ExecuteOnEventType;
import rules.ModifyDamage;
import rules.TakeDamage;
import character.Character;
import enums.Condition;
import enums.DamageType;
import enums.Stat;

public class ScaleMail extends Armor {
	
	private final int defenseBonus = 4;
	private final int agilityPenalty = 2;
	private final int stabPercentDamage = 75;
	
	public ScaleMail() {
		name = "Scale Mail";
	}
	
	public void applyEquip(Character character) {
		character.attributes.changeStat(Stat.DEFENSE, defenseBonus);
		character.attributes.changeStat(Stat.AGILITY, -agilityPenalty);
	}
	
	public void applyUnequip(Character character) {
		character.attributes.changeStat(Stat.DEFENSE, -defenseBonus);
		character.attributes.changeStat(Stat.AGILITY, agilityPenalty);
	}
	
	public void startEncounter(Character character) {
		ModifyDamage modify = new ModifyDamage(DamageType.STAB, stabPercentDamage);
		ExecuteOnEventType execute = new ExecuteOnEventType(TakeDamage.class, modify);
		Queue.add(execute, character, character, Condition.TAKE_DAMAGE);
	}

}
