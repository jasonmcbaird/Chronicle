package test.rules;

import static org.junit.Assert.*;

import org.junit.Test;

import queue.Queue;
import rules.Die;
import character.Character;
import encounter.Encounter;
import enums.Condition;

public class TestDie {
	
	@Test
	public void testExecute() {
		Character cody = new Character();
		
		Encounter encounter = new Encounter();
		encounter.add(cody);
		assertEquals(encounter.getCharacters().size(), 1);
		
		Queue.addAndRun(new Die(), cody, cody, Condition.DIE);
		
		assertEquals(encounter.getCharacters().size(), 0);
	}
}
