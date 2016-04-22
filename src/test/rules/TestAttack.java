package test.rules;

import static org.junit.Assert.*;

import org.junit.Test;

import ability.Ability;
import queue.Queue;
import rules.Attack;
import character.Character;
import enums.Condition;
import enums.Stat;

public class TestAttack {
	
	@Test
	public void TestActivate() {
		Character cody = new Character();
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		Character jason = new Character();
		Ability test = new TestAbility();
		Attack attack = new Attack(test.getDamageBase(), test.getDamageType(), test.getAttackStat());
		
		Queue.addAndRun(attack, cody, jason, Condition.ATTACK);
		int healthAfterAttack = jason.attributes.getCurrentHealth();
		assertTrue(healthAfterAttack < jason.attributes.getMaxHealth());
	}
	
	@Test
	public void TestResults() {
		Character cody = new Character();
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		Character jason = new Character();
		Ability test = new TestAbility();
		Attack attack = new Attack(test.getDamageBase(), test.getDamageType(), test.getAttackStat());
		
		assertFalse(attack.wasHit());
		
		Queue.addAndRun(attack, cody, jason, Condition.ATTACK);
		int healthAfterAttack = jason.attributes.getCurrentHealth();
		assertTrue(healthAfterAttack < jason.attributes.getMaxHealth());
		
		assertTrue(attack.wasHit());
		assertEquals(jason.attributes.getMaxHealth() - healthAfterAttack, attack.getDamageDealt());
	}
	
	class TestAbility extends Ability {
		public void subExecute(Character source, Character target, Condition condition) {
			target.setName("Bitch");
		}
	}
}
