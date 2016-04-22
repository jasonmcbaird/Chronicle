package test.abilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ability.Ability;
import queue.Queue;
import roles.psion.Inspiration;
import rules.Cooldown;
import character.Character;
import enums.Condition;

public class TestInspiration {
	
	@Test
	public void TestBoost() {	
		Character cody = new Character();
		Inspiration inspiration = new Inspiration();
		Queue.addAndRun(inspiration, cody, cody, Condition.ACTIVATE_ABILITY);
		Ability test = new TestAbility();
		test.setRange(2);
		assertEquals(2, test.getRange(cody));
		Queue.add(new Cooldown(1, test), cody, cody, Condition.START_TURN);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(3, test.getRange(cody));
	}
	
	@Test
	public void TestOneRange() {
		Character cody = new Character();
		Inspiration inspiration = new Inspiration();
		Queue.addAndRun(inspiration, cody, cody, Condition.ACTIVATE_ABILITY);
		Ability test = new TestAbility();
		test.setRange(1);
		assertEquals(test.getRange(cody), 1);
		Queue.add(new Cooldown(1, test), cody, cody, Condition.START_TURN);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(test.getRange(cody), 1);
	}
	
	@Test
	public void TestEOT() {	
		Character cody = new Character();
		Inspiration inspiration = new Inspiration();
		Queue.addAndRun(inspiration, cody, cody, Condition.ACTIVATE_ABILITY);
		Ability test = new TestAbility();
		test.setRange(2);
		assertEquals(test.getRange(cody), 2);
		Queue.add(new Cooldown(1, test), cody, cody, Condition.START_TURN);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(test.getRange(cody), 3);
		Queue.run(Condition.END_TURN, cody);
		assertEquals(test.getRange(cody), 2);
	}
	
	@Test
	public void TestMultiple() {	
		Character cody = new Character();
		Inspiration inspiration = new Inspiration();
		Queue.addAndRun(inspiration, cody, cody, Condition.ACTIVATE_ABILITY);
		Ability test = new TestAbility();
		test.setRange(2);
		assertEquals(test.getRange(cody), 2);
		Queue.add(new Cooldown(1, test), cody, cody, Condition.START_TURN);
		Queue.add(new Cooldown(1, test), cody, cody, Condition.START_TURN);
		Queue.add(new Cooldown(1, test), cody, cody, Condition.START_TURN);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(test.getRange(cody), 5);
		Queue.run(Condition.END_TURN, cody);
		assertEquals(test.getRange(cody), 2);
		Queue.run(Condition.END_TURN, cody);
		assertEquals(test.getRange(cody), 2);
	}
	
	class TestAbility extends Ability {
		public void subExecute(Character source, Character target, Condition condition) {
			target.setName("Bitch");
		}
		
		public void setRange(int i) {
			range = i;
		}
	}
}
