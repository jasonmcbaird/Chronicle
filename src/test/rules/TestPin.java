package test.rules;

import static org.junit.Assert.*;

import org.junit.Test;

import queue.Queue;
import rules.Pin;
import character.Character;
import enums.Condition;

public class TestPin {
	
	@Test
	public void testExecute() {
		Character cody = new Character();
		int priorMoveSpeed = cody.getMoveSpeed();
		
		Queue.addAndRun(new Pin(100), cody, cody, Condition.NOW);
		
		assertEquals(0, cody.getMoveSpeed());
		
		Queue.run(Condition.END_TURN, cody);
		
		assertEquals(priorMoveSpeed, cody.getMoveSpeed());
		
		Queue.addAndRun(new Pin(50), cody, cody, Condition.NOW);
		
		assertEquals(Math.round(priorMoveSpeed / 2), cody.getMoveSpeed());
	}

}
