package test.abilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import roles.sylvan.Vigor;
import character.Character;
import enums.Condition;
import enums.DamageType;

public class TestVigor {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.takeDamage(15, DamageType.NEURO);
		int beforeHealth = cody.attributes.getCurrentHealth();
		Vigor vigor = new Vigor();
		Queue.addAndRun(vigor, cody, cody, Condition.ACTIVATE_ABILITY);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.attributes.getCurrentHealth(), beforeHealth + 2);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.attributes.getCurrentHealth(), beforeHealth + 4);
	}
	
}
