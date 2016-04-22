package test.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import rules.Berserk;
import rules.ChangeEnergy;
import character.Character;
import enums.Condition;
import enums.EnergyType;
import enums.RoleName;
import enums.Stat;

public class TestBerserk {
	
	@Test
	public void TestActivate() {
		Character cody = new Character();
		cody.setRole(RoleName.BERSERKER, 0);
		cody.setIsPlayer(true);
		int strength = cody.attributes.getStat(Stat.STRENGTH);
		Berserk berserk = new Berserk();
		
		Queue.add(berserk, cody, cody, Condition.CHANGE_ENERGY);
		
		Queue.addAndRun(new ChangeEnergy(EnergyType.RAGE, 10), cody, cody, Condition.CHANGE_ENERGY);
		assertEquals(cody.attributes.getStat(Stat.STRENGTH), strength + 4);
		assertEquals(cody.isPlayer(), false);
		
		Queue.add(new ChangeEnergy(EnergyType.RAGE, -5), cody, cody, Condition.CHANGE_ENERGY);
		Queue.add(new ChangeEnergy(EnergyType.RAGE, 2), cody, cody, Condition.CHANGE_ENERGY);
		Queue.run(Condition.CHANGE_ENERGY, cody);
		assertEquals(cody.getEnergy(EnergyType.RAGE), 5);
		Queue.addAndRun(new ChangeEnergy(EnergyType.RAGE, -5), cody, cody, Condition.CHANGE_ENERGY);
		
		assertEquals(cody.attributes.getStat(Stat.STRENGTH), strength);
		assertEquals(cody.isPlayer(), true);
	}
}
