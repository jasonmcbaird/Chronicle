package dungeonMode;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.Music;
import ui.UIStack;
import ui.grid.EncounterGrid;
import ui.grid.Grid;
import arenaMode.FullscreenSetup;
import character.Character;
import character.CharacterBuilder;
import character.Team;
import controller.Controller;

public class DungeonMode extends JFrame {
	
	private Grid startupGrid;
	private JPanel mainPanel;
	
	public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DungeonMode dungeon = new DungeonMode();
            }
        });
    }
	
	public DungeonMode() {
		mainPanel = FullscreenSetup.setFullscreen(this);
    	setTitle("Chronicle: Arena");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	startupGrid = new Grid(getWidth(), getHeight());
    	UIStack.push(startupGrid);
    	EncounterGrid grid = new EncounterGrid(getWidth(), getHeight());
    	Dungeon dungeon = new FallenTemple(grid);
    	
    	Party.addCharacter(CharacterBuilder.makeCharacter("Paladin", 8));
    	Party.addCharacter(CharacterBuilder.makeCharacter("Thief", 7));
    	Party.addCharacter(CharacterBuilder.makeCharacter("Psych", 7));
    	Party.addCharacter(CharacterBuilder.makeCharacter("Blood Mage", 7));
    	for(Character character: Party.getCharacters()) {
    		character.setController(Controller.getPlayerController());
    		character.setTeam(Team.getPlayerTeam());
    	}
    	
    	Music music = new Music();
    	UIStack.push(music);
    	dungeon.start();
	}
}
