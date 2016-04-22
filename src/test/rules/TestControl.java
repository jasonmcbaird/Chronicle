package test.rules;

import static org.junit.Assert.*;

import org.junit.Test;

import queue.Queue;
import rules.Control;
import character.Character;
import enums.Condition;

public class TestControl {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.setIsPlayer(true);
		Character jason = new Character();
		jason.setIsPlayer(false);

		Queue.addAndRun(new Control(cody.isPlayer()), cody, jason, Condition.ACTIVATE_ABILITY);
		assertTrue(jason.isPlayer());
	}
	
}
