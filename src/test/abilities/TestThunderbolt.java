package test.abilities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import queue.Queue;
import roles.mage.Thunderbolt;
import character.Character;
import character.Team;
import encounter.Encounter;
import enums.Condition;
import enums.Direction;
import enums.RoleName;
import enums.Stat;

public class TestThunderbolt {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.setRole(RoleName.MAGE, 0);
		cody.setTeam(Team.getPlayerTeam());
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		Character jason = new Character();
		jason.setTeam(Team.getEnemyTeam());
		Character jody = new Character();
		jody.setTeam(Team.getEnemyTeam());
		
		Encounter encounter = Encounter.get();
		encounter.add(cody);
		encounter.add(jason);
		encounter.add(jody);
		
		Queue.addAndRun(new Thunderbolt(), cody, jason, Condition.ACTIVATE_ABILITY);
		
		int jasonHealthAfterFirstHit = jason.attributes.getCurrentHealth();
		int jodyHealthAfterFirstHit = jody.attributes.getCurrentHealth();
		assertTrue(jasonHealthAfterFirstHit < jason.attributes.getMaxHealth());
		assertTrue(jodyHealthAfterFirstHit < jody.attributes.getMaxHealth());
		
		jody.move(Direction.DOWN, 4);
		Queue.addAndRun(new Thunderbolt(), cody, jason, Condition.ACTIVATE_ABILITY);
		
		assertTrue(jason.attributes.getCurrentHealth() < jasonHealthAfterFirstHit);
		assertTrue(jody.attributes.getCurrentHealth() == jodyHealthAfterFirstHit);
	}
	
}
