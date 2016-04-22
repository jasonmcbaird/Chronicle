package test.rules;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import queue.Event;
import queue.Queue;
import rules.ClearEvent;
import character.Character;
import enums.Condition;

public class TestClearEvent {
	
	@Test
	public void testEventClear() {
		TestEvent testEvent = new TestEvent();
		Character cody = new Character();
		cody.setName("Cody");
		ClearEvent eventClear = new ClearEvent(testEvent);
		Queue.add(testEvent, cody, cody, Condition.START_TURN);
		Queue.add(eventClear, cody, cody, Condition.HEAL);
		Queue.run(Condition.HEAL, cody);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.getName(), "Cody");
		Queue.add(testEvent, cody, cody, Condition.START_TURN);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.getName(), "Bitch");
	}
	
	@Test
	public void testEventClearMultiple() {
		TestEvent testEvent = new TestEvent();
		TestEvent testEvent2 = new TestEvent();
		ArrayList<Event> testEvents = new ArrayList<Event>();
		testEvents.add(testEvent);
		testEvents.add(testEvent2);
		Character cody = new Character();
		cody.setName("Cody");
		ClearEvent eventClear = new ClearEvent(testEvents);
		
		Queue.add(testEvent, cody, cody, Condition.START_TURN);
		Queue.add(testEvent2, cody, cody, Condition.START_TURN);
		Queue.add(eventClear, cody, cody, Condition.HEAL);
		
		Queue.run(Condition.HEAL, cody);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.getName(), "Cody");
		
		Queue.add(testEvent, cody, cody, Condition.START_TURN);
		Queue.add(testEvent2, cody, cody, Condition.START_TURN);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.getName(), "Bitch");
	}
	
	class TestEvent implements Event {
		
		public int getPriority() {
			return 0;
		}
		
		public void execute(Character source, Character target, Condition condition) {
			source.setName("Bitch");
		}
	}
}
