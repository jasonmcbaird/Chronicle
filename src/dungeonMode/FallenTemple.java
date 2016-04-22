package dungeonMode;

import ui.grid.EncounterGrid;
import dungeonMode.rooms.Armory;
import dungeonMode.rooms.CombatRoom;
import dungeonMode.rooms.EverflowingFountain;
import dungeonMode.rooms.TreasureRoom;

public class FallenTemple extends Dungeon {
	
	private String name = "Temple of the Fallen God";
	
	public FallenTemple(EncounterGrid grid) {
		super(grid);
		CombatRoom ruins = new CombatRoom(this);
		ruins.setOpponents("Brigands", 1);
		rooms.add(ruins);
		
		rooms.add(new EverflowingFountain(this));
		
		CombatRoom darkTemple = new CombatRoom(this);
		darkTemple.setMap("Cathedral");
		darkTemple.setOpponents("Cultists", 1);
		rooms.add(darkTemple);
		
		rooms.add(new Armory(this));
		
		CombatRoom unholyCave = new CombatRoom(this);
		unholyCave.setMap("Cavern");
		unholyCave.setOpponents("Demonlord", 2);
		rooms.add(unholyCave);
		
		rooms.add(new TreasureRoom(this));
	}
	
	public String getName() {
		return name;
	}
}
