package test.abilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import roles.assassin.Patience;
import character.Character;
import enums.Condition;
import enums.EnergyType;
import enums.RoleName;

public class TestPatience {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.setRole(RoleName.ASSASSIN, 0);
		int beforeEnergy = cody.getEnergy(EnergyType.PATIENCE);
		Patience patience = new Patience();
		Queue.addAndRun(patience, cody, cody, Condition.ACTIVATE_ABILITY);
		Queue.run(Condition.END_TURN, cody);
		assertEquals(cody.getEnergy(EnergyType.PATIENCE), beforeEnergy + 1);
		Queue.run(Condition.ATTACK, cody);
		assertEquals(cody.getEnergy(EnergyType.PATIENCE), beforeEnergy);
		Queue.run(Condition.END_TURN, cody);
		assertEquals(cody.getEnergy(EnergyType.PATIENCE), beforeEnergy);
		Queue.run(Condition.END_TURN, cody);
		assertEquals(cody.getEnergy(EnergyType.PATIENCE), beforeEnergy + 1);
	}
	
}
