package test.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.UIStack;
import ui.party.ClassGrid;
import utilities.Logger;
import arenaMode.FullscreenSetup;
import character.Character;
import character.CharacterBuilder;

public class TestClassGrid extends JFrame {
	
	private JPanel mainPanel;
	
	public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TestClassGrid testUI = new TestClassGrid();
                testUI.setVisible(true);
            }
        });
    }
	
	public TestClassGrid() {
		Logger.setSeverity(-1);
    	FullscreenSetup.setFullscreen(this);
    	setTitle("Chronicle");
        ClassGrid grid = new ClassGrid();
        Character cody = CharacterBuilder.makeCharacter("Barbarian", 7);
        grid.setCharacter(cody);
		UIStack.push(grid);
	}
	
}
