package test.rules;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import ability.Ability;
import queue.Queue;
import rules.ReduceDamage;
import rules.TakeDamage;
import character.Character;
import enums.Condition;
import enums.DamageType;

public class TestReduceDamage {
	
	@Test
	public void TestActivate() {
		Character jason = new Character();
		TakeDamage damage = new TakeDamage(DamageType.SMASH, 10);
		ReduceDamage reduce = new ReduceDamage((float) 0.7);
		reduce.executeOnEvent(damage, jason, jason);
		
		Queue.addAndRun(damage, jason, jason, Condition.TAKE_DAMAGE);
		int healthAfterAttack = jason.attributes.getCurrentHealth();
		assertEquals(healthAfterAttack, jason.attributes.getMaxHealth() - 3);
	}
	
	@Test
	public void TestType() {
		Character jason = new Character();
		TakeDamage damage = new TakeDamage(DamageType.SMASH, 10);
		ReduceDamage reduce = new ReduceDamage((float) 0.5, DamageType.SMASH);
		reduce.executeOnEvent(damage, jason, jason);
		
		Queue.addAndRun(damage, jason, jason, Condition.TAKE_DAMAGE);
		Queue.run(Condition.TAKE_DAMAGE, jason);
		int healthAfterAttack = jason.attributes.getCurrentHealth();
		assertEquals(healthAfterAttack, jason.attributes.getMaxHealth() - 5);
		
		TakeDamage damage2 = new TakeDamage(DamageType.BLEED, 10);
		reduce.executeOnEvent(damage2, jason, jason);
		
		Queue.addAndRun(damage2, jason, jason, Condition.TAKE_DAMAGE);
		int healthAfterSecondAttack = jason.attributes.getCurrentHealth();
		assertEquals(healthAfterSecondAttack, healthAfterAttack - 10);
	}
	
	@Test
	public void TestMultipleTypes() {
		Character jason = new Character();
		TakeDamage smash = new TakeDamage(DamageType.SMASH, 2);
		ArrayList<DamageType> types = new ArrayList<DamageType>();
		types.add(DamageType.SMASH);
		types.add(DamageType.SLASH);
		ReduceDamage reduce = new ReduceDamage((float) 0.5, types);
		reduce.setPersistent(true);
		
		Queue.add(smash, jason, jason, Condition.TAKE_DAMAGE);
		reduce.executeOnEvent(smash, jason, jason);
		Queue.run(Condition.TAKE_DAMAGE, jason);
		int healthAfterAttack = jason.attributes.getCurrentHealth();
		assertEquals(healthAfterAttack, jason.attributes.getMaxHealth() - 1);
		
		TakeDamage bleed = new TakeDamage(DamageType.BLEED, 2);
		
		Queue.add(bleed, jason, jason, Condition.TAKE_DAMAGE);
		reduce.executeOnEvent(bleed, jason, jason);
		Queue.run(Condition.TAKE_DAMAGE, jason);
		int healthAfterSecondAttack = jason.attributes.getCurrentHealth();
		assertEquals(healthAfterSecondAttack, healthAfterAttack - 2);
		
		TakeDamage slash = new TakeDamage(DamageType.SLASH, 2);
		
		Queue.add(slash, jason, jason, Condition.TAKE_DAMAGE);
		reduce.executeOnEvent(slash, jason, jason);
		Queue.run(Condition.TAKE_DAMAGE, jason);
		int healthAfterThirdAttack = jason.attributes.getCurrentHealth();
		assertEquals(healthAfterThirdAttack, healthAfterSecondAttack - 1);
	}
	
	class TestAbility extends Ability {
		public void subExecute(Character source, Character target, Condition condition) {
			target.setName("Bitch");
		}
	}
}
