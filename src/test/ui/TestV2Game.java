package test.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import maps.Arena;
import ui.LogDisplay;
import ui.UIStack;
import ui.conversation.Conversation;
import ui.conversation.Statement;
import ui.grid.EncounterGrid;
import utilities.Logger;
import arenaMode.FullscreenSetup;
import character.Character;
import character.Team;
import controller.Controller;
import encounter.Encounter;
import enums.RoleName;

public class TestV2Game extends JFrame {
	
	private JPanel mainPanel;
	
	public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TestV2Game testUI = new TestV2Game();
                testUI.setVisible(true);
            }
        });
    }
	
	public TestV2Game() {
		Logger.setSeverity(-1);
    	FullscreenSetup.setFullscreen(this);
    	setTitle("Chronicle");
        EncounterGrid grid = new EncounterGrid(getWidth(), getHeight());
        Encounter encounter = new Encounter(grid);
		UIStack.push(grid);
        setUpCharacters(encounter);
        Arena arena = new Arena();
        arena.setup(encounter);
		//encounter.startEncounter();
		
		//TitleDisplay title = new TitleDisplay("This is an awesome title. Also, Cody's a bitch.", UIPosition.TOP_LEFT);
		//UIStack.push(title);
		
		LogDisplay.log("Cody really is a bitch.");
		LogDisplay.log("No, seriously.");
	}
	
	public void setUpCharacters(Encounter encounter) {
		Character cody = new Character();
		cody.setTeam(Team.getPlayerTeam());
		cody.setController(Controller.getPlayerController());
		cody.setName("Cody");
		cody.setX(5);
		cody.setY(8);
		Character drew = new Character();
		drew.setTeam(Team.getPlayerTeam());
		drew.setController(Controller.getPlayerController());
		drew.setName("Drew");
		drew.setX(3);
		drew.setY(8);
		Character jason = new Character();
		jason.setTeam(Team.getEnemyTeam());
		jason.setName("Jason");
		jason.setX(11);
		jason.setY(8);
		jason.setRole(RoleName.WARP, 0);
		jason.setRoleLevel(0, 3);
		Character cheyenne = new Character();
		cheyenne.setTeam(Team.getEnemyTeam());
		cheyenne.setName("Cheyenne");
		cheyenne.setX(13);
		cheyenne.setY(8);
		Character jody = new Character();
		jody.setTeam(Team.getNeutralTeam());
		jody.setName("Jody");
		jody.setX(8);
		jody.setY(2);
		jody.setRole(RoleName.PURGER, 0);

		
		encounter.add(cody);
		encounter.add(drew);
		encounter.add(jason);
		encounter.add(cheyenne);
		encounter.add(jody);
		
		Conversation conversation = new Conversation();
		conversation.add(new Statement(cody, "I'm all of the bitches."));
		conversation.add(new Statement(cody, "Seriously, though. All of them."));
		conversation.add(new Statement(jason, "Yup. You are."));
		conversation.add(new Statement(jody, "Yup. I should know, I'm your brother."));
		
		UIStack.push(conversation);
	}
	
}
