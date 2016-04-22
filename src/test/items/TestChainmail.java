package test.items;

import static org.junit.Assert.assertEquals;
import items.armors.Chainmail;

import org.junit.Test;

import character.Character;
import enums.Stat;

public class TestChainmail {

	
	@Test
	public void equipUnequip() {
		Character cody = new Character();
		Chainmail chainmail = new Chainmail();
		int initialDefense = cody.attributes.getStat(Stat.DEFENSE);
		int initialAgility = cody.attributes.getStat(Stat.AGILITY);
		cody.inventory.equip(chainmail);
		assertEquals(initialDefense + 6, cody.attributes.getStat(Stat.DEFENSE));
		assertEquals(initialAgility - 2, cody.attributes.getStat(Stat.AGILITY));
		cody.inventory.unequip(chainmail);
		assertEquals(initialDefense, cody.attributes.getStat(Stat.DEFENSE));
		assertEquals(initialAgility, cody.attributes.getStat(Stat.AGILITY));
	}
}
