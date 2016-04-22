package test.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import rules.Knockback;
import character.Character;
import encounter.Encounter;
import enums.Condition;
import enums.Stat;

public class TestKnockback {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.setX(4);
		cody.setY(4);
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		Character jason = new Character();
		jason.setX(4);
		jason.setY(5);
		
		Encounter encounter = Encounter.get();
		encounter.add(cody);
		encounter.add(jason);

		Queue.addAndRun(new Knockback(1), cody, jason, Condition.ACTIVATE_ABILITY);
		assertEquals(jason.getY(), 6);
	}
	
}
