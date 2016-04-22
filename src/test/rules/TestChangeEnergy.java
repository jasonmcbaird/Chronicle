package test.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import queue.Queue;
import rules.ChangeEnergy;
import character.Character;
import enums.Condition;
import enums.EnergyType;
import enums.RoleName;

public class TestChangeEnergy {
	
	@Test
	public void TestActivate() {
		Character cody = new Character();
		cody.setRole(RoleName.BERSERKER, 0);
		ChangeEnergy gainEnergy = new ChangeEnergy(EnergyType.RAGE, 2);
		assertEquals(cody.getEnergy(EnergyType.RAGE), 0);
		
		Queue.addAndRun(gainEnergy, cody, cody, Condition.START_TURN);
		
		assertEquals(cody.getEnergy(EnergyType.RAGE), 2);
	}
	
	@Test
	public void TestClear() {
		Character cody = new Character();
		cody.setRole(RoleName.BERSERKER, 0);
		ChangeEnergy gainEnergy = new ChangeEnergy(EnergyType.RAGE, 7);
		Queue.addAndRun(gainEnergy, cody, cody, Condition.START_TURN);
		assertEquals(cody.getEnergy(EnergyType.RAGE), 7);
		
		ChangeEnergy clearEnergy = new ChangeEnergy(EnergyType.RAGE, true);
		Queue.addAndRun(clearEnergy, cody, cody, Condition.START_TURN);
		
		assertEquals(cody.getEnergy(EnergyType.RAGE), 0);
	}
	
	@Test
	public void TestRandomActivate() {
		Character cody = new Character();
		cody.setRole(RoleName.BERSERKER, 0);
		ChangeEnergy gainEnergy = new ChangeEnergy(EnergyType.RAGE, 3, 9);
				
		for(int i = 0; i<1000; i++) {
			cody.resetEnergy();
			
			Queue.addAndRun(gainEnergy, cody, cody, Condition.START_TURN);
			assertTrue(cody.getEnergy(EnergyType.RAGE) >= 3 && cody.getEnergy(EnergyType.RAGE) <= 9);
		}
	}
	
	@Test
	public void TestPersistent() {
		Character cody = new Character();
		cody.setRole(RoleName.BERSERKER, 0);
		ChangeEnergy regen = new ChangeEnergy(EnergyType.RAGE, 2);
		regen.setPersistent(true);
		assertEquals(cody.getEnergy(EnergyType.RAGE), 0);
		
		Queue.addAndRun(regen, cody, cody, Condition.START_TURN);
		assertEquals(cody.getEnergy(EnergyType.RAGE), 2);
		
		Queue.addAndRun(regen, cody, cody, Condition.START_TURN);
		assertEquals(cody.getEnergy(EnergyType.RAGE), 4);
	}
	
	@Test
	public void TestRandomPersistent() {
		Character cody = new Character();
		cody.setRole(RoleName.BERSERKER, 0);
		ChangeEnergy regen = new ChangeEnergy(EnergyType.RAGE, 3, 9);
		regen.setPersistent(true);
		Queue.add(regen, cody, cody, Condition.START_TURN);
		
		for(int i = 0; i<1000; i++) {
			cody.resetEnergy();

			Queue.run(Condition.START_TURN, cody);
			assertTrue(cody.getEnergy(EnergyType.RAGE) >= 3 && cody.getEnergy(EnergyType.RAGE) <= 9);
		}
	}
}
