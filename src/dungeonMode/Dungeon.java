package dungeonMode;

import java.util.ArrayList;

import ui.TitleDisplay;
import ui.UIStack;
import ui.grid.EncounterGrid;
import enums.UIPosition;

public abstract class Dungeon {
	
	protected ArrayList<Room> rooms = new ArrayList<Room>();
	private int roomIndex = 0;
	protected EncounterGrid grid;
	
	public Dungeon(EncounterGrid grid) {
		this.grid = grid;
	}
	
	public void start() {
		enterRoom();
	}
	
	public void nextRoom() {
		roomIndex ++;
		enterRoom();
	}
	
	public void enterRoom() {
		if(roomIndex < rooms.size())
			rooms.get(roomIndex).enter(grid);
		else
			victory();
	}
	
	private void victory() {
		TitleDisplay title = new TitleDisplay("Victory!", UIPosition.TOP_LEFT);
		UIStack.push(title);
	}

}
