package test.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import rules.UnPin;
import character.Character;
import enums.Condition;

public class TestUnPin {
	
	@Test
	public void testExecute() {
		Character cody = new Character();
		int priorMoveSpeed = cody.getMoveSpeed();
		
		Queue.addAndRun(new UnPin(3), cody, cody, Condition.END_TURN);
		
		assertEquals(priorMoveSpeed + 3, cody.getMoveSpeed());
	}

}
