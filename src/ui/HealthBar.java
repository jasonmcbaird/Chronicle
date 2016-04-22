package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import character.Character;

public class HealthBar extends JPanel implements Bar {
		
	private Character character;
	
	public HealthBar(Character character) {
		this.character = character;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		setBounds(character.getX()*32 - 1 , (character.getY()+1)*32 - 5, 33, 5);
		g.setColor(Color.WHITE);
		g.fillRoundRect(0, 0, 32, 4, 1, 1);
		g.setColor(Color.BLACK);
		g.drawRoundRect(0, 0, 32, 4, 1, 1);
		Color color = getColor();
		g.setColor(color);
		g.fillRoundRect(0, 0, (int) Math.ceil(32*getHealthFraction(character)), 4, 1, 1);
	}
	
	private double getHealthFraction(Character character) {
		return (double)character.attributes.getCurrentHealth() / character.attributes.getMaxHealth();
	}
	
	public Character getCharacter() {
		return character;
	}
	
	private Color getColor() {
		if(character.hasActed())
			return Color.GRAY;
		else
			return character.getTeam().getColor();
	}
}
