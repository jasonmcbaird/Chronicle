package ui;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogDisplay extends Display {
	
	private static LogDisplay logDisplay;
	private ArrayList<String> text = new ArrayList<String>();
	private int width;
	private int textHeight = 16;
	private int xPosition = 0;
	private int yPosition;
	private int fontSize = 14;
	private final int displayAmount = 4;
	
	public static void log(String string) {
		if(UIStack.getMainPanel() == null)
			return;
		if(logDisplay == null) {
			logDisplay = new LogDisplay();
			UIStack.push(logDisplay);
		}
		logDisplay.addText(string);
	}
	
	private void addText(String string) {
		text.add(string);
		if(text.size() > displayAmount)
			text.remove(0);
	}
	
	public void display(JPanel jPanel) {
		width = jPanel.getPreferredSize().width * 3 / 4;
		//setOpaque(false);
		jPanel.add(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		removeAll();
		int offset = 0;
		int height = text.size() * textHeight;
		yPosition = getParent().getPreferredSize().height - height;
		setBounds(xPosition, yPosition, width + 1, height + 1);
		for(String string: text) {
			paintLabel(g, string, offset, height);
			offset++;
		}
	}
	
	private void paintLabel(Graphics g, String string, int offset, int height) {
		JLabel jLabel = new JLabel(string);
        jLabel.setBounds(0, height - ((offset + 1) * textHeight), width + 1, textHeight + 1);
        jLabel.setFont(new Font("Verdana", 1, fontSize));
        add(jLabel);
	}

}
