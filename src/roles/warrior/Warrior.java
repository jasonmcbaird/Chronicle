package roles.warrior;

import items.Equipment;
import items.weapons.ColdIronLongsword;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import character.Energy;
import character.Role;
import enums.EnergyType;
import enums.RoleName;

public class Warrior extends Role {
	
	private Energy stamina;
	
	public Warrior() {
		createAbilities();
		setName("Warrior");
		stamina = new Energy(EnergyType.STAMINA);
		stamina.setMaxEnergy(5);
		resetEnergy();
	}
	
	private void createAbilities() {
		
		abilityLevels.put(new Stamina(), 0);
		
		Bash bash = new Bash();
		abilityLevels.put(bash, 0);
		
		Crash crash = new Crash();
		abilityLevels.put(crash, 4);
	}
	
	public boolean gainEnergy(EnergyType energyType, int amount) {
		if(energyType == EnergyType.STAMINA) {
			if(amount < 0 && Math.abs(amount) > stamina.getEnergy())
				return false;
			stamina.addEnergy(amount);
			return true;
		}
		return false;
	}
	
	public int getEnergy(EnergyType energyType) {
		if(energyType == EnergyType.STAMINA) {
			return stamina.getEnergy();
		}
		return 0;
	}
	
	public void resetEnergy() {
		stamina.setEnergyToMax();
	}
	
	public void resetEnergy(EnergyType energyType) {
		if(energyType == EnergyType.STAMINA) {
			stamina.setEnergyToMax();
		}
	}
	
	public int getMaxEnergy(EnergyType energyType) {
		if(energyType == EnergyType.STAMINA) {
			return stamina.getMaxEnergy();
		}
		return 0;
	}
	
	public void setMaxEnergy(EnergyType energyType, int i) {
		if(energyType == EnergyType.STAMINA)
			stamina.setMaxEnergy(i);
	}
	
	public RoleName getRoleName() {
		return RoleName.WARRIOR;
	}
	
	public BufferedImage getDefaultImage() {
		try {
			return ImageIO.read(this.getClass().getResource("/res/feLordSprites/Nephenee.png"));
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
		return "An expert soldier, capable of massive feats of strength and grit.";
	}
}