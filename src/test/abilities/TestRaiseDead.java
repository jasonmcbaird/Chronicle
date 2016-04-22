package test.abilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import queue.Queue;
import roles.necromancer.RaiseDead;
import rules.TakeDamage;
import character.Character;
import encounter.Encounter;
import enums.Condition;
import enums.DamageType;
import enums.EnergyType;
import enums.RoleName;

public class TestRaiseDead {
	
	@Test
	public void testExecute() {
		Encounter encounter = new Encounter();
		Character cody = new Character();
		cody.setRole(RoleName.NECROMANCER, 0);
		
		encounter.add(cody);
		Queue.addAndRun(new RaiseDead(), cody, cody, Condition.ACTIVATE_ABILITY);
		assertTrue(cody.getEnergy(EnergyType.MANA) < cody.getMaxEnergy(EnergyType.MANA));
		
		Character zombie = encounter.getCharacters().get(1);
		
		assertEquals(encounter.getCharacters().size(), 2);
		assertEquals(zombie.getName(), "Zombie");
		
		Queue.addAndRun(new TakeDamage(DamageType.SMASH, 5), cody, zombie, Condition.TAKE_DAMAGE);
		assertTrue(cody.getHealth() < cody.getMaxHealth());
	}

}
