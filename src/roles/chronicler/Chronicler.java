package roles.chronicler;

import items.Equipment;
import items.weapons.BlackKnife;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import character.Role;
import enums.RoleName;

public class Chronicler extends Role {
	
	public Chronicler() {
		createAbilities();
		setName("Chronicler");
	}
	
	private void createAbilities() {
		abilityLevels.put(new Versatility(), 7);
	}
	
	public RoleName getRoleName() {
		return RoleName.CHRONICLER;
	}
	
	public BufferedImage getDefaultImage() {
		try {
			return ImageIO.read(this.getClass().getResource("/res/feLordSprites/MaleRobin.png"));
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
		if(getLevel() > 8)
			return "A legendary historian caught up in a time of great change.";
		if(getLevel() > 4)
			return "A talented historian caught up in a time of great change.";
		return "A simple historian caught up in a time of great change.";
	}
}