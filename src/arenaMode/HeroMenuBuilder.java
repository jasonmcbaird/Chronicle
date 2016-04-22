package arenaMode;

import ui.Menu;
import ui.TitleDisplay;
import ui.UIStack;
import character.CharacterBuilder;
import enums.UIPosition;

public class HeroMenuBuilder implements ArenaMenuBuilder {
	
	public void build(ArenaMode arena) {
		Menu menu = new Menu(UIPosition.RIGHT);
		menu.setItems(CharacterBuilder.getHeroNames());
		menu.setResponder(new ArenaMenuResponder(arena, ArenaMenuKey.HERO));
 		UIStack.push(menu);
		TitleDisplay title = new TitleDisplay("Choose Hero", UIPosition.TOP);
		UIStack.push(title);
	}

}
