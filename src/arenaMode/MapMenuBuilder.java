package arenaMode;

import maps.MapBuilder;
import ui.Menu;
import ui.TitleDisplay;
import ui.UIStack;
import enums.UIPosition;

public class MapMenuBuilder implements ArenaMenuBuilder {
	
	public void build(ArenaMode arena) {
		Menu menu = new Menu(UIPosition.RIGHT);
		menu.setItems(MapBuilder.getMapNames());
		menu.setResponder(new ArenaMenuResponder(arena, ArenaMenuKey.MAP));
		UIStack.push(menu);
		TitleDisplay title = new TitleDisplay("Map", UIPosition.TOP);
		UIStack.push(title);
	}

}
