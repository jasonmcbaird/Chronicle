package test.abilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ability.Ability;
import queue.Queue;
import roles.warrior.Bash;
import character.Character;
import enums.Condition;
import enums.Stat;

public class TestShieldBash {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.setName("Cody");
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		Character jason = new Character();
		Bash shieldBash = new Bash();
		boolean done = false;
		while(!done) {
			Queue.add(shieldBash, cody, jason, Condition.ACTIVATE_ABILITY);
			Queue.run(Condition.ACTIVATE_ABILITY, cody);
			int healthAfterAttack = jason.attributes.getCurrentHealth();
			assertTrue(healthAfterAttack < jason.attributes.getMaxHealth());
			if(Queue.getEventSetList(Condition.ACTIVATE_ABILITY, jason).size() > 0) {
				done = true;
			}
		}
		
		Queue.add(new TestAbility(), jason, cody, Condition.ACTIVATE_ABILITY);
		Queue.run(Condition.ACTIVATE_ABILITY, jason);
		assertEquals(cody.getName(), "Cody");
		
		Queue.add(new TestAbility(), jason, cody, Condition.ACTIVATE_ABILITY);
		Queue.run(Condition.ACTIVATE_ABILITY, jason);
		assertEquals(cody.getName(), "Bitch");
	}
	
	class TestAbility extends Ability {
		public void subExecute(Character source, Character target, Condition condition) {
			target.setName("Bitch");
		}
	}
	
}
