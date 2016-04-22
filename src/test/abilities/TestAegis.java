package test.abilities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import queue.Queue;
import roles.purger.Aegis;
import rules.TakeDamage;
import character.Character;
import enums.Condition;
import enums.DamageType;

public class TestAegis {
	
	@Test
	public void TestActivate() {
		Character cody = new Character();
		Aegis safeguard = new Aegis();
		
		Queue.addAndRun(safeguard, cody, cody, Condition.ACTIVATE_ABILITY);
		Queue.addAndRun(new TakeDamage(DamageType.STAB, 5), cody, cody, Condition.TAKE_DAMAGE);
		assertTrue(cody.attributes.getCurrentHealth() == cody.attributes.getMaxHealth());
		
		Queue.addAndRun(new TakeDamage(DamageType.STAB, 5), cody, cody, Condition.TAKE_DAMAGE);
		assertTrue(cody.attributes.getCurrentHealth() < cody.attributes.getMaxHealth());
	}
}
