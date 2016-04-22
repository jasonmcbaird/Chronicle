package test.abilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import queue.Queue;
import roles.psion.Dominate;
import character.Character;
import character.Team;
import enums.Condition;

public class TestDominate {
	
	@Test
	public void TestActivate() {
		Character cody = new Character();
		cody.setIsPlayer(true);
		cody.setTeam(Team.getPlayerTeam());
		Character jason = new Character();
		jason.setIsPlayer(false);
		jason.setTeam(Team.getEnemyTeam());
		
		Queue.addAndRun(new Dominate(), cody, jason, Condition.ACTIVATE_ABILITY);
		assertTrue(jason.isPlayer());
		assertEquals(jason.getTeam(), Team.getPlayerTeam());
		
		for(int i = 0; i < 3; i++) {
			Queue.run(Condition.END_TURN, jason);
		}
		assertFalse(jason.isPlayer());
		assertEquals(jason.getTeam(), Team.getEnemyTeam());
	}
}
