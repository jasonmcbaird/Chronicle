package test.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import rules.Convert;
import character.Character;
import character.Team;
import enums.Condition;

public class TestConvert {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.setTeam(Team.getPlayerTeam());
		Character jason = new Character();
		jason.setTeam(Team.getEnemyTeam());

		Queue.addAndRun(new Convert(cody.getTeam()), cody, jason, Condition.ACTIVATE_ABILITY);
		assertEquals(jason.getTeam(), Team.getPlayerTeam());
	}
	
}
