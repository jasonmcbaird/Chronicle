package roles.assassin;

import items.Equipment;
import items.weapons.BlackKnife;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import character.Energy;
import character.Role;
import enums.EnergyType;
import enums.RoleName;

public class Assassin extends Role {
	
	private Energy patienceBar;
	final private int patienceMax = 6;
	
	public Assassin() {
		createAbilities();
		setName("Assassin");
		patienceBar = new Energy(EnergyType.PATIENCE);
		patienceBar.setMaxEnergy(patienceMax);
		patienceBar.setEnergy(0);
	}
	
	private void createAbilities() {
		
		Patience patience = new Patience();
		abilityLevels.put(patience, 0);
		
		abilityLevels.put(new Vanish(), 0);
		
		Rush sprint = new Rush();
		abilityLevels.put(sprint, 2);
		
	}
	
	public boolean gainEnergy(EnergyType energyType, int amount) {
		if(energyType == EnergyType.PATIENCE) {
			if(amount < 0 && Math.abs(amount) > patienceBar.getEnergy()) {
				return false;
			}
			patienceBar.addEnergy(amount);
			return true;
		}
		return false;
	}
	
	public int getEnergy(EnergyType energyType) {
		if(energyType == EnergyType.PATIENCE) {
			return patienceBar.getEnergy();
		}
		return 0;
	}
	
	public int getMaxEnergy(EnergyType energyType) {
		if(energyType == EnergyType.PATIENCE) {
			return patienceBar.getMaxEnergy();
		}
		return 0;
	}
	
	public void setMaxEnergy(EnergyType energyType, int i) {
		if(energyType == EnergyType.PATIENCE)
			patienceBar.setMaxEnergy(i);
	}
	
	public void resetEnergy() {
		patienceBar.setEnergy(0);
	}
	
	public void resetEnergy(EnergyType energyType) {
		if(energyType == EnergyType.PATIENCE) {
			patienceBar.setEnergy(0);
		}
	}
	
	public RoleName getRoleName() {
		return RoleName.ASSASSIN;
	}
	
	public BufferedImage getDefaultImage() {
		try {
			return ImageIO.read(this.getClass().getResource("/res/feLordSprites/Sothe.png"));
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
		return "An artist of stealth and slaughter.";
	}
}