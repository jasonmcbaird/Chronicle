package test.abilities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import queue.Queue;
import roles.berserker.Fury;
import character.Character;
import enums.Condition;
import enums.EnergyType;
import enums.RoleName;

public class TestFury {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.setRole(RoleName.BERSERKER, 0);
		Fury fury = new Fury();
		Queue.addAndRun(fury, cody, cody, Condition.ACTIVATE_ABILITY);
		Queue.run(Condition.TAKE_DAMAGE, cody);
		assertTrue(cody.getEnergy(EnergyType.RAGE) == 2);
		Queue.run(Condition.TAKE_DAMAGE, cody);
		assertTrue(cody.getEnergy(EnergyType.RAGE) == 4);
	}
	
}
