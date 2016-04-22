package test.abilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import items.Weapon;
import items.accessories.Warhorse;
import items.weapons.ColdIronLongsword;

import org.junit.Test;

import queue.Queue;
import roles.rogue.Mug;
import character.Character;
import encounter.Encounter;
import enums.Condition;
import enums.EnergyType;
import enums.RoleName;
import enums.Stat;

public class TestMug {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.setRole(RoleName.ROGUE, 0);
		cody.changeEnergy(EnergyType.RHYTHM, 3);
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		cody.setX(4);
		cody.setY(5);
		Weapon sword = new ColdIronLongsword();
		cody.inventory.equip(sword);
		Character jason = new Character();
		jason.setX(5);
		jason.setY(5);
		jason.inventory.equip(new Warhorse());
		
		Encounter encounter = Encounter.get();
		encounter.add(jason);
		encounter.add(cody);
		
		assertEquals(1, jason.inventory.getAccessories().size());
		assertEquals(0, cody.inventory.getAccessories().size());
		Queue.addAndRun(new Mug(), cody, jason, Condition.ACTIVATE_ABILITY);
		
		assertTrue(jason.getMaxHealth() - sword.getDamageBase() + 2 <= jason.getHealth());
		assertTrue(jason.getMaxHealth() - sword.getDamageBase() + 3 >= jason.getHealth());
		
		assertEquals(0, jason.inventory.getAccessories().size());
		assertEquals(1, cody.inventory.getAccessories().size());
	}
	
}
