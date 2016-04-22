package ui;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import enums.UIPosition;

public class TitleDisplay extends Display {
	
	private UIPosition position;
	private String text;
	private int width;
	private int height = 25;
	private int xPosition;
	private int yPosition;
	private int fontSize = 20;
	
	public TitleDisplay(String string, UIPosition position) {
		text = string;
		this.position = position;
	}
	
	public void display(JPanel jPanel) {
		width = jPanel.getPreferredSize().width * 3 / 4;
		setPosition(position, jPanel);
		setOpaque(true);
		jPanel.add(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		removeAll();
		paintLabel(g);
		setBounds(xPosition, yPosition, width + 1, height + 1);
	}
	
	private void paintLabel(Graphics g) {
		JLabel jlabel = new JLabel(text, SwingConstants.CENTER);
        jlabel.setBounds(0, 0, width + 1, height + 1);
        jlabel.setFont(new Font("Verdana", 1, fontSize));
        add(jlabel);
	}
	
	private void setPosition(UIPosition position, JPanel jPanel) {
		switch (position) {
		case LEFT:
			xPosition = 0;
			yPosition = jPanel.getPreferredSize().height / 2 - height / 2;
			return;
		case RIGHT:
			xPosition = jPanel.getPreferredSize().width - width;
			yPosition = jPanel.getPreferredSize().height / 2 - height / 2;
			return;
		case TOP:
			xPosition = jPanel.getPreferredSize().width / 2 - width / 2;
			yPosition = 0;
			return;
		case BOTTOM:
			xPosition = jPanel.getPreferredSize().width / 2 - width / 2;
			yPosition = jPanel.getPreferredSize().height - height;
			return;
		case TOP_LEFT:
			xPosition = 0;
			yPosition = 0;
			return;
		case BOTTOM_LEFT:
			xPosition = 0;
			yPosition = jPanel.getPreferredSize().height - height;
			return;
		default:
			throw new UnsupportedOperationException("Tried to make a TitleDisplay with an unsupported UIPosition: " + position);
		}
	}

}
