package test.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import rules.BurnStamina;
import character.Character;
import enums.Condition;
import enums.Stat;

public class TestBurnStamina {
	
	@Test
	public void testActivate() {
		Character cody = new Character();
		for(Stat stat: Stat.getMajorStats()) {
			int initial = cody.attributes.getStat(stat);
			BurnStamina burn = new BurnStamina(1);
			
			Queue.addAndRun(burn, cody, cody, Condition.NOW);
			
			assertEquals(initial - 1, cody.attributes.getStat(stat));
			
			Queue.run(Condition.END_ENCOUNTER, cody);
			assertEquals(initial, cody.attributes.getStat(stat));
		}
		
	}
}
