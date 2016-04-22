package test.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ui.UIStack;
import ui.conversation.Conversation;
import ui.conversation.Statement;
import character.Character;

public class TestConversation {
	
	@Test
	public void testProgress() {
		Character cody = new Character();
		Character jason = new Character();
		Conversation conversation = new Conversation();
		conversation.add(new Statement(cody, "I'm seriously a bitch, guys."));
		conversation.add(new Statement(cody, "No kidding. The biggest."));
		conversation.add(new Statement(jason, "I knew it!"));
		
		assertEquals("I'm seriously a bitch, guys.", conversation.getStatement().getText());
		assertEquals(cody, conversation.getStatement().getCharacter());
		
		conversation.inputAccept();
		
		assertEquals("No kidding. The biggest.", conversation.getStatement().getText());
		assertEquals(cody, conversation.getStatement().getCharacter());
		
		conversation.inputAccept();
		
		assertEquals("I knew it!", conversation.getStatement().getText());
		assertEquals(jason, conversation.getStatement().getCharacter());
	}

}
