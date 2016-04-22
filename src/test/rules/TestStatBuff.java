package test.rules;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import queue.Queue;
import rules.StatBuff;
import character.Character;
import enums.Condition;
import enums.Stat;

public class TestStatBuff {
	
	@Test
	public void testActivate() {
		Character cody = new Character();
		int strength = cody.attributes.getStat(Stat.STRENGTH);
		StatBuff statBuff = new StatBuff(Stat.STRENGTH, 2, Condition.END_TURN);
		
		Queue.add(statBuff, cody, cody, Condition.NOW);
		Queue.run(Condition.NOW, cody);
		assertEquals(cody.attributes.getStat(Stat.STRENGTH), strength + 2);
		
		Queue.run(Condition.END_TURN, cody);
		assertEquals(cody.attributes.getStat(Stat.STRENGTH), strength);
		
		Queue.run(Condition.END_ENCOUNTER, cody);
		assertEquals(cody.attributes.getStat(Stat.STRENGTH), strength);
		
		Queue.add(statBuff, cody, cody, Condition.NOW);
		Queue.run(Condition.NOW, cody);
		assertEquals(cody.attributes.getStat(Stat.STRENGTH), strength + 2);
		
		Queue.run(Condition.END_ENCOUNTER, cody);
		assertEquals(cody.attributes.getStat(Stat.STRENGTH), strength);
	}
	
	@Test
	public void testNoClearCondition() {
		Character cody = new Character();
		int strength = cody.attributes.getStat(Stat.STRENGTH);
		StatBuff statBuff = new StatBuff(Stat.STRENGTH, 2);
		
		Queue.add(statBuff, cody, cody, Condition.NOW);
		Queue.run(Condition.NOW, cody);
		assertEquals(cody.attributes.getStat(Stat.STRENGTH), strength + 2);
		
		Queue.run(Condition.END_ENCOUNTER, cody);
		assertEquals(cody.attributes.getStat(Stat.STRENGTH), strength);
	}
	
	@Test
	public void testMultipleClearConditions() {
		Character cody = new Character();
		int strength = cody.attributes.getStat(Stat.STRENGTH);
		ArrayList<Condition> clearConditions = new ArrayList<Condition>();
		clearConditions.add(Condition.END_TURN);
		clearConditions.add(Condition.ATTACK);
		StatBuff statBuff = new StatBuff(Stat.STRENGTH, 2, clearConditions);
		
		
		Queue.add(statBuff, cody, cody, Condition.NOW);
		Queue.run(Condition.NOW, cody);
		assertEquals(cody.attributes.getStat(Stat.STRENGTH), strength + 2);
		
		Queue.run(Condition.END_TURN, cody);
		assertEquals(cody.attributes.getStat(Stat.STRENGTH), strength);
		
		
		Queue.add(statBuff, cody, cody, Condition.NOW);
		Queue.run(Condition.NOW, cody);
		assertEquals(cody.attributes.getStat(Stat.STRENGTH), strength + 2);
		
		Queue.run(Condition.ATTACK, cody);
		assertEquals(cody.attributes.getStat(Stat.STRENGTH), strength);
		
		
		Queue.add(statBuff, cody, cody, Condition.NOW);
		Queue.run(Condition.NOW, cody);
		assertEquals(cody.attributes.getStat(Stat.STRENGTH), strength + 2);
		
		Queue.run(Condition.END_ENCOUNTER, cody);
		assertEquals(cody.attributes.getStat(Stat.STRENGTH), strength);
		
		Queue.run(Condition.END_TURN, cody);
		assertEquals(cody.attributes.getStat(Stat.STRENGTH), strength);
		
		Queue.run(Condition.ATTACK, cody);
		assertEquals(cody.attributes.getStat(Stat.STRENGTH), strength);
	}
}
