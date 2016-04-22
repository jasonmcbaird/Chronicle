package test.abilities;

import static org.junit.Assert.*;

import org.junit.Test;

import queue.Queue;
import roles.warlock.Dreadbolt;
import rules.Move;
import character.Character;
import enums.Condition;
import enums.Direction;
import enums.Stat;

public class TestDreadbolt {
	
	@Test
	public void TestActivate() {
		Character cody = new Character();
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		cody.setX(5);
		cody.setY(4);
		Character jason = new Character();
		jason.setX(5);
		jason.setY(5);
		Dreadbolt dreadbolt = new Dreadbolt();
		
		Queue.addAndRun(dreadbolt, cody, jason, Condition.ACTIVATE_ABILITY);
		assertTrue(jason.getMaxHealth() > jason.getHealth());
		while(Queue.getEventSetList(Condition.MOVE, jason).size() < 1) {
			Queue.addAndRun(dreadbolt, cody, jason, Condition.ACTIVATE_ABILITY);
		}		
		
		Queue.addAndRun(new Move(Direction.RIGHT, 1), jason, jason, Condition.MOVE);
		assertEquals(5, jason.getX());
		assertEquals(8, jason.getY());
	}
}
