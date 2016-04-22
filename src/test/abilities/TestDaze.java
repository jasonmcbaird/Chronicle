package test.abilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ability.Ability;
import queue.Queue;
import roles.psion.Daze;
import character.Character;
import enums.Condition;
import enums.Stat;

public class TestDaze {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.setName("Cody");
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		Character jason = new Character();
		Daze daze = new Daze();
		
		assertTrue(daze.getCanBeActivated(cody));
		Queue.add(daze, cody, jason, Condition.ACTIVATE_ABILITY);
		Queue.run(Condition.ACTIVATE_ABILITY, cody);
		
		assertFalse(daze.getCanBeActivated(cody));
		Queue.run(Condition.START_TURN, cody);
		Queue.run(Condition.START_TURN, cody);
		Queue.run(Condition.START_TURN, cody);
		Queue.run(Condition.START_TURN, cody);
		assertTrue(daze.getCanBeActivated(cody));
		
		Queue.add(new TestAbility(), jason, cody, Condition.ACTIVATE_ABILITY);
		Queue.run(Condition.ACTIVATE_ABILITY, jason);
		assertEquals(cody.getName(), "Cody");
		
		Queue.add(new TestAbility(), jason, cody, Condition.ACTIVATE_ABILITY);
		Queue.run(Condition.ACTIVATE_ABILITY, jason);
		assertEquals(cody.getName(), "Bitch");	}
	
	class TestAbility extends Ability {
		public void subExecute(Character source, Character target, Condition condition) {
			target.setName("Bitch");
		}
	}
	
}
