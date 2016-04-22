package test.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Event;
import queue.Queue;
import rules.ExecuteIfFullEnergy;
import character.Character;
import enums.Condition;
import enums.EnergyType;
import enums.RoleName;

public class TestExecuteIfFullEnergy {
	
	@Test
	public void testMax() {
		TestEvent testEvent = new TestEvent();
		Character cody = new Character();
		cody.setRole(RoleName.MAGE, 0);
		cody.setName("Cody");
		ExecuteIfFullEnergy primer = new ExecuteIfFullEnergy(testEvent, EnergyType.MANA);
		primer.setPersistent(true);
		Queue.add(primer, cody, cody, Condition.CHANGE_ENERGY);
		assertEquals(cody.getName(), "Cody");
		
		cody.changeEnergy(EnergyType.MANA, -cody.getEnergy(EnergyType.MANA));
		assertEquals(cody.getEnergy(EnergyType.MANA), 0);
		Queue.run(Condition.CHANGE_ENERGY, cody);
		assertEquals(cody.getName(), "Cody");
		
		cody.resetEnergy(EnergyType.MANA);
		Queue.run(Condition.CHANGE_ENERGY, cody);
		assertEquals(cody.getName(), "Bitch");
	}
	
	class TestEvent implements Event {
		
		public int getPriority() {
			return 0;
		}
		
		public void execute(Character source, Character target, Condition condition) {
			source.setName("Bitch");
		}
	}
}
