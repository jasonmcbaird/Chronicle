package roles.general;

import items.Equipment;
import items.weapons.ColdIronLongsword;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import character.Energy;
import character.Role;
import enums.EnergyType;
import enums.RoleName;

public class General extends Role {
	
	Energy concentration;
	
	public General() {
		createAbilities();
		setName("General");
		concentration = new Energy(EnergyType.CONCENTRATION);
		concentration.setMaxEnergy(10);
		resetEnergy();
	}
	
	private void createAbilities() {
		
		Recruit recruit = new Recruit();
		abilityLevels.put(recruit, 0);
	}
	
	public boolean gainEnergy(EnergyType energyType, int amount) {
		if(energyType == EnergyType.CONCENTRATION) {
			if(amount < 0 && Math.abs(amount) > concentration.getEnergy()) {
				return false;
			}
			concentration.addEnergy(amount);
			return true;
		}
		return false;
	}
	
	public int getEnergy(EnergyType energyType) {
		if(energyType == EnergyType.CONCENTRATION) {
			return concentration.getEnergy();
		}
		return 0;
	}
	
	public void resetEnergy() {
		concentration.setEnergyToMax();
	}
	
	public void resetEnergy(EnergyType energyType) {
		if(energyType == EnergyType.CONCENTRATION) {
			concentration.setEnergyToMax();
		}
	}
	
	public int getMaxEnergy(EnergyType energyType) {
		if(energyType == EnergyType.CONCENTRATION) {
			return concentration.getMaxEnergy();
		}
		return 0;
	}
	
	public void setMaxEnergy(EnergyType energyType, int i) {
		if(energyType == EnergyType.CONCENTRATION)
			concentration.setMaxEnergy(i);
	}
	
	public RoleName getRoleName() {
		return RoleName.GENERAL;
	}
	
	public BufferedImage getDefaultImage() {
		try {
			return ImageIO.read(this.getClass().getResource("/res/feLordSprites/Sigurd.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Equipment getBasicWeapon() {
		return new ColdIronLongsword();
	}
	
	public String getInfo() {
		return "";
	}
}