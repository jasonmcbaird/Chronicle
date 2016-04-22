package test.abilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import roles.mage.ManaTide;
import rules.ChangeEnergy;
import character.Character;
import enums.Condition;
import enums.EnergyType;
import enums.RoleName;

public class TestManaTide {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.setRole(RoleName.MAGE, 0);
		Queue.addAndRun(new ChangeEnergy(EnergyType.MANA, -10), cody, cody, Condition.CHANGE_ENERGY);
		int beforeEnergy = cody.getEnergy(EnergyType.MANA);
		ManaTide manaTide = new ManaTide();
		Queue.addAndRun(manaTide, cody, cody, Condition.ACTIVATE_ABILITY);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.getEnergy(EnergyType.MANA), beforeEnergy);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.getEnergy(EnergyType.MANA), beforeEnergy + 1);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.getEnergy(EnergyType.MANA), beforeEnergy + 3);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.getEnergy(EnergyType.MANA), beforeEnergy + 6);
		Queue.addAndRun(new ChangeEnergy(EnergyType.MANA, -6), cody, cody, Condition.CHANGE_ENERGY);
		Queue.run(Condition.START_TURN, cody);
		assertEquals(cody.getEnergy(EnergyType.MANA), beforeEnergy + 3);
	}
	
}
