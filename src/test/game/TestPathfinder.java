package test.game;

import static org.junit.Assert.*;
import mapLogic.GridLogic;
import mapLogic.pathfinders.AStarPathfinder;
import mapLogic.pathfinders.Path;
import maps.MapBuilder;

import org.junit.Test;

import utilities.Logger;
import utilities.Position;
import character.Character;
import encounter.Encounter;

public class TestPathfinder {
	
	@Test
	public void testFind() {
		Encounter encounter = new Encounter();
		Character cody = new Character();
		cody.setPosition(new Position(5, 4));
		encounter.add(cody);
		MapBuilder.build("Caverns", encounter);
		
		Character jason = new Character();
		jason.setPosition(new Position(13, 11));
		
		encounter.add(jason);
		
		AStarPathfinder pathfinder = new AStarPathfinder(GridLogic.get());
		
		Path path = pathfinder.findPath(cody, jason);
		Logger.print(path.toString(), 1);
		assertTrue(path.toString().contains("12, 11"));
		
		Character drew = new Character();
		drew.setPosition(new Position(10, 11));
		encounter.add(drew);
		
		Path impededPath = pathfinder.findPath(cody, jason);
		Logger.print(impededPath.toString(), 1);
		assertTrue(impededPath.toString().contains("13, 10"));
		
		assertTrue(path.getLength() < impededPath.getLength());
	}

}
