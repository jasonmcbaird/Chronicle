package test.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import rules.Bleed;
import character.Character;
import enums.Condition;

public class TestBleed {
	
	@Test
	public void testBleedExecute() {
		Bleed bleed = new Bleed(2);
		Character cody = new Character();
		int health = cody.attributes.getCurrentHealth();
		bleed.execute(cody, cody, Condition.START_TURN);
		assertEquals(cody.attributes.getCurrentHealth(), health-2);
		
		Bleed bleed5 = new Bleed(5);
		bleed5.execute(cody, cody, Condition.START_TURN);
		assertEquals(cody.attributes.getCurrentHealth(), health-7);
	}
	
	@Test
	public void testBleedInQueue() {
		int health;
		Bleed bleed = new Bleed(2);
		Character cody = new Character();
		health = cody.attributes.getCurrentHealth();
		Queue.add(bleed, cody, cody, Condition.START_TURN);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.attributes.getCurrentHealth(), health-2);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.attributes.getCurrentHealth(), health-4);
	}
}