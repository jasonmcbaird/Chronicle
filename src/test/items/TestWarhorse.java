package test.items;

import static org.junit.Assert.*;
import items.accessories.Warhorse;

import org.junit.Test;

import character.Character;
import enums.Stat;

public class TestWarhorse {
	
	@Test
	public void equipUnequip() {
		Character cody = new Character();
		Warhorse warhorse = new Warhorse();
		int initialSpeed = cody.getMoveSpeed();
		int initialDodge = cody.attributes.getStat(Stat.DODGE);
		int initialMaxHealth = cody.attributes.getMaxHealth();
		
		cody.inventory.equip(warhorse);
		assertEquals(initialSpeed + 3, cody.getMoveSpeed());
		assertEquals(initialDodge - 7, cody.attributes.getStat(Stat.DODGE));
		assertEquals(initialMaxHealth + 9, cody.attributes.getMaxHealth());
		
		cody.inventory.unequip(warhorse);
		assertEquals(initialSpeed, cody.getMoveSpeed());
		assertEquals(initialDodge, cody.attributes.getStat(Stat.DODGE));
		assertEquals(initialMaxHealth, cody.attributes.getMaxHealth());
	}

}
