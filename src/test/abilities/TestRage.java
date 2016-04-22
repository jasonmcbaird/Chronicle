package test.abilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import queue.Queue;
import roles.berserker.Rage;
import character.Character;
import enums.Condition;
import enums.EnergyType;
import enums.RoleName;

public class TestRage {
	
	@Test
	public void TestActivate() {
		Character cody = new Character();
		cody.setRole(RoleName.BERSERKER, 0);
		Rage rage = new Rage();
		assertEquals(cody.getEnergy(EnergyType.RAGE), 0);
		
		Queue.add(rage, cody, cody, Condition.ACTIVATE_ABILITY);
		Queue.run(Condition.ACTIVATE_ABILITY, cody);
		Queue.run(Condition.ATTACK, cody);
		assertTrue(cody.getEnergy(EnergyType.RAGE) >= 2 && cody.getEnergy(EnergyType.RAGE) <= 4 );
		
		Queue.run(Condition.ATTACK, cody);
		Queue.run(Condition.ATTACK, cody);
		Queue.run(Condition.ATTACK, cody);
		Queue.run(Condition.ATTACK, cody);
		
		assertEquals(cody.getEnergy(EnergyType.RAGE), 10);
		
		Queue.run(Condition.START_TURN, cody);
		Queue.run(Condition.ATTACK, cody);
		Queue.run(Condition.END_TURN, cody);
		assertEquals(cody.getEnergy(EnergyType.RAGE), 8);
		
		Queue.run(Condition.START_TURN, cody);
		Queue.run(Condition.END_TURN, cody);
		assertEquals(cody.getEnergy(EnergyType.RAGE), 4);
		
		Queue.run(Condition.START_TURN, cody);
		Queue.run(Condition.END_TURN, cody);
		assertEquals(cody.getEnergy(EnergyType.RAGE), 0);
		
		Queue.run(Condition.START_TURN, cody);
		Queue.run(Condition.ATTACK, cody);
		Queue.run(Condition.END_TURN, cody);
		assertTrue(cody.getEnergy(EnergyType.RAGE) >= 2 && cody.getEnergy(EnergyType.RAGE) <= 4 );
	}
}
