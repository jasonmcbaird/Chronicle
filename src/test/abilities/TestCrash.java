package test.abilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import queue.Queue;
import roles.warrior.Crash;
import character.Character;
import encounter.Encounter;
import enums.Condition;
import enums.RoleName;
import enums.Stat;

public class TestCrash {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.setRole(RoleName.WARRIOR, 0);
		cody.attributes.changeStat(Stat.ACCURACY, 10000000); // TODO: This is a bad way to ensure the attack doesn't miss. Figure out a better way?
		cody.setX(3);
		cody.setY(5);
		Character jason = new Character();
		jason.setX(4);
		jason.setY(5);
		
		Encounter encounter = Encounter.get();
		encounter.add(jason);
		encounter.add(cody);
		
		int priorMoveSpeed = jason.getMoveSpeed();
		
		Queue.addAndRun(new Crash(), cody, jason, Condition.ACTIVATE_ABILITY);
		
		assertEquals(0, jason.getMoveSpeed());
		
		Queue.run(Condition.END_TURN, jason);
		
		assertEquals(priorMoveSpeed, jason.getMoveSpeed());
	}
	
}
