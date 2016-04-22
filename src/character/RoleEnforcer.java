package character;

import items.Equipment;

import java.awt.image.BufferedImage;

import enums.RoleName;

public interface RoleEnforcer {
	
	public RoleName getRoleName();
	public BufferedImage getDefaultImage();
	public Equipment getBasicWeapon();

}
