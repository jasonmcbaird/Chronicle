package test.ui;

import org.junit.Test;

import character.Character;
import ui.conversation.Statement;

public class TestStatement {
	
	@Test
	public void testProgress() {
		Character cody = new Character();
		Statement statement = new Statement(cody, "The biggest of all bitches.");
	}

}
