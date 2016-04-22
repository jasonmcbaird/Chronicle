package ui.conversation;

import java.awt.image.BufferedImage;

import character.Character;

public class Statement {
	
	private Character character;
	private String text;
	
	public Statement(Character character, String text) {
		this.character = character;
		this.text = text;
	}
	
	public Character getCharacter() {
		return character;
	}
	
	public String getText() {
		return text;
	}
	
	public BufferedImage getImage() {
		return character.getImage();
	}

}
