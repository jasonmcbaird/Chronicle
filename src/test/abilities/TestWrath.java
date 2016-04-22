package test.abilities;

import static org.junit.Assert.*;
import items.Weapon;
import items.weapons.Greataxe;

import org.junit.Test;

import queue.Queue;
import roles.berserker.Wrath;
import utilities.Logger;
import character.Character;
import enums.Condition;
import enums.EnergyType;
import enums.RoleName;
import enums.Stat;

public class TestWrath {
	
	@Test
	public void TestActivate() {
		Character cody = new Character();
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		cody.setRole(RoleName.BERSERKER, 0);
		Weapon greataxe = new Greataxe();
		cody.inventory.equip(greataxe);
		Character jason = new Character();
		Wrath wrath = new Wrath();
		
		cody.startEncounter();
		
		Queue.addAndRun(wrath, cody, jason, Condition.ACTIVATE_ABILITY);
		
		int healthAfterFirstHit = jason.getHealth();
		assertTrue(jason.getHealth() >= jason.getMaxHealth() - greataxe.getDamageBase() + 2);
		assertTrue(jason.getHealth() <= jason.getMaxHealth() - greataxe.getDamageBase() + 3);
		
		int energyAfterFirstHit = cody.getEnergy(EnergyType.RAGE);
		
		assertTrue(energyAfterFirstHit >= 4);
		assertTrue(energyAfterFirstHit <= 6);
		
		cody.changeEnergy(EnergyType.RAGE, -energyAfterFirstHit);
		cody.changeEnergy(EnergyType.RAGE, 5);
		
		Queue.addAndRun(wrath, cody, jason, Condition.ACTIVATE_ABILITY);
		
		assertTrue(jason.getHealth() >= healthAfterFirstHit - greataxe.getDamageBase() - 2);
		assertTrue(jason.getHealth() <= healthAfterFirstHit - greataxe.getDamageBase() - 1);
		
		assertTrue(cody.getEnergy(EnergyType.RAGE) >= 7);
		assertTrue(cody.getEnergy(EnergyType.RAGE) <= 9);
	}
	
}
