package ui.conversation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ui.InputResponder;
import ui.UIComponent;
import ui.UIStack;
import enums.Direction;

public class Conversation extends UIComponent implements InputResponder {
	
	private final int conversationHeight = 100;
	private final int fontSize = 24;
	
	private ArrayList<Statement> statements = new ArrayList<Statement>();
	
	public Conversation() {
		setOpaque(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		removeAll();
		setBounds(0, getParent().getHeight() - conversationHeight, getParent().getWidth(), conversationHeight);
		paintConversationBox(g);
		paintImage(g);
		paintStatement(g);
	}

	public void inputDirection(Direction direction) {
		
	}

	public void inputAccept() {
		if(statements.size() > 0)
			statements.remove(0);
		if(statements.size() <= 0)
			UIStack.pop(this);
		repaint();
	}

	public void inputCancel() {
		
	}
	
	public void inputInfo() {
		
	}
	
	public Statement getStatement() {
		return statements.get(0);
	}
	
	public void add(Statement statement) {
		statements.add(statement);
	}
	
	public void display(JPanel jPanel) {
		jPanel.add(this);
		jPanel.revalidate();
		jPanel.repaint();
	}
	
	public void paintConversationBox(Graphics g) {
		g.setColor(Color.WHITE);
        g.fillRoundRect(0, 0, getParent().getWidth(), conversationHeight, 30, 30);
		g.setColor(Color.BLACK);
        g.drawRoundRect(0, 0, getParent().getWidth(), conversationHeight, 30, 30);
	}
	
	public void paintImage(Graphics g) {
		BufferedImage image = statements.get(0).getImage();
		if(image != null)
			g.drawImage(image, 10, conversationHeight/2 - image.getHeight()/2, null);
	}
	
	public void paintStatement(Graphics g) {
		JLabel jlabel = new JLabel(statements.get(0).getText(), SwingConstants.CENTER);
        jlabel.setBounds(3, 3, getWidth() - 6, getHeight() - 3);
        jlabel.setFont(new Font("Verdana", 1, fontSize));
        add(jlabel);
	}
}
