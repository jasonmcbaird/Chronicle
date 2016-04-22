package arenaMode;

import java.util.ArrayList;

import ui.Menu;
import ui.TitleDisplay;
import ui.UIStack;
import enums.UIPosition;

public class TeamSizeMenuBuilder implements ArenaMenuBuilder {
	
	public void build(ArenaMode arena) {
		Menu menu = new Menu(UIPosition.RIGHT);
		ArrayList<String> items = new ArrayList<String>();
		for(int i = 1; i <= 8; i++)
			items.add(Integer.toString(i));
		menu.setItems(items);
		menu.setResponder(new ArenaMenuResponder(arena, ArenaMenuKey.TEAM_SIZE));
		UIStack.push(menu);
		TitleDisplay title = new TitleDisplay("Team Size", UIPosition.TOP);
		UIStack.push(title);
	}

}
