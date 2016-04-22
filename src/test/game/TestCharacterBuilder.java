package test.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import character.Character;
import character.CharacterBuilder;
import enums.Stat;

public class TestCharacterBuilder {
	
	@Test
	public void testClasses() {
		Character cody = CharacterBuilder.makeCharacter("Purger", 3);
		assertEquals(cody.getRole(0).getName(), "Purger");
	}
	
	@Test
	public void testHeroes() {
		Character cody = CharacterBuilder.makeCharacter("Barbarian", 1);
		assertEquals(cody.getRoles().size(), 1);
		assertEquals(cody.getRole(0).getName(), "Berserker");
		
		cody = CharacterBuilder.makeCharacter("Barbarian", 4);
		assertEquals(cody.getRoles().size(), 2);
		assertEquals(cody.getRole(0).getName(), "Berserker");
		assertEquals(cody.getRole(1).getName(), "Warrior");
		
		cody = CharacterBuilder.makeCharacter("Barbarian", 8);
		assertEquals(cody.getRoles().size(), 3);
		assertEquals(cody.getRole(0).getName(), "Berserker");
		assertEquals(cody.getRole(1).getName(), "Warrior");
		assertEquals(cody.getRole(2).getName(), "Sylvan");
		
		cody = CharacterBuilder.makeCharacter("Barbarian", 10);
		assertEquals(cody.getRoles().size(), 4);
		assertEquals(cody.getRole(0).getName(), "Berserker");
		assertEquals(cody.getRole(1).getName(), "Warrior");
		assertEquals(cody.getRole(2).getName(), "Sylvan");
		assertEquals(cody.getRole(3).getName(), "Ranger");
	}
	
	@Test
	public void testStats() {
		Character cody = CharacterBuilder.makeCharacter("Purger", 2);
		assertEquals(cody.attributes.getStat(Stat.TOUGHNESS), 8);
		assertEquals(cody.attributes.getStat(Stat.STRENGTH) + cody.attributes.getStat(Stat.WILL), 13);
		assertEquals(cody.attributes.getStat(Stat.AGILITY) + cody.attributes.getStat(Stat.INTELLECT), 11);
		assertEquals(cody.attributes.getStat(Stat.INTUITION), 5);
		assertEquals(cody.getGeneralLevel(), 6);
	}
	
	@Test
	public void testEquipment() {
		Character cody = CharacterBuilder.makeCharacter("Commander", 0);
		assertEquals(7, cody.getMoveSpeed());
	}

}
