package test.abilities;

import static org.junit.Assert.*;

import org.junit.Test;

import queue.Queue;
import roles.warlock.BloodPact;
import character.Character;
import enums.Condition;

public class TestBloodPact {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		Queue.addAndRun(new BloodPact(), cody, cody, Condition.NOW);
		
		assertEquals(cody.getMaxHealth(), cody.getHealth());
		
		Queue.run(Condition.END_TURN, cody);
		
		assertEquals(Math.round(cody.getMaxHealth() - Math.round(cody.getMaxHealth() * 0.1)), cody.getHealth());
		
		Queue.run(Condition.END_TURN, cody);
		Queue.run(Condition.END_TURN, cody);
		
		assertEquals(Math.round(cody.getMaxHealth() - 3 * Math.round(cody.getMaxHealth() * 0.1)), cody.getHealth());
		
		cody.attributes.setCurrentHealth((int) Math.round(cody.getMaxHealth() * .65));
		
		Queue.run(Condition.END_TURN, cody);
		
		assertEquals(Math.round(cody.getMaxHealth() * .65) + Math.round(cody.getMaxHealth() * 0.1), cody.getHealth());
	}
	
}
