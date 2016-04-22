package ui.party;

import java.util.ArrayList;
import java.util.HashMap;

import ui.Menu;
import ui.MenuResponder;
import ui.UIStack;
import character.Character;
import dungeonMode.Party;
import enums.UIPosition;

public class PartyMenuResponder implements MenuResponder {
	
	private HashMap<String, PartyMenuBuilder> builders = new HashMap<String, PartyMenuBuilder>();
	private Menu menu;
	
	public PartyMenuResponder(UIPosition position) {
		Menu menu = new Menu(position);
		menu.setItems(getNames(Party.getCharacters()));
		menu.setResponder(this);
		for(Character character: Party.getCharacters())
			builders.put(character.getName(), new CharacterMenuResponder(character));
		UIStack.push(menu);
	}

	public void menuItemSelected(String string) {
		PartyMenuBuilder builder = builders.get(string);
		if(builder != null)
			builder.build();
	}

	public void inputCancel() {
		UIStack.pop(menu);
	}
	
	private ArrayList<String> getNames(ArrayList<Character> characters) {
		ArrayList<String> names = new ArrayList<String>();
		for(Character character: characters)
			names.add(character.getName());
		return names;
	}

}
