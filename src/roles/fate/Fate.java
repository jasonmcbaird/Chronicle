package roles.fate;

import items.Equipment;
import items.weapons.ColdIronLongsword;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import character.Energy;
import character.Role;
import enums.EnergyType;
import enums.RoleName;

public class Fate extends Role {
	
	private Energy manaBar;
	
	public Fate() {
		createAbilities();
		setName("Fate");
		manaBar = new Energy(EnergyType.MANA);
		manaBar.setMaxEnergy(6);
		resetEnergy();
	}
	
	private void createAbilities() {
		
		abilityLevels.put(new Blight(), 0);
		
		TwistFate twistFate = new TwistFate();
		abilityLevels.put(twistFate, 5);
		
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
		return RoleName.FATE;
	}
	
	public BufferedImage getDefaultImage() {
		try {
			return ImageIO.read(this.getClass().getResource("/res/feLordSprites/Lucina.png"));
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
		return "One with power over life and death. Fates have returned from beyond death.";
	}
}