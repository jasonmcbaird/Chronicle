package test.game;

import static org.junit.Assert.assertEquals;
import mapLogic.GridLogic;
import maps.MapBuilder;

import org.junit.Test;

import utilities.Position;
import character.Character;
import character.Team;
import encounter.Encounter;

public class TestGridLogic {
	
	@Test
	public void testFind() {
		Encounter encounter = new Encounter();
		Character cody = new Character();
		cody.setPosition(new Position(5, 4));
		cody.setTeam(Team.getPlayerTeam());
		encounter.add(cody);
		MapBuilder.build("Caverns", encounter);
		
		Character jason = new Character();
		jason.setPosition(new Position(13, 11));
		
		Character jody = new Character();
		jody.setPosition(new Position(16, 6));
		
		encounter.add(jason);
		encounter.add(jody);
		
		Character target = GridLogic.findNearestTarget(cody);
		assertEquals(target, jason);
		
		Character drew = new Character();
		drew.setPosition(new Position(10, 11));
		drew.setTeam(Team.getPlayerTeam());
		encounter.add(drew);
		
		target = GridLogic.findNearestTarget(cody);
		assertEquals(target, jody);
	}

}
