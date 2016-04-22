package test.abilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import roles.npc.StoneVeins;
import rules.TakeDamage;
import character.Character;
import enums.Condition;
import enums.DamageType;

public class TestStoneVeins {

	
	@Test
	public void testDamage() {
		
		Character cody = new Character();
		
		Queue.addAndRun(new StoneVeins(), cody, cody, Condition.NOW);
		
		Queue.addAndRun(new TakeDamage(DamageType.BLEED, 4), cody, cody, Condition.TAKE_DAMAGE);
		
		assertEquals(cody.getMaxHealth(), cody.getHealth());
		
		Queue.addAndRun(new TakeDamage(DamageType.SMASH, 4), cody, cody, Condition.TAKE_DAMAGE);
		
		assertEquals(cody.getMaxHealth() - 4, cody.getHealth());
		
		
	}
}
