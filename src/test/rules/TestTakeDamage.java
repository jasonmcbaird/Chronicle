package test.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import queue.Queue;
import rules.TakeDamage;
import character.Character;
import enums.Condition;
import enums.DamageType;
import enums.Stat;

public class TestTakeDamage {
	
	@Test
	public void TestActivate() {
		Character cody = new Character();
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		TakeDamage damage = new TakeDamage(DamageType.SMASH, 5);
		
		Queue.addAndRun(damage, cody, cody, Condition.TAKE_DAMAGE);
		int healthAfterAttack = cody.attributes.getCurrentHealth();
		assertEquals(healthAfterAttack, cody.attributes.getMaxHealth() - 5);
	}
	
	@Test
	public void TestRandom() {
		Character cody = new Character();
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		TakeDamage damage = new TakeDamage(DamageType.SMASH, 5, 10);
		
		for(int i = 0; i < 1000; i++) {
			cody.attributes.setCurrentHealth(cody.attributes.getMaxHealth());
			Queue.addAndRun(damage, cody, cody, Condition.TAKE_DAMAGE);
			int healthAfterAttack = cody.attributes.getCurrentHealth();
			assertTrue(healthAfterAttack <= cody.attributes.getMaxHealth() - 5);
			assertTrue(healthAfterAttack >= cody.attributes.getMaxHealth() - 10);
		}
	}
	
	@Test
	public void TestOverride() {
		Character cody = new Character();
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		TakeDamage damage = new TakeDamage(DamageType.SMASH, 5, 10);
		damage.overrideAmount(7);

		cody.attributes.setCurrentHealth(cody.attributes.getMaxHealth());
		Queue.addAndRun(damage, cody, cody, Condition.TAKE_DAMAGE);
		int healthAfterAttack = cody.attributes.getCurrentHealth();
		assertEquals(healthAfterAttack, cody.attributes.getMaxHealth() - 7);
	}
}