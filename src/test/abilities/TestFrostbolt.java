package test.abilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ability.Ability;
import queue.Queue;
import roles.mage.Frostbolt;
import rules.Move;
import character.Character;
import enums.Condition;
import enums.Direction;
import enums.RoleName;
import enums.Stat;

public class TestFrostbolt {
	
	@Test
	public void TestClearByAbility() {	
		Character cody = new Character();
		cody.setRole(RoleName.MAGE, 0);
		cody.setName("Cody");
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		Character jason = new Character();
		Frostbolt frostbolt = new Frostbolt();
		boolean done = false;
		while(!done) {
			cody.resetEnergy();
			Queue.addAndRun(frostbolt, cody, jason, Condition.ACTIVATE_ABILITY);
			int healthAfterAttack = jason.attributes.getCurrentHealth();
			assertTrue(healthAfterAttack < jason.attributes.getMaxHealth());
			if(Queue.getEventSetList(Condition.ACTIVATE_ABILITY, jason).size() > 0) {
				done = true;
			}
		}
		
		Queue.addAndRun(new TestAbility(), jason, cody, Condition.ACTIVATE_ABILITY);
		assertEquals(cody.getName(), "Cody");
		
		Queue.addAndRun(new Move(Direction.DOWN, 1), jason, jason, Condition.MOVE);
		Queue.addAndRun(new Move(Direction.RIGHT, 1), jason, jason, Condition.MOVE);
		assertEquals(cody.getX(), 0);
		assertEquals(cody.getY(), 0);
		
		for(int i = 0; i<10000; i++) {
			Queue.addAndRun(new TestAbility(), jason, cody, Condition.ACTIVATE_ABILITY);
			if(cody.getName() == "Bitch") {
				i = 10000;
			}
		}
		assertEquals(cody.getName(), "Bitch");
	}
	
	class TestAbility extends Ability {
		public void subExecute(Character source, Character target, Condition condition) {
			target.setName("Bitch");
		}
	}
	
}
