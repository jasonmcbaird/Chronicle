package roles.sylvan;

import items.Equipment;
import items.weapons.Greataxe;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import character.Energy;
import character.Role;
import enums.EnergyType;
import enums.RoleName;

public class Sylvan extends Role {
	
	private Energy herbs;
	
	public Sylvan() {
		createAbilities();
		setName("Sylvan");
		herbs = new Energy(EnergyType.HERBS);
		herbs.setMaxEnergy(8);
		resetEnergy();
	}
	
	private void createAbilities() {
		
		abilityLevels.put(new Forage(), 0);
		
		abilityLevels.put(new Morph(), 0);
		
		Vigor vigor = new Vigor();
		abilityLevels.put(vigor, 2);
		
	}
	
	public boolean gainEnergy(EnergyType energyType, int amount) {
		if(energyType == EnergyType.HERBS) {
			if(amount < 0 && Math.abs(amount) > herbs.getEnergy()) {
				return false;
			}
			herbs.addEnergy(amount);
			return true;
		}
		return false;
	}
	
	public int getEnergy(EnergyType energyType) {
		if(energyType == EnergyType.HERBS) {
			return herbs.getEnergy();
		}
		return 0;
	}
	
	public void resetEnergy() {
		herbs.setEnergyToMax();
	}
	
	public void resetEnergy(EnergyType energyType) {
		if(energyType == EnergyType.HERBS) {
			herbs.setEnergyToMax();
		}
	}
	
	public int getMaxEnergy(EnergyType energyType) {
		if(energyType == EnergyType.HERBS) {
			return herbs.getMaxEnergy();
		}
		return 0;
	}
	
	public void setMaxEnergy(EnergyType energyType, int i) {
		if(energyType == EnergyType.HERBS)
			herbs.setMaxEnergy(i);
	}
	
	public RoleName getRoleName() {
		return RoleName.SYLVAN;
	}
	
	public BufferedImage getDefaultImage() {
		try {
			return ImageIO.read(this.getClass().getResource("/res/feLordSprites/Marth.png"));
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
		return "An herbalist whose conconctions heal, destroy, and transform.";
	}
}