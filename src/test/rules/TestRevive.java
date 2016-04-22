package test.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import rules.Revive;
import rules.TakeDamage;
import character.Character;
import encounter.Encounter;
import enums.Condition;
import enums.DamageType;

public class TestRevive {
	
	@Test
	public void testRevive() {
		Revive revive = new Revive();
		Character cody = new Character();
		
		Encounter encounter = Encounter.get();
		encounter.add(cody);
		
		Queue.add(revive, cody, cody, Condition.DIE);
		Queue.addAndRun(new TakeDamage(DamageType.NEURO, 10000), cody, cody, Condition.TAKE_DAMAGE);
		assertEquals(cody.attributes.getCurrentHealth(), cody.attributes.getMaxHealth()/2);
		assertEquals(encounter.getCharacters().size(), 1);
		Queue.addAndRun(new TakeDamage(DamageType.NEURO, 10000), cody, cody, Condition.TAKE_DAMAGE);
		assertEquals(cody.attributes.getCurrentHealth(), 0);
		assertEquals(encounter.getCharacters().size(), 0);
	}
}