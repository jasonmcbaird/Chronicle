package roles.ranger;

import items.Equipment;
import items.weapons.InfernoLongbow;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import character.Role;
import enums.RoleName;

public class Ranger extends Role {
	
	public Ranger() {
		createAbilities();
		setName("Ranger");
	}
	
	private void createAbilities() {
		
		Snipe snipe = new Snipe();
		abilityLevels.put(snipe, 0);
		
		abilityLevels.put(new Ambush(), 5);
		
	}
	
	public RoleName getRoleName() {
		return RoleName.RANGER;
	}
	
	public BufferedImage getDefaultImage() {
		try {
			return ImageIO.read(this.getClass().getResource("/res/feLordSprites/Chrom.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Equipment getBasicWeapon() {
		return new InfernoLongbow();
	}
	
	public String getInfo() {
		return "A champion of the wilderness, artful in the ways of the bow and the beast.";
	}
}