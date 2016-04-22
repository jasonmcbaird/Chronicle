package test.rules;

import org.junit.Test;

import queue.Queue;
import rules.EndTurn;
import character.Character;
import encounter.Encounter;
import enums.Condition;

public class TestEndTurn {
	
	@Test
	public void testExecute() {
		Character cody = new Character();
		cody.setIsPlayer(true);
		
		Encounter encounter = Encounter.get();
		encounter.add(cody);
		encounter.startEncounter();
		
		Queue.addAndRun(new EndTurn(Encounter.get().getGrid()), cody, cody, Condition.ACTIVATE_ABILITY);
		
		//TODO: Assert something, dumbass.
	}
}
