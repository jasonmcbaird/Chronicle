package test.abilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import items.Weapon;
import items.weapons.Greataxe;
import items.weapons.InfernoLongbow;

import org.junit.Test;

import queue.Queue;
import roles.ranger.Snipe;
import character.Character;
import enums.Condition;
import enums.Stat;

public class TestSnipe {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.inventory.equip(new Greataxe());
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		Snipe snipe = new Snipe();
		assertFalse(snipe.getCanBeActivated(cody));
		
		Weapon bow = new InfernoLongbow();
		cody.inventory.equip(bow);
		
		assertTrue(snipe.getCanBeActivated(cody));
		
		assertEquals(new InfernoLongbow().getRange(cody) + 2, snipe.getRange(cody));
		
		Queue.addAndRun(snipe, cody, cody, Condition.ACTIVATE_ABILITY);
		
		assertEquals(cody.getMaxHealth() - bow.getDamageBase() - 1, cody.getHealth());
	}
	
}
