package test.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import rules.ClearEvent;
import rules.Fear;
import rules.Move;
import character.Character;
import encounter.Encounter;
import enums.Condition;
import enums.Direction;
import enums.Stat;

public class TestFear {
	
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
		
		Fear fear = new Fear(2);
		fear.setUntilTurnEnd(cody, jason);
		Queue.add(fear, cody, jason, Condition.MOVE);
		assertEquals(jason.getY(), 5);
		
		Queue.addAndRun(new Move(Direction.RIGHT, 1), cody, jason, Condition.MOVE);
		Queue.addAndRun(new Move(Direction.RIGHT, 1), cody, cody, Condition.MOVE);
		assertEquals(jason.getX(), 5);
		Queue.addAndRun(new Move(Direction.RIGHT, 1), jason, jason, Condition.MOVE);
		assertEquals(jason.getX(), 5);
		assertEquals(jason.getY(), 7);
		
		Queue.addAndRun(new Move(Direction.RIGHT, 1), jason, jason, Condition.MOVE);
		assertEquals(jason.getX(), 5);
		assertEquals(jason.getY(), 9);
		
		Queue.run(Condition.END_TURN, jason);
		
		Queue.addAndRun(new Move(Direction.RIGHT, 1), jason, jason, Condition.MOVE);
		assertEquals(jason.getX(), 6);
		assertEquals(jason.getY(), 9);
	}
	
}
