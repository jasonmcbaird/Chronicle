package test.abilities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import queue.Queue;
import roles.psion.Shatter;
import character.Character;
import enums.Condition;
import enums.Stat;

public class TestShatter {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		Character jason = new Character();
		Shatter shatter = new Shatter();
		
		assertTrue(shatter.getCanBeActivated(cody));
		Queue.add(shatter, cody, jason, Condition.ACTIVATE_ABILITY);
		Queue.run(Condition.ACTIVATE_ABILITY, cody);
		
		assertFalse(shatter.getCanBeActivated(cody));
		Queue.run(Condition.END_TURN, cody);
		Queue.run(Condition.END_TURN, cody);
		Queue.run(Condition.END_TURN, cody);
		assertTrue(shatter.getCanBeActivated(cody));
	}
	
}
