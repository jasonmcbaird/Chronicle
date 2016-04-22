package storyline;

import java.awt.EventQueue;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

import maps.Arena;
import ui.UIStack;
import ui.grid.EncounterGrid;
import character.Character;
import character.Team;
import encounter.Encounter;
import enums.RoleName;
import enums.Stat;

public class Chronicle extends JFrame {
	
	public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Chronicle chronicle = new Chronicle();
                chronicle.setVisible(true);
            }
        });
    }
	
	private Encounter encounter;
	
	public Chronicle() {
        initUI();
    }
	
	private void initUI() {
		JPanel mainPanel = new JPanel();
		add(mainPanel);
    	setResizable(false);
        EncounterGrid grid = new EncounterGrid();
        UIStack.setMainPanel(mainPanel);
		UIStack.push(grid);
    	encounter = new Encounter(grid);
    	Arena arena = new Arena();
    	arena.setup(encounter);
        setTitle("Chronicle");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        twoVersusTwo();
        //war();
        encounter.startEncounter();
    }
	
	private void twoVersusTwo() {
		Character psion = new Character();
		psion.setCharacterImage(new File("res/FELordSprites/FemaleRobin.png"));
        psion.setName("Psion");
        psion.attributes.setStat(Stat.INTELLECT, 7);
        psion.attributes.setStat(Stat.INTUITION, 6);
        psion.setRole(RoleName.PSION, 0);
        psion.setRoleLevel(0, 10);
        psion.setRole(RoleName.WARP, 1);
        psion.setRoleLevel(1, 0);
        psion.setX(4);
        psion.setY(9);
        psion.setIsPlayer(true);
        encounter.add(psion);
        psion.setTeam(Team.getPlayerTeam());
        
        Character mage = new Character();
		mage.setCharacterImage(new File("res/FELordSprites/MaleRobin.png"));
        mage.setName("Mage");
        mage.attributes.setStat(Stat.INTELLECT, 7);
        mage.attributes.setStat(Stat.WILL, 6);
        mage.setRole(RoleName.MAGE, 0);
        mage.setRoleLevel(0, 5);
        mage.setX(4);
        mage.setY(7);
        encounter.add(mage);
        mage.setTeam(Team.getPlayerTeam());
        mage.setIsPlayer(true);
        
        Character berserker = new Character();
		berserker.setCharacterImage(new File("res/FELordSprites/Ike.png"));
        berserker.setName("Berserker");
        berserker.attributes.setStat(Stat.STRENGTH, 7);
        berserker.attributes.setStat(Stat.TOUGHNESS, 6);
        berserker.setRole(RoleName.BERSERKER, 0);
        berserker.setRoleLevel(0, 6);
        berserker.setRole(RoleName.ASSASSIN, 1);
        berserker.setRoleLevel(1, 2);
        berserker.setX(12);
        berserker.setY(7);
        berserker.setTeam(Team.getEnemyTeam());
        encounter.add(berserker);
        
        Character purger = new Character();
		purger.setCharacterImage(new File("res/FELordSprites/Seliph.png"));
        purger.setName("Purger");
        purger.attributes.setStat(Stat.STRENGTH, 7);
        purger.attributes.setStat(Stat.WILL, 6);
        purger.setRole(RoleName.PURGER, 0);
        purger.setRoleLevel(0, 5);
        purger.setX(12);
        purger.setY(9);
        purger.setTeam(Team.getEnemyTeam());
        encounter.add(purger);
	}
    
    private void war() {
    	
    	// Blue Soldiers
    	for(int i = 1; i < 20; i++) {
    		Character soldier = new Character();
            soldier.setName("Soldier " + i);
            soldier.attributes.setStat(Stat.STRENGTH, 7);
            soldier.setRole(RoleName.WARRIOR, 0);
            soldier.setRoleLevel(0, 2);
            soldier.setTeam(Team.getPlayerTeam());
            soldier.setX(i);
            soldier.setY(1);
            encounter.add(soldier);
            
            Character rider = new Character();
            rider.setName("Rider " + i);
            rider.attributes.setStat(Stat.AGILITY, 6);
            rider.setRole(RoleName.SYLVAN, 0);
            rider.setRoleLevel(0, 2);
            rider.setX(i);
            rider.setY(8);
            encounter.add(rider);
    	}
    	
    	Character commander = new Character();
        commander.setName("Commander");
        commander.attributes.setStat(Stat.INTELLECT, 7);
        commander.attributes.setStat(Stat.WILL, 6);
        commander.attributes.setStat(Stat.STRENGTH, 6);
        commander.setRole(RoleName.MAGE, 0);
        commander.setRoleLevel(0, 6);
        commander.setRole(RoleName.BERSERKER, 1);
        commander.setRoleLevel(1, 5);
        commander.setTeam(Team.getPlayerTeam());
        // commander.setIsPlayer(true);
        commander.setX(5);
        commander.setY(3);
        encounter.add(commander);
    	
    	Character runner = new Character();
        runner.setName("Runner");
        runner.attributes.setStat(Stat.INTELLECT, 7);
        runner.attributes.setStat(Stat.INTUITION, 6);
        runner.attributes.setStat(Stat.STRENGTH, 6);
        runner.setRole(RoleName.PSION, 0);
        runner.setRoleLevel(0, 6);
        runner.setRole(RoleName.PURGER, 1);
        runner.setRoleLevel(1, 5);
        runner.setX(5);
        runner.setY(6);
        encounter.add(runner);
    }
    
    
}