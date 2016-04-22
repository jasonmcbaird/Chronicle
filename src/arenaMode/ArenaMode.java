package arenaMode;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import maps.CustomMap;
import maps.MapBuilder;
import ui.Music;
import ui.UIStack;
import ui.grid.EncounterGrid;
import ui.grid.Grid;
import utilities.Logger;
import character.Character;
import character.CharacterBuilder;
import character.Team;
import controller.Controller;
import encounter.Encounter;
import encounter.TeamBuilder;

public class ArenaMode extends JFrame {
	
	// Ideal resolution: 1440 x 1080 ?
	
	private JPanel mainPanel;
	private Grid startupGrid;
	private String hero = "";
	private int level = 0;
	private String squad = "";
	private String enemySquad = "";
	private String map = "";
	private int teamSize = 0;
	private int menuIndex = 0;
	private ArrayList<ArenaMenuBuilder> menuBuilders = new ArrayList<ArenaMenuBuilder>();
	
	public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArenaMode arena = new ArenaMode();
            }
        });
    }
	
	public ArenaMode() {
    	mainPanel = FullscreenSetup.setFullscreen(this);
    	setTitle("Chronicle: Arena");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	startupGrid = new Grid(getWidth(), getHeight());
    	UIStack.push(startupGrid);
    	Logger.print("Main frame y: " + getY(), -1);
		Logger.print("Main frame height: " + getHeight(), -1);
		Logger.print("Main panel y: " + mainPanel.getY(), -1);
		Logger.print("Main panel height: " + mainPanel.getHeight(), -1);
		Logger.print("Startup grid y: " + startupGrid.getY(), -1);
		Logger.print("Startup grid height: " + startupGrid.getHeight(), -1);
		menuBuilders.add(new HeroMenuBuilder());
		menuBuilders.add(new LevelMenuBuilder());
		menuBuilders.add(new SquadMenuBuilder());
		menuBuilders.add(new EnemySquadMenuBuilder());
		menuBuilders.add(new MapMenuBuilder());
		menuBuilders.add(new TeamSizeMenuBuilder());
		startArena();
	}
	
	public void startArena() {
		makeMenu(menuIndex);
	}
	
	public void setValue(ArenaMenuKey key, String value) {
		switch (key) {
		case HERO:
			hero = value;
			return;
		case LEVEL:
			level = Integer.parseInt(value);
			return;
		case SQUAD:
			squad = value;
			return;
		case ENEMY_SQUAD:
			enemySquad = value;
			return;
		case MAP:
			map = value;
			return;
		case TEAM_SIZE:
			teamSize = Integer.parseInt(value);
			return;
		default:
			throw new UnsupportedOperationException("Tried to set an ArenaMode value that doesn't exist.");
		}
	}
	
	public void back() {
		menuIndex--;
		if(menuIndex < 0)
			menuIndex = 0;
		makeMenu(menuIndex);
	}
	
	public void forward() {
		menuIndex++;
		if(menuIndex > menuBuilders.size() - 1)
			startEncounter();
		else
			makeMenu(menuIndex);
	}
	
	private void makeMenu(int index) {
		menuBuilders.get(index).build(this);
	}
	
	private void startEncounter() {
        EncounterGrid grid = new EncounterGrid(getWidth(), getHeight());
		Encounter encounter = new Encounter(grid);
		CustomMap customMap = MapBuilder.build(map, encounter);
		
		Character hero = CharacterBuilder.makeCharacter(this.hero, level);
		hero.setTeam(Team.getPlayerTeam());
		ArrayList<Character> heroTeam = new ArrayList<Character>();
		heroTeam.add(hero);
		heroTeam.addAll(TeamBuilder.buildWithoutLeader(squad, teamSize - 1, Team.getPlayerTeam()));
		for(Character character: heroTeam)
			character.setController(Controller.getPlayerController());
		ArrayList<Character> enemyTeam = TeamBuilder.build(enemySquad, teamSize, Team.getEnemyTeam());
		
		for(Character character: heroTeam)
			encounter.add(character);
		for(Character character: enemyTeam)
			encounter.add(character);
				
		customMap.setPositions(heroTeam, enemyTeam);

		UIStack.push(grid);
		UIStack.pop(startupGrid);
		UIStack.push(new Music());
		encounter.startEncounter();
	}
}
