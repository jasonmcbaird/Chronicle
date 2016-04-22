package test.abilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import rules.ExecuteOnEventType;
import rules.SpreadDamage;
import rules.TakeDamage;
import utilities.Logger;
import character.Character;
import enums.Condition;
import enums.DamageType;

public class TestSpreadDamage {
	
	@Test
	public void TestExecute() {
		Character cody = new Character();
		cody.setName("Cody");
		
		Character jason = new Character();
		jason.setName("Jason");
		
		SpreadDamage spread = new SpreadDamage(cody, 40);
		Logger.setSeverity(-1);
		
		Character zombie = new Character();
		zombie.setName("Zombie");
		
		ExecuteOnEventType executeOn = new ExecuteOnEventType(TakeDamage.class, spread);
		
		Queue.add(executeOn, zombie, zombie, Condition.TAKE_DAMAGE);
		
		Queue.addAndRun(new TakeDamage(DamageType.SMASH, 5), jason, zombie, Condition.TAKE_DAMAGE);
		assertEquals(cody.getHealth(), cody.getMaxHealth() - 2);
	}

}
