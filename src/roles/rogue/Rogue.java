package roles.rogue;

import items.Equipment;
import items.weapons.PiratesRapier;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import character.Energy;
import character.Role;
import enums.EnergyType;
import enums.RoleName;

public class Rogue extends Role {
	
	private Energy rhythm;
	
	public Rogue() {
		createAbilities();
		setName("Rogue");
		rhythm = new Energy(EnergyType.RHYTHM);
		rhythm.setMaxEnergy(7);
		resetEnergy();
	}
	
	private void createAbilities() {
		
		abilityLevels.put(new Mug(), 0);
	}
	
	public boolean gainEnergy(EnergyType energyType, int amount) {
		if(energyType == EnergyType.RHYTHM) {
			if(amount < 0 && Math.abs(amount) > rhythm.getEnergy()) {
				return false;
			}
			rhythm.addEnergy(amount);
			return true;
		}
		return false;
	}
	
	public int getEnergy(EnergyType energyType) {
		if(energyType == EnergyType.RHYTHM) {
			return rhythm.getEnergy();
		}
		return 0;
	}
	
	public void resetEnergy() {
		rhythm.setEnergy(0);
	}
	
	public void resetEnergy(EnergyType energyType) {
		if(energyType == EnergyType.RHYTHM)
			rhythm.setEnergy(0);
	}
	
	public int getMaxEnergy(EnergyType energyType) {
		if(energyType == EnergyType.RHYTHM) {
			return rhythm.getMaxEnergy();
		}
		return 0;
	}
	
	public void setMaxEnergy(EnergyType energyType, int i) {
		if(energyType == EnergyType.RHYTHM)
			rhythm.setMaxEnergy(i);
	}
	
	public RoleName getRoleName() {
		return RoleName.ROGUE;
	}
	
	public BufferedImage getDefaultImage() {
		try {
			return ImageIO.read(this.getClass().getResource("/res/feLordSprites/Anna.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Equipment getBasicWeapon() {
		return new PiratesRapier();
	}
	
	public String getInfo() {
		return "A dashing swashbuckler, thief, and general scoundrel.";
	}
}