package test.ui;

import static org.junit.Assert.*;

import javax.swing.JPanel;

import org.junit.Test;

import ui.Menu;
import ui.UIComponent;
import enums.UIPosition;

public class TestUIComponent {
	
	
	@Test
	public void testRemove() {
		JPanel jPanel = new JPanel();
		UIComponent menu = new Menu(UIPosition.LEFT);
		jPanel.add(menu);
		assertEquals(jPanel.getComponents().length, 1);
		menu.remove();
		assertEquals(jPanel.getComponents().length, 0);
	}
}
