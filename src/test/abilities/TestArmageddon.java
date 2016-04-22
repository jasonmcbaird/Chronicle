package test.abilities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import queue.Queue;
import roles.mage.Armageddon;
import character.Character;
import character.Team;
import encounter.Encounter;
import enums.Condition;
import enums.Direction;
import enums.RoleName;
import enums.Stat;

public class TestArmageddon {
	
	@Test
	public void TestActivate() {
		Queue.clear();
		Character cody = new Character();
		cody.setRole(RoleName.MAGE, 0);
		cody.setTeam(Team.getPlayerTeam());
		cody.setName("Cody");
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		
		Character jason = new Character();
		jason.setTeam(Team.getEnemyTeam());
		jason.setX(5);
		jason.setY(5);
		jason.setName("Jason");
		Character jody = new Character();
		jody.setTeam(Team.getEnemyTeam());
		jody.setX(5);
		jody.setY(7);
		jody.setName("Jody");
		Character cheyenne = new Character();
		cheyenne.setTeam(Team.getEnemyTeam());
		cheyenne.setX(5);
		cheyenne.setY(8);
		cheyenne.setName("Cheyenne");
		Character colin = new Character();
		colin.setTeam(Team.getEnemyTeam());
		colin.setX(10);
		colin.setY(10);
		colin.setName("Colin");
		Character drew = new Character();
		drew.setTeam(Team.getEnemyTeam());
		drew.setX(15);
		drew.setY(15);
		drew.setName("Drew");
		
		Encounter encounter = new Encounter();
		encounter.add(cody);
		encounter.add(jason);
		encounter.add(jody);
		encounter.add(cheyenne);
		
		Queue.addAndRun(new Armageddon(), cody, jason, Condition.ACTIVATE_ABILITY);
		
		int jasonHealthAfterFirstHit = jason.attributes.getCurrentHealth();
		int jodyHealthAfterFirstHit = jody.attributes.getCurrentHealth();
		assertTrue(jasonHealthAfterFirstHit < jason.attributes.getMaxHealth());
		assertTrue(jodyHealthAfterFirstHit == jody.attributes.getMaxHealth());
		
		jody.move(Direction.UP, 1);
		Queue.run(Condition.START_TURN, cody);
		if(jason.attributes.getCurrentHealth() < jasonHealthAfterFirstHit) {
			assertTrue(jody.attributes.getCurrentHealth() < jodyHealthAfterFirstHit);
		} else {
			assertTrue(cheyenne.attributes.getCurrentHealth() < cheyenne.attributes.getMaxHealth());
			assertTrue(jody.attributes.getCurrentHealth() == jodyHealthAfterFirstHit);
		}
		
		Queue.run(Condition.START_TURN, cody);
		int jasonHealthAfterLastHit = jason.attributes.getCurrentHealth();
		assertTrue(jasonHealthAfterLastHit < jasonHealthAfterFirstHit);
		int jodyHealthAfterLastHit = jody.attributes.getCurrentHealth();
		assertTrue(jodyHealthAfterLastHit < jodyHealthAfterFirstHit);
		int cheyenneHealthAfterLastHit = cheyenne.attributes.getCurrentHealth();
		assertTrue(cheyenneHealthAfterLastHit < cheyenne.attributes.getMaxHealth());
		
		encounter.add(colin);
		Queue.run(Condition.START_TURN, cody);
		int colinHealthAfterLastHit = colin.attributes.getCurrentHealth();
		assertTrue(colinHealthAfterLastHit < colin.attributes.getMaxHealth());
		
		encounter.add(drew);
		Queue.run(Condition.START_TURN, cody);
		assertTrue(jason.attributes.getCurrentHealth() == jasonHealthAfterLastHit);
		assertTrue(jody.attributes.getCurrentHealth() == jodyHealthAfterLastHit);
		assertTrue(cheyenne.attributes.getCurrentHealth() == cheyenneHealthAfterLastHit);
		assertTrue(colin.attributes.getCurrentHealth() == colinHealthAfterLastHit);
		assertTrue(drew.attributes.getCurrentHealth() == drew.attributes.getMaxHealth());
	}
	
}
