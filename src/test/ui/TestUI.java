package test.ui;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import maps.Arena;
import ui.Menu;
import ui.UIStack;
import ui.grid.CursorMode;
import ui.grid.EncounterGrid;
import enums.UIPosition;

public class TestUI extends JFrame {
	
	private JPanel mainPanel;
	
	public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TestUI testUI = new TestUI();
                testUI.setVisible(true);
            }
        });
    }
	
	public TestUI() {
		mainPanel = new JPanel();
		add(mainPanel);
    	//setResizable(false);
    	setTitle("Chronicle");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //mainPanel.setSize(getSize());
        //mainPanel.setBounds(getBounds());
        //Logger.print(mainPanel.getSize() + "");
        EncounterGrid grid = new EncounterGrid();
        grid.setMode(new CursorMode(grid));
        UIStack.setMainPanel(mainPanel);
		UIStack.push(grid);
		Menu menu = new Menu(UIPosition.RIGHT);
		ArrayList<String> items = new ArrayList<String>();
		items.add("Item 1");
		items.add("Item 2");
		menu.setItems(items);
		UIStack.push(menu);
		Menu menu2 = new Menu(UIPosition.LEFT);
		items = new ArrayList<String>();
		items.add("Right Item 1");
		items.add("Right Item 2");
		menu2.setItems(items);
		UIStack.push(menu2);
	}
	
}
