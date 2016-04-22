package dungeonMode;

import java.util.ArrayList;

import character.Character;

public class Party {
	
	public static ArrayList<Character> characters = new ArrayList<Character>();
	
	public static ArrayList<Character> getCharacters() {
		return characters;
	}
	
	public static void addCharacter(Character character) {
		characters.add(character);
	}
	
	/**
	 * In the overworld, you have a cart follow you around.
	 * 
	 * When you enter a city, the characters in your party scatter out around the town.
	 * Each character goes somewhere specific to their personality.
	 * You can talk to them.
	 * Like, one dude usually goes to a church, another to a bar,
	 * and a third to the mage's guild.
	 * 
	 */
}
