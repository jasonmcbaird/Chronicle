package roles.npc;

import items.Equipment;
import items.weapons.Frostmourne;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import ability.Ability;
import character.Role;
import enums.RoleName;

public class Boss extends Role {
	
	public Boss(String name, HashMap<Ability, Integer> abilityLevels) {
		setName(name);
		this.abilityLevels = abilityLevels;
	}
	
	public RoleName getRoleName() {
		return RoleName.BOSS;
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
		return new Frostmourne();
	}
	
	public String getInfo() {
		return "A unique enemy with powerful abilities.";
	}
}