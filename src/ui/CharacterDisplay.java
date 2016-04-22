package ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.Logger;
import utilities.TextExtension;
import character.Character;
import character.Role;
import character.Team;
import enums.EnergyType;
import enums.Stat;

public class CharacterDisplay extends Display {
	
	private CharacterInterface character;
	private ArrayList<JLabel> labels;
	private int textWidth = 100;
	private int textHeight = 16;
	private int fontSize = 14;
	
	public CharacterDisplay(CharacterInterface character) {
		this.character = character;
	}
	
	public void display(JPanel jPanel) {
		jPanel.add(this);
		jPanel.revalidate();
		jPanel.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Logger.print("Repainting a CharacterDisplay for " + character.getName(), -1);
		
		removeAll();
				
		labels = new ArrayList<JLabel>();
		
		//paintBackground(g);
		paintName();
		paintRelationship();
		paintHealth();
		paintRoles();
		paintStats();
		
		setBounds(getParent().getWidth() - textWidth - 8, 4, textWidth + 4, (labels.size()) * textHeight + textHeight / 2 + 2);
	}
	
	private void paintName() {
		paintLabel(character.getName() + " " + character.getGeneralLevel(), 18);
		paintLabel("");
	}
	
	private void paintRelationship() {
		paintLabel(TextExtension.toFirstCase(character.getRelationship(Team.getPlayerTeam()).toString()));
	}
	
	private void paintHealth() {
		paintLabel("Health: " + character.getHealth() + "/" + character.getMaxHealth());
	}
	
	private void paintRoles() {
		paintLabel("");
		for(Role role: character.getRoles()) {
			paintRole(role);
			paintEnergy(role);
		}
	}
	
	private void paintRole(Role role) {
		paintLabel(TextExtension.toFirstCase(role.getName()) + " " + role.getLevel());
	}
	
	private void paintEnergy(Role role) {
		for(EnergyType energyType: EnergyType.getEnergyTypes()) {
			int energy = role.getEnergy(energyType);
			int maxEnergy = role.getMaxEnergy(energyType);
			if(maxEnergy > 0) {
				String energyName = energyType.toString();
				paintLabel("       " + TextExtension.toFirstCase(energyName) + ": " + energy + "/" + maxEnergy);
				paintEnergyBar(energyType);
				return;
			}
		}
		return;
	}
	
	private void paintEnergyBar(EnergyType energyType) {
		EnergyBar energyBar = new EnergyBar((Character) character, energyType);
		energyBar.setBounds(2, (labels.size()) * textHeight - energyBar.getBarHeight() / 2, energyBar.getBarWidth(), energyBar.getBarHeight());
		add(energyBar);
	}
	
	private void paintStats() {
		paintLabel("");
		for(Stat stat: Stat.getMajorStats())
			paintStat(stat);
		paintLabel("");
		for(Stat stat: Stat.getDerivedStats())
			paintStat(stat);
	}
	
	private void paintStat(Stat stat) {
		paintLabel(TextExtension.toFirstCase(stat.toString()) + ": " + character.getStat(stat));
	}
	
	private JLabel paintLabel(String string) {
		return paintLabel(string, fontSize);
	}
	
	private JLabel paintLabel(String string, int i) {
		JLabel jLabel = new JLabel(string);
        labels.add(jLabel);
        jLabel.setBounds(2, labels.indexOf(jLabel) * textHeight, textWidth, textHeight * 2);
        Font font = new Font("Verdana", 1, i);
        jLabel.setFont(font);
        matchTextWidth(string, font);
        add(jLabel);
        return jLabel;
	}
	
	private void paintBackground(Graphics g) {
		try {
			BufferedImage background = ImageIO.read(this.getClass().getResource("/res/ui/paper.jpg"));
			g.drawImage(background, 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void matchTextWidth(String string, Font font) {
		int newWidth = (int) font.getStringBounds(string, new FontRenderContext(font.getTransform(), false, false)).getBounds().getWidth();
		if(textWidth < newWidth)
			textWidth = newWidth;
	}
}
