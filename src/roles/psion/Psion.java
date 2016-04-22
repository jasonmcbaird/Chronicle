package roles.psion;

import items.Equipment;
import items.weapons.BlackKnife;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import character.Role;
import enums.RoleName;

public class Psion extends Role {
	
	public Psion() {
		createAbilities();
		setName("Psion");
	}
	
	private void createAbilities() {
		
		Shatter shatter = new Shatter();
		abilityLevels.put(shatter, 0);
		
		Inspiration inspiration = new Inspiration();
		abilityLevels.put(inspiration, 1);
		
		Daze daze = new Daze();
		abilityLevels.put(daze, 2);
		
		TelekineticShield shield = new TelekineticShield();
		abilityLevels.put(shield, 4);
		
		Focus focus = new Focus();
		abilityLevels.put(focus, 5);
		
		Dominate dominate = new Dominate();
		abilityLevels.put(dominate, 10);
		
	}
	
	public RoleName getRoleName() {
		return RoleName.PSION;
	}
	
	public BufferedImage getDefaultImage() {
		try {
			return ImageIO.read(this.getClass().getResource("/res/feLordSprites/Cecilia.png"));
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
		return "An intuitive manipular of mind and matter.";
	}
}