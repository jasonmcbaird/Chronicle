package arenaMode;

import java.util.ArrayList;

import ui.Menu;
import ui.TitleDisplay;
import ui.UIStack;
import enums.UIPosition;

public class LevelMenuBuilder implements ArenaMenuBuilder {
	
	public void build(ArenaMode arena) {
		Menu menu = new Menu(UIPosition.RIGHT);
		ArrayList<String> items = new ArrayList<String>();
		for(int i = 1; i <= 10; i++)
			items.add(Integer.toString(i));
		menu.setItems(items);
		menu.setResponder(new ArenaMenuResponder(arena, ArenaMenuKey.LEVEL));
		UIStack.push(menu);
		TitleDisplay title = new TitleDisplay("Hero Level", UIPosition.TOP);
		UIStack.push(title);
	}

}
