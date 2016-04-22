package roles.warp;

import items.Equipment;
import items.weapons.BlackKnife;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import character.Energy;
import character.Role;
import enums.EnergyType;
import enums.RoleName;

public class Warp extends Role {
	
	// TODO: If you have seconds, your turn doesn't end whenever you use an ability; instead, it ends when you're out of Seconds.
	// 		To go with this, EndTurn should always use the queue.
	// Options: 1. TryEndTurn queue, then EndTurn queue.
	//			2. PreventEndTurn events have high priority and abort the entire queue when run.
	//			3. Abilities have a check for whether they end the turn or not. The Seconds ability converts all abilities to
	//				not end the turn when they run.
	//			I like 3 best right now.
	// TODO: New level 0 ability: Wait. Burns your remaining seconds.
	// TODO: When you run out of seconds, end your turn.
	
	private Energy secondsBar;
	
	public Warp() {
		createAbilities();
		setName("Warp");
		secondsBar = new Energy(EnergyType.SECONDS);
		secondsBar.setMaxEnergy(6);
		resetEnergy();
	}
	
	private void createAbilities() {
		
		Seconds seconds = new Seconds();
		abilityLevels.put(seconds, 0);
		
		Backstab backstab = new Backstab();
		abilityLevels.put(backstab, 0);
	}
	
	public boolean gainEnergy(EnergyType energyType, int amount) {
		if(energyType == EnergyType.SECONDS) {
			if(amount < 0 && Math.abs(amount) > secondsBar.getEnergy()) {
				return false;
			}
			secondsBar.addEnergy(amount);
			return true;
		}
		return false;
	}
	
	public int getEnergy(EnergyType energyType) {
		if(energyType == EnergyType.SECONDS) {
			return secondsBar.getEnergy();
		}
		return 0;
	}
	
	public void resetEnergy() {
		secondsBar.setEnergyToMax();
	}
	
	public void resetEnergy(EnergyType energyType) {
		if(energyType == EnergyType.SECONDS) {
			secondsBar.setEnergyToMax();
		}
	}
	
	public int getMaxEnergy(EnergyType energyType) {
		if(energyType == EnergyType.SECONDS) {
			return secondsBar.getMaxEnergy();
		}
		return 0;
	}
	
	public void setMaxEnergy(EnergyType energyType, int i) {
		if(energyType == EnergyType.SECONDS)
			secondsBar.setMaxEnergy(i);
	}
	
	public RoleName getRoleName() {
		return RoleName.WARP;
	}
	
	public BufferedImage getDefaultImage() {
		try {
			return ImageIO.read(this.getClass().getResource("/res/feLordSprites/ArmedAnna.png"));
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
		return "One with an intuitive grasp over space and time and how to move through them.";
	}
}