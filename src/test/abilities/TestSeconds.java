package test.abilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ability.Ability;
import queue.Queue;
import roles.warp.Seconds;
import rules.ChangeEnergy;
import rules.EndTurn;
import rules.Move;
import character.Character;
import character.Team;
import encounter.Encounter;
import enums.Condition;
import enums.Direction;
import enums.EnergyType;
import enums.RoleName;

public class TestSeconds {
	
	@Test
	public void TestActivate() {	
		Character cody = new Character();
		cody.setTeam(Team.getPlayerTeam());
		cody.setIsPlayer(true);
		Character dummy = new Character(); // Keeps the encounter from ending due to lack of enemies
		dummy.setTeam(Team.getEnemyTeam());
		
		Encounter encounter = Encounter.get();
		encounter.add(cody);
		encounter.add(dummy);
		encounter.startEncounter();
				
		cody.setRole(RoleName.WARP, 0);
		Queue.addAndRun(new ChangeEnergy(EnergyType.SECONDS, -6), cody, cody, Condition.NOW);
		assertEquals(cody.getEnergy(EnergyType.SECONDS), 0);
		Seconds seconds = new Seconds();
		Queue.addAndRun(seconds, cody, cody, Condition.ACTIVATE_ABILITY);
		encounter.startEncounter();
		
		assertEquals(cody.getEnergy(EnergyType.SECONDS), 6);
		Queue.add(new EndTurn(encounter.getGrid()), cody, cody, Condition.ACTIVATE_ABILITY);
		Queue.addAndRun(new TestAbility(), cody, cody, Condition.ACTIVATE_ABILITY);
		assertEquals(cody.getEnergy(EnergyType.SECONDS), 2);
		
		Queue.addAndRun(new Move(Direction.DOWN, 4), cody, cody, Condition.MOVE);
		assertEquals(cody.getEnergy(EnergyType.SECONDS), 0);
		
		Queue.addAndRun(new ChangeEnergy(EnergyType.SECONDS, 6), cody, cody, Condition.NOW);
		assertEquals(cody.getEnergy(EnergyType.SECONDS), 6);
		
		Queue.addAndRun(new TestWarpAbility(), cody, cody, Condition.ACTIVATE_ABILITY);
		assertEquals(cody.getEnergy(EnergyType.SECONDS), 1);
		
		int y = cody.getY();
		Queue.addAndRun(new Move(Direction.DOWN, 1), cody, cody, Condition.MOVE);
		assertEquals(cody.getEnergy(EnergyType.SECONDS), 0);
		assertEquals(cody.getY(), y);
	}
	
	class TestAbility extends Ability {
		public void subExecute(Character source, Character target, Condition condition) {
			target.setName("Bitch");
		}
	}
	
	class TestWarpAbility extends Ability {
		public void subExecute(Character source, Character target, Condition condition) {
			target.setName("Bitch");
		}
		
		public int getSeconds(Character character) {
			return 5;
		}
	}
}
