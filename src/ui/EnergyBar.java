package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import utilities.Logger;
import character.Character;
import enums.EnergyType;

public class EnergyBar extends JPanel implements Bar {
	
	private Character character;
	private EnergyType energyType;
	private final int width = 32;
	private final int height = 10;
	
	public EnergyBar(Character character, EnergyType energyType) {
		Logger.print("Painting energy bar: " + character.getName() + "'s " + energyType, -1);
		this.character = character;
		this.energyType = energyType;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Logger.print("Painting energy bar: " + character.getName() + "'s " + energyType, -1);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width, height);
		g.setColor(EnergyType.getColor(energyType));
		g.fillRect(0, 0, (int) Math.ceil(width*getEnergyFraction(character, energyType)), height);
	}
	
	private double getEnergyFraction(Character character, EnergyType energyType) {
		return (double)character.getEnergy(energyType) / character.getMaxEnergy(energyType);
	}
	
	public Character getCharacter() {
		return character;
	}
	
	public int getBarHeight() {
		return height;
	}
	
	public int getBarWidth() {
		return width;
	}
}
