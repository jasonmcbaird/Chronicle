package test.rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ability.Ability;
import queue.Queue;
import rules.Cooldown;
import character.Character;
import enums.Condition;

public class TestCooldown {
	
	@Test
	public void testCooldown() {
		TestAbility testAbility = new TestAbility();
		Character cody = new Character();
		Queue.add(testAbility, cody, cody, Condition.ACTIVATE_ABILITY);
		assertTrue(testAbility.getCanBeActivated(cody));
		Queue.run(Condition.ACTIVATE_ABILITY, cody);
		assertFalse(testAbility.getCanBeActivated(cody));
		Queue.run(Condition.START_TURN, cody);
		assertFalse(testAbility.getCanBeActivated(cody));
		Queue.run(Condition.START_TURN, cody);
		assertTrue(testAbility.getCanBeActivated(cody));
	}
	
	class TestAbility extends Ability {
		public void subExecute(Character source, Character target, Condition condition) {
			target.setName("Bitch");
			Cooldown cooldown = new Cooldown(2, this);
			Queue.add(cooldown, source, source, Condition.START_TURN);
		}
	}
}
