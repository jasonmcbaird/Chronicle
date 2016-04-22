package test.rules;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ability.Ability;
import queue.Queue;
import rules.Attack;
import rules.ModifyAttack;
import character.Character;
import enums.Condition;

public class TestModifyAttack {
	
	@Test
	public void testExecute() {
		Character cody = new Character();
		Ability test = new TestAbility();
		Attack attack = new Attack(test.getDamageBase(), test.getDamageType(), test.getAttackStat());
		int originalBase = attack.getDamageBase();
		
		ModifyAttack modify = new ModifyAttack(attack, (float) 1.2);
		
		Queue.addAndRun(modify, cody, cody, Condition.NOW);
		
		assertTrue(attack.getDamageBase() == originalBase * 1.2);
	}
	
	class TestAbility extends Ability {
		public void subExecute(Character source, Character target, Condition condition) {
			target.setName("Bitch");
		}
	}
	
}
