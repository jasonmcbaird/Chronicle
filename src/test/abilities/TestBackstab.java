package test.abilities;

import static org.junit.Assert.*;
import items.Weapon;
import items.weapons.ColdIronLongsword;

import org.junit.Test;

import queue.Queue;
import roles.warp.Backstab;
import character.Character;
import encounter.Encounter;
import enums.Condition;
import enums.RoleName;
import enums.Stat;

public class TestBackstab {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.setRole(RoleName.WARRIOR, 0);
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		cody.setX(4);
		cody.setY(5);
		Weapon longsword = new ColdIronLongsword();
		cody.inventory.equip(longsword);
		Character jason = new Character();
		jason.setX(5);
		jason.setY(5);
		jason.attributes.changeStat(Stat.HEALTH, 20);
		
		Encounter encounter = Encounter.get();
		encounter.add(jason);
		encounter.add(cody);
		
		Queue.addAndRun(new Backstab(), cody, jason, Condition.ACTIVATE_ABILITY);
		
		assertEquals(6, cody.getX());
		int healthAfterOne = jason.getHealth();
		assertTrue(jason.getMaxHealth() - longsword.getDamageBase() - 3 <= healthAfterOne);
		assertTrue(jason.getMaxHealth() - longsword.getDamageBase() - 2 >= healthAfterOne);
		
		Character jody = new Character();
		jody.setX(4);
		jody.setY(5);
		encounter.add(jody);
		
		Queue.addAndRun(new Backstab(), cody, jason, Condition.ACTIVATE_ABILITY);
		
		assertEquals(5, cody.getX());
		int codyY = cody.getY();
		assertTrue(4 == codyY || 6 == codyY);
		int healthAfterTwo = jason.getHealth();
		assertTrue(healthAfterOne - longsword.getDamageBase() <= healthAfterTwo);
		assertTrue(healthAfterOne - longsword.getDamageBase() + 1 >= healthAfterTwo);
		
		Character drew = new Character();
		drew.setX(5);
		drew.setY(6);
		encounter.add(drew);
		
		Character cheyenne = new Character();
		cheyenne.setX(6);
		cheyenne.setY(5);
		encounter.add(cheyenne);
		
		Character colin = new Character();
		colin.setX(5);
		colin.setY(4);
		encounter.add(colin);
		
		Queue.addAndRun(new Backstab(), cody, jason, Condition.ACTIVATE_ABILITY);
		assertEquals(5, cody.getX());
		assertEquals(cody.getY(), codyY);
		assertTrue(healthAfterTwo - longsword.getDamageBase() + 2 <= jason.getHealth());
		assertTrue(healthAfterTwo - longsword.getDamageBase() + 3 >= jason.getHealth());


	}
	
}
