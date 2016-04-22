package roles.berserker;

import items.Equipment;
import items.weapons.Greataxe;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import character.Energy;
import character.Role;
import enums.EnergyType;
import enums.RoleName;

public class Berserker extends Role {
	
	private Energy rageBar;
	final private int rageMax = 10;
	
	public Berserker() {
		createAbilities();
		setName("Berserker");
		rageBar = new Energy(EnergyType.RAGE);
		rageBar.setMaxEnergy(rageMax);
		rageBar.setEnergy(0);
	}
	
	private void createAbilities() {
		
		abilityLevels.put(new Wrath(), 0);
		
		Rage rage = new Rage();
		abilityLevels.put(rage, 1);
		
		Gut gut = new Gut();
		abilityLevels.put(gut, 2);
		
		Fury fury = new Fury();
		abilityLevels.put(fury, 5);
	}
	
	public boolean gainEnergy(EnergyType energyType, int amount) {
		if(energyType == EnergyType.RAGE) {
			if(amount < 0 && Math.abs(amount) > rageBar.getEnergy()) {
				return false;
			}
			rageBar.addEnergy(amount);
			return true;
		}
		return false;
	}
	
	public int getEnergy(EnergyType energyType) {
		if(energyType == EnergyType.RAGE) {
			return rageBar.getEnergy();
		}
		return 0;
	}
	
	public int getMaxEnergy(EnergyType energyType) {
		if(energyType == EnergyType.RAGE) {
			return rageBar.getMaxEnergy();
		}
		return 0;
	}
	
	public void setMaxEnergy(EnergyType energyType, int i) {
		if(energyType == EnergyType.RAGE)
			rageBar.setMaxEnergy(i);
	}
	
	public void resetEnergy() {
		rageBar.setEnergy(0);
	}
	
	public void resetEnergy(EnergyType energyType) {
		if(energyType == EnergyType.RAGE)
			rageBar.setEnergy(0);
	}
	
	public RoleName getRoleName() {
		return RoleName.BERSERKER;
	}
	
	public BufferedImage getDefaultImage() {
		try {
			return ImageIO.read(this.getClass().getResource("/res/feLordSprites/Ike.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Equipment getBasicWeapon() {
		return new Greataxe();
	}
	
	public String getInfo() {
		return "An insane fighter that flies into uncontrollable rage.";
	}
}