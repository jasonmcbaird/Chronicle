package test.ui;

import static org.junit.Assert.*;
import mapLogic.Entity;
import mapLogic.Terrain;

import org.junit.Test;

import ui.grid.Grid;
import utilities.Position;

public class TestGrid {

	@Test
	public void testGetEntitiesAt() {
		Grid uiGrid = new Grid();
		Entity entity = new Terrain(new Position(1, 1), null);
		uiGrid.add(entity);
		assertEquals(uiGrid.getEntitiesAt(new Position(1, 1)).size(), 1);
	}
	
	@Test
	public void testRemove() {
		Grid uiGrid = new Grid();
		Entity entity = new Terrain(new Position(1, 1), null);
		uiGrid.add(entity);
		assertEquals(uiGrid.getEntitiesAt(new Position(1, 1)).size(), 1);
		uiGrid.remove(entity);
		assertEquals(uiGrid.getEntitiesAt(new Position(1, 1)).size(), 0);
	}
	
	@Test
	public void testDisplay() {
		// How the hell do I do this?
	}
}
