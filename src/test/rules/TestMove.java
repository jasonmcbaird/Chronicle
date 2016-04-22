package test.rules;

import static org.junit.Assert.*;

import org.junit.Test;

import queue.Queue;
import rules.Move;
import character.Character;
import enums.Condition;
import enums.Direction;

public class TestMove {
	
	@Test
	public void TestDirectional() {
		Character cody = new Character();
		cody.setX(10);
		cody.setY(10);
		Move move = new Move(Direction.DOWN, 2);
		Queue.add(move, cody, cody, Condition.MOVE);
		Queue.run(Condition.MOVE, cody);
		assertEquals(cody.getX(), 10);
		assertEquals(cody.getY(), 12);
	}
	
	@Test
	public void TestSeconds() {
		Character cody = new Character();
		Move move = new Move(Direction.DOWN, 2);
		assertEquals(move.getSeconds(cody), 1);
		Move move2 = new Move(Direction.DOWN, 3);
		assertEquals(move2.getSeconds(cody), 2);
	}
}
