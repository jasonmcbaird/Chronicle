package roles.warlock;

import items.Equipment;
import items.weapons.BlackKnife;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import character.Role;
import enums.RoleName;

public class Warlock extends Role {
	
	public Warlock() {
		createAbilities();
		setName("Warlock");
	}
	
	private void createAbilities() {
		
		Dreadbolt dreadbolt = new Dreadbolt();
		abilityLevels.put(dreadbolt, 0);
		
		LifeSiphon lifeSiphon = new LifeSiphon();
		abilityLevels.put(lifeSiphon, 2);
		
		abilityLevels.put(new BloodPact(), 5);
		
	}
	
	public RoleName getRoleName() {
		return RoleName.WARLOCK;
	}
	
	public BufferedImage getDefaultImage() {
		try {
			return ImageIO.read(this.getClass().getResource("/res/feLordSprites/FemaleRobin.png"));
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
		return "'One who bleeds.' Each Warlock makes an unholy pact to grant power at a terrible cost.";
	}
}