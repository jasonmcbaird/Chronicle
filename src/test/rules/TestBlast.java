package test.rules;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ability.Ability;
import queue.Queue;
import rules.Blast;
import character.Character;
import character.Team;
import encounter.Encounter;
import enums.Condition;
import enums.Direction;
import enums.RoleName;
import enums.Stat;

public class TestBlast {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.setRole(RoleName.MAGE, 0);
		cody.setTeam(Team.getPlayerTeam());
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		Character jason = new Character();
		jason.setTeam(Team.getEnemyTeam());
		jason.setX(4);
		jason.setY(4);
		Character jody = new Character();
		jody.setTeam(Team.getEnemyTeam());
		jody.setX(4);
		jody.setY(5);
		Character cheyenne = new Character();
		cheyenne.setTeam(Team.getEnemyTeam());
		cheyenne.setX(5);
		cheyenne.setY(4);
		
		Encounter encounter = Encounter.get();
		encounter.add(cody);
		encounter.add(jason);
		encounter.add(jody);
		encounter.add(cheyenne);
		
		Queue.addAndRun(new Blast(new TestAbility(), 2), cody, jason, Condition.ACTIVATE_ABILITY);
		
		int jodyHealthAfterFirstHit = jody.attributes.getCurrentHealth();
		assertTrue(jodyHealthAfterFirstHit < jody.attributes.getMaxHealth());
		
		int cheyenneHealthAfterFirstHit = cheyenne.attributes.getCurrentHealth();
		assertTrue(cheyenneHealthAfterFirstHit < cheyenne.attributes.getMaxHealth());
		
		jody.move(Direction.DOWN, 2);
		Queue.addAndRun(new Blast(new TestAbility(), 2), cody, jason, Condition.ACTIVATE_ABILITY);
		
		assertTrue(jody.attributes.getCurrentHealth() == jodyHealthAfterFirstHit);
		assertTrue(cheyenne.attributes.getCurrentHealth() < cheyenneHealthAfterFirstHit);
	}
	
	class TestAbility extends Ability {
		public void subExecute(Character source, Character target, Condition condition) {
			target.setName("Bitch");
		}
	}
	
}
