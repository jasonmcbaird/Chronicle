package test.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ability.Ability;
import queue.Event;
import queue.Queue;
import rules.Heal;
import utilities.Logger;
import character.Character;
import enums.Condition;

public class TestQueue {
	
	@Test
	public void testAddGet() {
		Logger.print("Adding and getting");
		Character cody = new Character();
		Event event = new TestEvent();
		Queue.add(event, cody, cody, Condition.START_TURN);
		Event gottenEvent = Queue.getEventSetList(Condition.START_TURN, cody).get(0).getEvent();
		assertEquals(event, gottenEvent);
		Logger.print("Done adding and getting");
	}
	
	@Test
	public void testRun() {
		Character cody = new Character();
		Queue.add(new TestEvent(), cody, cody, Condition.START_TURN);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.getName(), "Bitch");
	}
	
	@Test
	public void testAddAndRun() {
		Character cody = new Character();
		Queue.addAndRun(new TestEvent(), cody, cody, Condition.START_TURN);
		assertEquals(cody.getName(), "Bitch");
		cody.setName("Cody");
		Queue.addAndRun(new TestAbility(), new Character(), cody, Condition.ACTIVATE_ABILITY);
		assertEquals(cody.getName(), "Bitch");
	}
	
	@Test
	public void testRemoveWithEvent() {
		Character character = new Character();
		Event event = new TestEvent();
		Queue.add(event, character, character, Condition.START_TURN);
		Queue.remove(event);
		assertEquals(Queue.getEventSetList(Condition.START_TURN).size(), 0);
	}
	
	@Test
	public void testRemoveWithIndex() {
		Character cody = new Character();
		Event event = new TestEvent();
		Queue.add(event, cody, cody, Condition.START_TURN);
		Queue.remove(Condition.START_TURN, 0);
		assertEquals(Queue.getEventSetList(Condition.START_TURN).size(), 0);
	}
	
	@Test
	public void testAddAbility() {
		Character jasonSource = new Character();
		Character codyTarget = new Character();
		codyTarget.setName("Cody");
		Ability ability = new TestAbility();
		Queue.add(ability, jasonSource, codyTarget, Condition.ACTIVATE_ABILITY);
		Queue.run(Condition.ACTIVATE_ABILITY, codyTarget);
		assertEquals(codyTarget.getName(), "Cody");
		Queue.run(Condition.ACTIVATE_ABILITY, jasonSource);
		assertEquals(codyTarget.getName(), "Bitch");
	}
	
	class TestAbility extends Ability {
		public void subExecute(Character source, Character target, Condition condition) {
			target.setName("Bitch");
		}
	}
	
	@Test
	public void testRunNow() {
		Character cody = new Character();
		Queue.add(new TestEvent(), cody, cody, Condition.NOW);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.getName(), "Bitch");
	}
	
	@Test
	public void testPriority() {
		Character cody = new Character();
		cody.attributes.setCurrentHealth(0);
		Queue.add(new TestRetainOrderEvent(1), cody, cody, Condition.START_TURN);
		Queue.add(new TestRetainOrderEvent(2), cody, cody, Condition.START_TURN);
		Queue.add(new TestRetainOrderEvent(3), cody, cody, Condition.START_TURN);
		
		TestPriorityEvent minus = new TestPriorityEvent();
		minus.setPriority(-100);
		Queue.add(minus, cody, cody, Condition.START_TURN);
		
		TestPriorityEvent plus = new TestPriorityEvent();
		plus.setPriority(100);
		Queue.add(plus, cody, cody, Condition.START_TURN);
		
		Queue.run(Condition.START_TURN, cody);
	}
	
	class TestEvent implements Event {
		
		public int getPriority() {
			return 0;
		}
		
		public void execute(Character source, Character target, Condition condition) {
			target.setName("Bitch");
		}
	}
	
	class TestRetainOrderEvent implements Event {
		
		int test = 0;
		
		public TestRetainOrderEvent(int i) {
			test = i;
		}
		
		public void execute(Character source, Character target, Condition condition) {
			Queue.addAndRun(new Heal(1), source, target, Condition.NOW);
			assertEquals(target.attributes.getCurrentHealth(), test);
			target.setName("Bitch");
		}

		public int getPriority() {
			return 0;
		}
	}
	
	class TestPriorityEvent implements Event {
		private int priority = 0;
		
		public int getPriority() {
			return priority;
		}
		
		public void setPriority(int priority) {
			this.priority = priority;
		}
		
		public void execute(Character source, Character target, Condition condition) {
			if(getPriority() == 0) {
				target.setName("Bitch");
			}
			if(getPriority() > 0) {
				assertEquals(target.getName(), "Codybitch");
			}
			if(getPriority() < 0) {
				assertEquals(target.getName(), "Bitch");
			}
		}
	}
}

