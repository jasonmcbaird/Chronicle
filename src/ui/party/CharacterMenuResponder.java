package ui.party;

import java.util.ArrayList;
import java.util.HashMap;

import ui.Menu;
import ui.MenuResponder;
import ui.UIStack;
import character.Character;
import enums.UIPosition;

public class CharacterMenuResponder implements MenuResponder, PartyMenuBuilder {

	private HashMap<String, PartyMenuBuilder> builders = new HashMap<String, PartyMenuBuilder>();
	private Character character;
	private Menu menu;
	
	public CharacterMenuResponder(Character character) {
		this.character = character;
	}

	public void menuItemSelected(String string) {
		PartyMenuBuilder builder = builders.get(string);
		if(builder != null)
			builder.build();
	}

	public void inputCancel() {
		UIStack.pop(menu);
	}
	
	public void build() {
		buildItems();
		menu = new Menu(UIPosition.RIGHT_2);
		menu.setResponder(this);
		ArrayList<String> subMenuNames = new ArrayList<String>();
		for(String string: builders.keySet())
			subMenuNames.add(string);
		menu.setItems(subMenuNames);
		UIStack.push(menu);
	}
	
	private void buildItems() {
		builders.put("Inventory", new InventoryMenuResponder(character.inventory));
		builders.put("Classes", new ClassGrid(character));
	}
	
}
