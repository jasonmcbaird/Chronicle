package test.ui;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.junit.Test;

import ui.Menu;
import enums.Direction;
import enums.UIPosition;

public class TestMenu extends JPanel {
	
	@Test
	public void testList() {
		Menu menu = new Menu(UIPosition.LEFT);
		ArrayList<String> items = dummyList();
		menu.setItems(items);
		assertEquals(menu.getItems(), items);
	}
	
	@Test
	public void testMove() {
		Menu menu = new Menu(UIPosition.LEFT);
		ArrayList<String> items = dummyList();
		menu.setItems(items);
		menu.inputDirection(Direction.DOWN);
		assertEquals(menu.getSelection(), items.get(1));
	}
	
	private ArrayList<String> dummyList() {
		ArrayList<String> items = new ArrayList<String>();
		items.add("Attack");
		items.add("Item");
		items.add("Ability");
		return items;
	}
	
}
