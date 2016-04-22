package test.rules;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import queue.Queue;
import rules.ExecuteOnNextTarget;
import rules.Rule;
import utilities.Logger;
import character.Character;
import character.Team;
import encounter.Encounter;
import enums.Condition;

public class TestExecuteOnNextTarget {
	
	@Test
	public void TestActivate() {
		Queue.clear();
		Character cody = new Character();
		cody.setTeam(Team.getPlayerTeam());
		cody.setName("Cody");
		
		Character jason = new Character();
		jason.setTeam(Team.getEnemyTeam());
		jason.setName("Jason");
		Character jody = new Character();
		jody.setTeam(Team.getEnemyTeam());
		jody.setName("Jody");
		Character cheyenne = new Character();
		cheyenne.setTeam(Team.getEnemyTeam());
		cheyenne.setName("Cheyenne");
		Character colin = new Character();
		colin.setTeam(Team.getEnemyTeam());
		colin.setName("Colin");
		
		Encounter encounter = new Encounter();
		encounter.add(cody);
		encounter.add(jason);
		encounter.add(jody);
		encounter.add(cheyenne);
		
		Queue.add(new ExecuteOnNextTarget(new TestRule()), cody, cody, Condition.START_TURN);
		
		Queue.run(Condition.START_TURN, cody);
		Logger.print(jason.getName() + jody.getName() + cheyenne.getName());
		ArrayList<String> names = new ArrayList<String>();
		names.add(jason.getName());
		names.add(jody.getName());
		names.add(cheyenne.getName());
		assertTrue(names.contains("Bitch"));
		 
		Queue.run(Condition.START_TURN, cody);
		Queue.run(Condition.START_TURN, cody);
		assertTrue(jason.getName() == "Bitch");
		assertTrue(jody.getName() == "Bitch");
		assertTrue(cheyenne.getName() == "Bitch");
		
		encounter.add(colin);
		Queue.run(Condition.START_TURN, cody);
		assertTrue(colin.getName() == "Bitch");
	}
	
	@Test
	public void TestExcept() {
		Queue.clear();
		Character cody = new Character();
		cody.setTeam(Team.getPlayerTeam());
		cody.setName("Cody");
		
		Character jason = new Character();
		jason.setTeam(Team.getEnemyTeam());
		jason.setName("Jason");
		Character jody = new Character();
		jody.setTeam(Team.getEnemyTeam());
		jody.setName("Jody");
		Character cheyenne = new Character();
		cheyenne.setTeam(Team.getEnemyTeam());
		cheyenne.setName("Cheyenne");
		Character colin = new Character();
		colin.setTeam(Team.getEnemyTeam());
		colin.setName("Colin");
		
		Encounter encounter = new Encounter();
		encounter.add(cody);
		encounter.add(jason);
		encounter.add(jody);
		encounter.add(cheyenne);
		
		ArrayList<Character> except = new ArrayList<Character>();
		except.add(jason);
		Queue.add(new ExecuteOnNextTarget(new TestRule(), except), cody, cody, Condition.START_TURN);
		
		Queue.run(Condition.START_TURN, cody);
		assertTrue(jody.getName() == "Bitch" || cheyenne.getName() == "Bitch");
		 
		Queue.run(Condition.START_TURN, cody);
		Queue.run(Condition.START_TURN, cody);
		assertTrue(jody.getName() == "Bitch");
		assertTrue(cheyenne.getName() == "Bitch");
		
		encounter.add(colin);
		Queue.run(Condition.START_TURN, cody);
		assertTrue(colin.getName() == "Bitch");
		assertTrue(jason.getName() != "Bitch");
	}
	
	class TestRule extends Rule {
		public void subExecute(Character source, Character target, Condition condition) {
			Logger.print("Changing " + target.getName() + "'s name to bitch");
			target.setName("Bitch");
		}
	}
}
