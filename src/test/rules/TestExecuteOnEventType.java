package test.rules;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ability.Ability;
import queue.Event;
import queue.EventModifier;
import queue.Queue;
import rules.Attack;
import rules.ExecuteOnEventType;
import rules.Rule;
import character.Character;
import enums.Condition;

public class TestExecuteOnEventType {
	
	@Test
	public void testDefaultCondition() {
		Character cody = new Character();
		Ability test = new TestAbility();
		Attack attack = new Attack(test.getDamageBase(), test.getDamageType(), test.getAttackStat());
		Attack attack2 = new Attack(test.getDamageBase(), test.getDamageType(), test.getAttackStat());
		int originalBase = attack.getDamageBase();
		int originalBase2 = attack2.getDamageBase();
		
		ExecuteOnEventType execute = new ExecuteOnEventType(Attack.class, new TestModifier());
		
		Queue.add(execute, cody, cody, Condition.ATTACK);
		Queue.add(test, cody, cody, Condition.ATTACK);
		Queue.add(attack2, cody, cody, Condition.ATTACK);
		Queue.addAndRun(attack, cody, cody, Condition.ATTACK);
		
		//Logger.print(attack.getDamageBase() + " vs " + originalBase * 1.2);
		assertTrue(attack.getDamageBase() == originalBase + 1);
		assertTrue(attack2.getDamageBase() == originalBase2 + 1);

	}
	
	@Test
	public void testCustomCondition() {
		Character cody = new Character();
		Ability test = new TestAbility();
		Attack attack = new Attack(test.getDamageBase(), test.getDamageType(), test.getAttackStat());
		int originalBase = attack.getDamageBase();
		
		ExecuteOnEventType execute = new ExecuteOnEventType(Attack.class, new TestModifier(), Condition.ATTACK);
		Queue.add(execute, cody, cody, Condition.NOW);
		Queue.add(test, cody, cody, Condition.ATTACK);
		Queue.addAndRun(attack, cody, cody, Condition.ATTACK);
		
		assertTrue(attack.getDamageBase() == originalBase + 1);
	}
	
	class TestAbility extends Ability {
		public void subExecute(Character source, Character target, Condition condition) {
			target.setName("Bitch");
		}
	}
	
	class TestModifier extends Rule implements EventModifier {
		
		private Event event;
		
		public void executeOnEvent(Event event, Character source, Character target) {
			this.event = event;
			Queue.addAndRun(this, source, target, Condition.NOW);
		}
		
		public void executeAfterOnEvent(Event event, Character source, Character target) {
			this.event = event;
			Queue.add(this, source, target, Condition.AFTER);
		}
		
		public void subExecute(Character source, Character target, Condition condition) {
			if(event != null && event instanceof Attack) {
				Attack attack = (Attack) event;
				attack.overrideDamageBase(attack.getDamageBase() + 1);
			}
		}
	}
}
