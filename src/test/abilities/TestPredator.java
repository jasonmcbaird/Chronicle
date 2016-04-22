package test.abilities;

import org.junit.Test;

import queue.Queue;
import ability.MutableAbility;
import character.Character;
import enums.Condition;

public class TestPredator {
	
	@Test
	public void testPredator() {
		Character cody = new Character();
		Character jason = new Character();
		Queue.addAndRun(new MutableAbility(), cody, jason, Condition.ACTIVATE_ABILITY);
	}
}