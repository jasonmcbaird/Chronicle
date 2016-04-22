package test.abilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import roles.mage.Innovation;
import rules.ChangeEnergy;
import character.Character;
import enums.Condition;
import enums.EnergyType;
import enums.RoleName;

public class TestInnovatin {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.setRole(RoleName.MAGE, 0);
		Queue.addAndRun(new ChangeEnergy(EnergyType.MANA, -10), cody, cody, Condition.CHANGE_ENERGY);
		int beforeEnergy = cody.getEnergy(EnergyType.MANA);
		Innovation innovation = new Innovation();
		Queue.addAndRun(innovation, cody, cody, Condition.ACTIVATE_ABILITY);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.getEnergy(EnergyType.MANA), beforeEnergy + 2);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.getEnergy(EnergyType.MANA), beforeEnergy + 4);
	}
	
}
