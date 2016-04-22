package test.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import rules.TakeDamage;
import character.Character;
import character.CharacterBuilder;
import character.Team;
import encounter.Encounter;
import enums.Condition;
import enums.DamageType;

public class TestXP {
	
	@Test
	public void testKill() {
		Character cody = CharacterBuilder.makeCharacter("Barbarian", 1);
		cody.setName("Cody");
		cody.setTeam(Team.getPlayerTeam());
		Character jason = CharacterBuilder.makeCharacter("Rogue", 1);
		jason.setName("Jason");
		
		
		Encounter encounter = new Encounter();
		encounter.add(cody);
		encounter.add(jason);
		encounter.startEncounter();
		
		assertEquals(0, cody.getCurrentGenLevelExp());
		
		Queue.addAndRun(new TakeDamage(DamageType.STAB, 50), cody, jason, Condition.TAKE_DAMAGE);
		
		assertEquals(8, cody.getCurrentGenLevelExp());
	}
	
	@Test
	public void testBystander() {
		Character cody = CharacterBuilder.makeCharacter("Barbarian", 1);
		cody.setName("Cody");
		cody.setTeam(Team.getPlayerTeam());
		Character jason = CharacterBuilder.makeCharacter("Rogue", 1);
		jason.setName("Jason");
		
		
		Encounter encounter = new Encounter();
		encounter.add(cody);
		encounter.add(jason);
		encounter.startEncounter();
		
		assertEquals(0, cody.getCurrentGenLevelExp());
		
		Queue.addAndRun(new TakeDamage(DamageType.STAB, 50), jason, jason, Condition.TAKE_DAMAGE);
		
		assertEquals(4, cody.getCurrentGenLevelExp());
	}
	
	@Test
	public void testLevelUp() {
		Character cody = CharacterBuilder.makeCharacter("Barbarian", 1);
		cody.setName("Cody");
		cody.setTeam(Team.getPlayerTeam());
		
		Encounter encounter = new Encounter();
		encounter.add(cody);
		encounter.startEncounter();
		
		assertEquals(0, cody.getCurrentGenLevelExp());
		assertEquals(4, cody.getGeneralLevel());
		
		for(int i = 1; i <= 4; i++) {
			Character drew = CharacterBuilder.makeCharacter("Rogue", 1);
			encounter.add(drew);
			
			Queue.addAndRun(new TakeDamage(DamageType.STAB, 50), cody, drew, Condition.TAKE_DAMAGE);
		}
		
		assertEquals(0, cody.getCurrentGenLevelExp());
		assertEquals(5, cody.getGeneralLevel());
	}
}
