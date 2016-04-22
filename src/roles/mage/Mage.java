package roles.mage;

import items.Equipment;
import items.weapons.BlackKnife;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import character.Energy;
import character.Role;
import enums.EnergyType;
import enums.RoleName;

public class Mage extends Role {
	
	private Energy manaBar;
	
	public Mage() {
		createAbilities();
		setName("Mage");
		manaBar = new Energy(EnergyType.MANA);
		manaBar.setMaxEnergy(10);
		resetEnergy();
	}
	
	private void createAbilities() {
		
		Firebolt firebolt = new Firebolt();
		abilityLevels.put(firebolt, 0);
		
		Innovation innovation = new Innovation();
		abilityLevels.put(innovation, 1);
		
		Thunderbolt thunderbolt = new Thunderbolt();
		abilityLevels.put(thunderbolt, 2);
		
		Frostbolt frostbolt = new Frostbolt();
		abilityLevels.put(frostbolt, 4);
		
		ManaTide manaTide = new ManaTide();
		abilityLevels.put(manaTide, 7);
	}
	
	public boolean gainEnergy(EnergyType energyType, int amount) {
		if(energyType == EnergyType.MANA) {
			if(amount < 0 && Math.abs(amount) > manaBar.getEnergy()) {
				return false;
			}
			manaBar.addEnergy(amount);
			return true;
		}
		return false;
	}
	
	public int getEnergy(EnergyType energyType) {
		if(energyType == EnergyType.MANA) {
			return manaBar.getEnergy();
		}
		return 0;
	}
	
	public void resetEnergy() {
		manaBar.setEnergyToMax();
	}
	
	public void resetEnergy(EnergyType energyType) {
		if(energyType == EnergyType.MANA) {
			manaBar.setEnergyToMax();
		}
	}
	
	public int getMaxEnergy(EnergyType energyType) {
		if(energyType == EnergyType.MANA) {
			return manaBar.getMaxEnergy();
		}
		return 0;
	}
	
	public void setMaxEnergy(EnergyType energyType, int i) {
		if(energyType == EnergyType.MANA)
			manaBar.setMaxEnergy(i);
	}
	
	public RoleName getRoleName() {
		return RoleName.MAGE;
	}
	
	public BufferedImage getDefaultImage() {
		try {
			return ImageIO.read(this.getClass().getResource("/res/feLordSprites/Micaiah.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Equipment getBasicWeapon() {
		return new BlackKnife();
	}
	
	public String getInfo() {
		return "A studied wizard with control of the elements.";
	}
}