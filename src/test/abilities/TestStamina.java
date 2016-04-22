package test.abilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import roles.warrior.Stamina;
import rules.ChangeEnergy;
import character.Character;
import character.Team;
import enums.Condition;
import enums.EnergyType;
import enums.RoleName;
import enums.Stat;

public class TestStamina {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.setTeam(Team.getPlayerTeam());
		cody.setIsPlayer(true);
		cody.setRole(RoleName.WARRIOR, 0);
		int initialStrength = cody.attributes.getStat(Stat.STRENGTH);
		
		Queue.addAndRun(new Stamina(), cody, cody, Condition.NOW);
		
		cody.changeEnergy(EnergyType.STAMINA, -cody.getEnergy(EnergyType.STAMINA));
		assertEquals(0, cody.getEnergy(EnergyType.STAMINA));
				
		Queue.run(Condition.END_TURN, cody);
		assertEquals(2, cody.getEnergy(EnergyType.STAMINA));
		
		Queue.addAndRun(new ChangeEnergy(EnergyType.STAMINA, -1), cody, cody, Condition.NOW);
		assertEquals(1, cody.getEnergy(EnergyType.STAMINA));
		
		Queue.run(Condition.END_TURN, cody);
		assertEquals(1, cody.getEnergy(EnergyType.STAMINA));
				
		Queue.addAndRun(new ChangeEnergy(EnergyType.STAMINA, -3), cody, cody, Condition.NOW);
		assertEquals(initialStrength - 2, cody.attributes.getStat(Stat.STRENGTH));
	}
}
