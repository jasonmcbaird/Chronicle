package test.abilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import roles.fate.TwistFate;
import character.Character;
import enums.Condition;
import enums.DamageType;

public class TestTwistFate {
	
	@Test
	public void testTwistFate() {
		TwistFate twistFate = new TwistFate();
		Character cody = new Character();
		Queue.add(twistFate, cody, cody, Condition.ACTIVATE_ABILITY);
		Queue.run(Condition.ACTIVATE_ABILITY, cody);
		cody.takeDamage(10000, DamageType.NEURO);
		Queue.run(Condition.DIE, cody);
		assertEquals(cody.attributes.getCurrentHealth(), cody.attributes.getMaxHealth()/2);
		cody.takeDamage(10000, DamageType.NEURO);
		Queue.run(Condition.DIE, cody);
		assertEquals(cody.attributes.getCurrentHealth(), 0);
	}
}