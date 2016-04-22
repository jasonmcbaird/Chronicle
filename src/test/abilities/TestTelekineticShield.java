package test.abilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ability.Ability;
import queue.Queue;
import roles.psion.TelekineticShield;
import rules.TakeDamage;
import character.Character;
import enums.Condition;
import enums.DamageType;

public class TestTelekineticShield {
	
	@Test
	public void TestActivate() {
		Character jason = new Character();
		TakeDamage smash = new TakeDamage(DamageType.SMASH, 10);
		TelekineticShield shield = new TelekineticShield();
		
		Queue.addAndRun(shield, jason, jason, Condition.ACTIVATE_ABILITY);
		Queue.addAndRun(smash, jason, jason, Condition.TAKE_DAMAGE);
		int healthAfterAttack = jason.attributes.getCurrentHealth();
		assertEquals(healthAfterAttack, jason.attributes.getMaxHealth() - 7);
		
		TakeDamage bleed = new TakeDamage(DamageType.BLEED, 10);
		
		Queue.addAndRun(bleed, jason, jason, Condition.TAKE_DAMAGE);
		int healthAfterSecondAttack = jason.attributes.getCurrentHealth();
		assertEquals(healthAfterSecondAttack, healthAfterAttack - 10);
		
		TakeDamage slash = new TakeDamage(DamageType.SLASH, 10);
		
		Queue.addAndRun(slash, jason, jason, Condition.TAKE_DAMAGE);
		int healthAfterThirdAttack = jason.attributes.getCurrentHealth();
		assertEquals(healthAfterThirdAttack, healthAfterSecondAttack - 7);
		jason.attributes.setCurrentHealth(jason.attributes.getMaxHealth());
		healthAfterThirdAttack = jason.attributes.getCurrentHealth();
		
		TakeDamage stab = new TakeDamage(DamageType.STAB, 10);
		
		Queue.addAndRun(stab, jason, jason, Condition.TAKE_DAMAGE);
		int healthAfterFourthAttack = jason.attributes.getCurrentHealth();
		assertEquals(healthAfterFourthAttack, healthAfterThirdAttack - 7);
		
		Queue.run(Condition.START_TURN, jason);
		Queue.run(Condition.START_TURN, jason);
		Queue.run(Condition.START_TURN, jason);
		Queue.run(Condition.START_TURN, jason);
		TakeDamage stabAgain = new TakeDamage(DamageType.STAB, 10);
		
		Queue.addAndRun(stabAgain, jason, jason, Condition.TAKE_DAMAGE);
		int healthAfterFifthAttack = jason.attributes.getCurrentHealth();
		assertEquals(healthAfterFifthAttack, healthAfterFourthAttack - 7);
		
		Queue.run(Condition.START_TURN, jason);
		TakeDamage stabYetAgain = new TakeDamage(DamageType.STAB, 10);
		
		Queue.addAndRun(stabYetAgain, jason, jason, Condition.TAKE_DAMAGE);
		int healthAfterSixthAttack = jason.attributes.getCurrentHealth();
		assertEquals(healthAfterSixthAttack, healthAfterFifthAttack - 10);
	}
	
	class TestAbility extends Ability {
		public void subExecute(Character source, Character target, Condition condition) {
			target.setName("Bitch");
		}
	}
}
