package test.abilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import queue.Queue;
import roles.assassin.Rush;
import character.Character;
import character.Team;
import encounter.Encounter;
import enums.Condition;
import enums.EnergyType;
import enums.RoleName;
import enums.Stat;

public class TestRush {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.setX(4);
		cody.setY(4);
		cody.setTeam(Team.getPlayerTeam());
		cody.setIsPlayer(true);
		cody.setRole(RoleName.ASSASSIN, 0);
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		Character jason = new Character();
		jason.setX(4);
		jason.setY(6);
		jason.setTeam(Team.getEnemyTeam());
		
		Encounter encounter = Encounter.get();
		encounter.add(cody);
		encounter.add(jason);
		encounter.startEncounter();

		Queue.run(Condition.END_TURN, cody);
		Queue.addAndRun(new Rush(), cody, jason, Condition.ACTIVATE_ABILITY);
		assertTrue(jason.attributes.getCurrentHealth() < jason.attributes.getMaxHealth());
		assertEquals(cody.getY(), 5);
	}
	
}
