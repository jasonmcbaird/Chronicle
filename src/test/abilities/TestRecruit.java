package test.abilities;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import queue.Queue;
import roles.general.Recruit;
import rules.TakeDamage;
import character.Character;
import encounter.Encounter;
import enums.Condition;
import enums.DamageType;
import enums.EnergyType;
import enums.RoleName;
import enums.Stat;

public class TestRecruit {
	
	@Test
	public void testExecute() {
		Character cody = new Character();
		cody.setRole(RoleName.GENERAL, 0);
		
		Encounter encounter = Encounter.get();
		encounter.add(cody);
		assertEquals(encounter.getEntities().size(), 1);
		Queue.addAndRun(new Recruit(), cody, cody, Condition.ACTIVATE_ABILITY);
		assertEquals(encounter.getEntities().size(), 2);
		
		ArrayList<Character> characters = encounter.getCharacters();
		Character soldier;
		if(characters.get(0) != cody)
			soldier = characters.get(0);
		else
			soldier = characters.get(1);
		assertEquals(Math.round(cody.attributes.getStat(Stat.STRENGTH) * 70 / 100), soldier.attributes.getStat(Stat.STRENGTH));
		assertEquals(cody.getController(), soldier.getController());
		assertEquals(cody.getTeam(), soldier.getTeam());
		
		assertEquals(cody.getEnergy(EnergyType.CONCENTRATION), cody.getMaxEnergy(EnergyType.CONCENTRATION) - 4);
		Queue.addAndRun(new TakeDamage(DamageType.SMASH, 100), soldier, soldier, Condition.TAKE_DAMAGE);
		assertEquals(cody.getEnergy(EnergyType.CONCENTRATION), cody.getMaxEnergy(EnergyType.CONCENTRATION));
	}

}
