package test.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import queue.Queue;
import rules.RegenerateHealth;
import character.Character;
import enums.Condition;
import enums.DamageType;

public class TestRegenerateHealth {
	
	@Test
	public void TestActivate() {
		Character cody = new Character();
		RegenerateHealth regen = new RegenerateHealth(2);
		cody.takeDamage(15, DamageType.BLEED);
		int beforeHealth = cody.attributes.getCurrentHealth();
		
		Queue.add(regen, cody, cody, Condition.START_TURN);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.attributes.getCurrentHealth(), beforeHealth + 2);
		
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.attributes.getCurrentHealth(), beforeHealth + 4);
	}
	
	@Test
	public void TestRandomActivate() {
		Character cody = new Character();
		RegenerateHealth regen = new RegenerateHealth(2, 4);
		
		Queue.add(regen, cody, cody, Condition.START_TURN);
		
		for(int i = 0; i<1000; i++) {
			cody.heal(10000);
			cody.takeDamage(15, DamageType.BLEED);
			int beforeHealth = cody.attributes.getCurrentHealth();

			Queue.run(Condition.START_TURN, cody);
			assertTrue(cody.attributes.getCurrentHealth() >= beforeHealth + 2 && cody.attributes.getCurrentHealth() <= beforeHealth + 4);
		}
	}
}
