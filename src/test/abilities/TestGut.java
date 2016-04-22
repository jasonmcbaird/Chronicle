package test.abilities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import queue.Queue;
import roles.berserker.Gut;
import character.Character;
import enums.Condition;
import enums.Stat;

public class TestGut {
	
	@Test
	public void TestActivate() {
		Character cody = new Character();
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		Character jason = new Character();
		Gut gut = new Gut();
		
		Queue.addAndRun(gut, cody, jason, Condition.ACTIVATE_ABILITY);
		int healthAfterAttack = jason.attributes.getCurrentHealth();
		assertTrue(healthAfterAttack < jason.attributes.getMaxHealth());
		
		Queue.run(Condition.START_TURN, jason);
		int healthAfterFirstBleed = jason.attributes.getCurrentHealth();
		assertTrue(healthAfterFirstBleed < healthAfterAttack);
		
		Queue.run(Condition.HEAL, jason);
		Queue.run(Condition.START_TURN, jason);
		assertTrue(healthAfterFirstBleed == jason.attributes.getCurrentHealth());
		// Logger.get().print("Max health: " + jason.attributes.getMaxHealth() + ", health after attack: " + healthAfterAttack + ", health after first bleed: " + healthAfterFirstBleed + ", health after second bleed: " + jason.attributes.getCurrentHealth());
	}
	
}
