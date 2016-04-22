package roles.necromancer;

import items.Equipment;
import items.weapons.BlackKnife;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import character.Energy;
import character.Role;
import enums.EnergyType;
import enums.RoleName;

public class Necromancer extends Role {
	
	private Energy manaBar;
	
	public Necromancer() {
		createAbilities();
		setName("Necromancer");
		manaBar = new Energy(EnergyType.MANA);
		manaBar.setMaxEnergy(8);
		resetEnergy();
	}
	
	private void createAbilities() {
		
		RaiseDead raiseDead = new RaiseDead();
		abilityLevels.put(raiseDead, 0);
		
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
		return RoleName.NECROMANCER;
	}
	
	public BufferedImage getDefaultImage() {
		try {
			return ImageIO.read(this.getClass().getResource("/res/feLordSprites/HoodedRobin.png"));
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
		return "A vile wizard studied in animating the dead and freezing the living.";
	}
}