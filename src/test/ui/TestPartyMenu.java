package test.ui;

import items.accessories.CrisisGem;
import items.armors.Chainmail;
import items.weapons.BlackKnife;
import items.weapons.ColdIronLongsword;
import items.weapons.LegionsShield;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.UIStack;
import ui.grid.Grid;
import ui.party.PartyMenuResponder;
import utilities.Logger;
import arenaMode.FullscreenSetup;
import character.Character;
import character.Team;
import controller.Controller;
import dungeonMode.Party;
import enums.RoleName;
import enums.UIPosition;

public class TestPartyMenu extends JFrame {
	
	private JPanel mainPanel;
	
	public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TestPartyMenu testUI = new TestPartyMenu();
                testUI.setVisible(true);
            }
        });
    }
	
	public TestPartyMenu() {
		Logger.setSeverity(-1);
    	FullscreenSetup.setFullscreen(this);
    	setTitle("Chronicle");
        Grid grid = new Grid(getWidth(), getHeight());
        setUpCharacters();
		UIStack.push(grid);
		
		PartyMenuResponder partyMenu = new PartyMenuResponder(UIPosition.RIGHT);
	}
	
	public void setUpCharacters() {
		Character cody = new Character();
		cody.setTeam(Team.getPlayerTeam());
		cody.setController(Controller.getPlayerController());
		cody.setRole(RoleName.PSION, 0);
		cody.setRole(RoleName.ARTIFICER, 1);
		cody.setRole(RoleName.MAGE, 2);
		cody.setName("Cody");
		cody.setX(5);
		cody.setY(8);
		cody.inventory.equip(new BlackKnife());
		cody.inventory.add(new CrisisGem());
		cody.inventory.equip(new LegionsShield());
		cody.inventory.equip(new Chainmail());
		cody.inventory.equip(new ColdIronLongsword());
		cody.setGeneralLevel(30);
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
		
		Party.addCharacter(cody);
		Party.addCharacter(drew);
	}
	
}
