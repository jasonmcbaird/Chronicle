package test.ui;

import static org.junit.Assert.assertEquals;

import java.util.EmptyStackException;

import javax.swing.JPanel;

import org.junit.After;
import org.junit.Test;

import ui.Menu;
import ui.UIComponent;
import ui.UIStack;
import enums.UIPosition;

public class TestUIStack extends JPanel {
	
	@After
	public void tearDown() {
		UIStack.clear();
	}
	
	@Test
	public void testPush() {
		UIStack.setMainPanel(this);
		pushDummy();
		assertEquals(UIStack.getSize(), 1);
	}
	
	@Test
	public void testGetTop() {
		UIStack.setMainPanel(this);
		pushDummy();
		assertEquals(UIStack.getTop().getName(), "Dummy");
	}
	
	@Test(expected=EmptyStackException.class)
	public void testGetTopEmpty() {
		UIStack.setMainPanel(this);
		UIStack.getTop();
	}
	
	@Test
	public void testPop() {
		UIStack.setMainPanel(this);
		pushDummy();
		pushDummy();
		assertEquals(UIStack.getSize(), 2);
		UIStack.pop();
		assertEquals(UIStack.getSize(), 1);
	}
	
	@Test(expected=EmptyStackException.class)
	public void testPopEmpty() {
		UIStack.setMainPanel(this);
		UIStack.pop();
	}
	
	private void pushDummy() {
		UIStack.push(getDummyComponent());
	}
	
	private UIComponent getDummyComponent() {
		UIComponent component = new Menu(UIPosition.LEFT);
		component.setName("Dummy");
		return component;
	}
}
