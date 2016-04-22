package test.items;

import static org.junit.Assert.assertEquals;
import items.armors.ScaleMail;

import org.junit.Test;

import queue.Queue;
import roles.npc.StoneVeins;
import rules.TakeDamage;
import character.Character;
import encounter.Encounter;
import enums.Condition;
import enums.DamageType;
import enums.Stat;

public class TestScaleMail {

	
	@Test
	public void equipUnequip() {
		Character cody = new Character();
		ScaleMail scale = new ScaleMail();
		int initialDefense = cody.attributes.getStat(Stat.DEFENSE);
		int initialAgility = cody.attributes.getStat(Stat.AGILITY);
		cody.inventory.equip(scale);
		assertEquals(initialDefense + 4, cody.attributes.getStat(Stat.DEFENSE));
		assertEquals(initialAgility - 2, cody.attributes.getStat(Stat.AGILITY));
		
		Encounter encounter = new Encounter();
		encounter.add(cody);
		encounter.startEncounter();
		
		Queue.addAndRun(new TakeDamage(DamageType.STAB, 4), cody, cody, Condition.TAKE_DAMAGE);
		
		assertEquals(cody.getMaxHealth() - 3, cody.getHealth());
		
		Queue.addAndRun(new TakeDamage(DamageType.SMASH, 4), cody, cody, Condition.TAKE_DAMAGE);
		
		assertEquals(cody.getMaxHealth() - 7, cody.getHealth());
		
		cody.inventory.unequip(scale);
		assertEquals(initialDefense, cody.attributes.getStat(Stat.DEFENSE));
		assertEquals(initialAgility, cody.attributes.getStat(Stat.AGILITY));
	}
}
