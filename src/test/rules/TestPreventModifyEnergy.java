package test.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import rules.ChangeEnergy;
import rules.PreventModifyEnergy;
import character.Character;
import enums.Condition;
import enums.EnergyType;
import enums.RoleName;
import enums.Sign;

public class TestPreventModifyEnergy {
	
	@Test
	public void TestBoth() {
		Character cody = new Character();
		cody.setRole(RoleName.BERSERKER, 0);
		Queue.addAndRun(new ChangeEnergy(EnergyType.RAGE, 2), cody, cody, Condition.CHANGE_ENERGY);
		assertEquals(cody.getEnergy(EnergyType.RAGE), 2);
		
		ChangeEnergy modify = new ChangeEnergy(EnergyType.RAGE, 2);
		PreventModifyEnergy prevent = new PreventModifyEnergy(EnergyType.RAGE);
		prevent.executeOnEvent(modify, cody, cody);

		Queue.add(prevent, cody, cody, Condition.CHANGE_ENERGY);
		Queue.add(modify, cody, cody, Condition.CHANGE_ENERGY);
		Queue.run(Condition.CHANGE_ENERGY, cody);
		
		assertEquals(cody.getEnergy(EnergyType.RAGE), 2);
		
		modify = new ChangeEnergy(EnergyType.RAGE, -2);
		prevent = new PreventModifyEnergy(EnergyType.RAGE);
		prevent.executeOnEvent(modify, cody, cody);
		
		Queue.add(prevent, cody, cody, Condition.CHANGE_ENERGY);
		Queue.add(modify, cody, cody, Condition.CHANGE_ENERGY);

		Queue.run(Condition.CHANGE_ENERGY, cody);
		
		assertEquals(cody.getEnergy(EnergyType.RAGE), 2);
	}
	
	@Test
	public void TestPositive() {
		Character cody = new Character();
		cody.setRole(RoleName.BERSERKER, 0);
		Queue.addAndRun(new ChangeEnergy(EnergyType.RAGE, 2), cody, cody, Condition.CHANGE_ENERGY);
		assertEquals(cody.getEnergy(EnergyType.RAGE), 2);
		
		ChangeEnergy modify = new ChangeEnergy(EnergyType.RAGE, 2);
		PreventModifyEnergy prevent = new PreventModifyEnergy(EnergyType.RAGE, Sign.POSITIVE);
		prevent.executeOnEvent(modify, cody, cody);

		Queue.add(prevent, cody, cody, Condition.CHANGE_ENERGY);
		Queue.add(modify, cody, cody, Condition.CHANGE_ENERGY);
		Queue.run(Condition.CHANGE_ENERGY, cody);
		
		assertEquals(cody.getEnergy(EnergyType.RAGE), 2);
		
		modify = new ChangeEnergy(EnergyType.RAGE, -2);
		prevent = new PreventModifyEnergy(EnergyType.RAGE, Sign.POSITIVE);
		prevent.executeOnEvent(modify, cody, cody);
		
		Queue.add(modify, cody, cody, Condition.CHANGE_ENERGY);
		Queue.add(prevent, cody, cody, Condition.CHANGE_ENERGY);

		Queue.run(Condition.CHANGE_ENERGY, cody);
		
		assertEquals(cody.getEnergy(EnergyType.RAGE), 0);
	}
	
	@Test
	public void TestNegative() {
		Character cody = new Character();
		cody.setRole(RoleName.BERSERKER, 0);
		Queue.addAndRun(new ChangeEnergy(EnergyType.RAGE, 2), cody, cody, Condition.CHANGE_ENERGY);
		assertEquals(cody.getEnergy(EnergyType.RAGE), 2);
		
		ChangeEnergy modify = new ChangeEnergy(EnergyType.RAGE, 2);
		PreventModifyEnergy prevent = new PreventModifyEnergy(EnergyType.RAGE, Sign.NEGATIVE);
		prevent.executeOnEvent(modify, cody, cody);

		Queue.add(prevent, cody, cody, Condition.CHANGE_ENERGY);
		Queue.add(modify, cody, cody, Condition.CHANGE_ENERGY);
		Queue.run(Condition.CHANGE_ENERGY, cody);
		
		assertEquals(cody.getEnergy(EnergyType.RAGE), 4);
		
		modify = new ChangeEnergy(EnergyType.RAGE, -2);
		prevent = new PreventModifyEnergy(EnergyType.RAGE, Sign.NEGATIVE);
		prevent.executeOnEvent(modify, cody, cody);
		
		Queue.add(prevent, cody, cody, Condition.CHANGE_ENERGY);
		Queue.add(modify, cody, cody, Condition.CHANGE_ENERGY);

		Queue.run(Condition.CHANGE_ENERGY, cody);
		
		assertEquals(cody.getEnergy(EnergyType.RAGE), 4);
	}
}
