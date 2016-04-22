package test.abilities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ability.Ability;
import queue.Queue;
import roles.psion.Focus;
import rules.Cooldown;
import character.Character;
import enums.Condition;
import enums.Stat;

public class TestFocus {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		Queue.addAndRun(new Focus(), cody, cody, Condition.ACTIVATE_ABILITY);
		Character jason = new Character();
		TestAbility test = new TestAbility();
		
		assertTrue(test.getCanBeActivated(cody));
		Queue.addAndRun(test, cody, jason, Condition.ACTIVATE_ABILITY);
		assertFalse(test.getCanBeActivated(cody));

		Queue.run(Condition.ATTACK, cody);
		Queue.run(Condition.END_TURN, cody);
		Queue.run(Condition.ATTACK, cody);
		Queue.run(Condition.END_TURN, cody);
		assertFalse(test.getCanBeActivated(cody));
		
		Queue.run(Condition.END_TURN, cody);
		assertFalse(test.getCanBeActivated(cody));
		Queue.run(Condition.END_TURN, cody);
		assertTrue(test.getCanBeActivated(cody));
	}
	
	class TestAbility extends Ability {
		public void subExecute(Character source, Character target, Condition condition) {
			target.setName("Bitch");
			Cooldown cooldown = new Cooldown(2, this);
			Queue.add(cooldown, source, source, Condition.START_TURN);
		}
	}
	
}
