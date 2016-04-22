package arenaMode;

import ui.MenuResponder;
import ui.UIStack;

public class ArenaMenuResponder implements MenuResponder {
	
	private ArenaMode arena;
	private ArenaMenuKey key;
	
	public ArenaMenuResponder(ArenaMode arena, ArenaMenuKey key) {
		this.arena = arena;
		this.key = key;
	}

	@Override
	public void menuItemSelected(String string) {
		UIStack.pop();
		UIStack.pop();
		arena.setValue(key, string);
		arena.forward();
	}

	@Override
	public void inputCancel() {
		UIStack.pop();
		UIStack.pop();
		arena.back();
	}

}
