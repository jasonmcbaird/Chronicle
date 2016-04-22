package arenaMode;

import ui.Menu;
import ui.TitleDisplay;
import ui.UIStack;
import encounter.TeamBuilder;
import enums.UIPosition;

public class SquadMenuBuilder implements ArenaMenuBuilder {
	
	public void build(ArenaMode arena) {
		Menu menu = new Menu(UIPosition.RIGHT);
		menu.setItems(TeamBuilder.getTeamNames());
		menu.setResponder(new ArenaMenuResponder(arena, ArenaMenuKey.SQUAD));
		UIStack.push(menu);
		TitleDisplay title = new TitleDisplay("Hero's Squad", UIPosition.TOP);
		UIStack.push(title);
	}

}
