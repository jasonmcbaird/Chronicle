package roles.purger;

import items.Equipment;
import items.weapons.ColdIronLongsword;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ability.Ability;
import character.Role;
import enums.RoleName;

public class Purger extends Role {
	
	private ArrayList<Ability> readyAbilities = new ArrayList<Ability>();
	
	public Purger() {
		createAbilities();
		setName("Purger");
	}
	
	private void createAbilities() {
		
		abilityLevels.put(new Revelation(), 0);
		
		Smite smite = new Smite();
		abilityLevels.put(smite, 0);
		
		Mend mend = new Mend();
		abilityLevels.put(mend, 2);
		
		Aegis aegis = new Aegis();
		abilityLevels.put(aegis, 3);
	}
	
	public RoleName getRoleName() {
		return RoleName.PURGER;
	}
	
	public BufferedImage getDefaultImage() {
		try {
			return ImageIO.read(this.getClass().getResource("/res/feLordSprites/Seliph.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Equipment getBasicWeapon() {
		return new ColdIronLongsword();
	}
	
	public boolean isReady(Ability ability) {
		if(readyAbilities.contains(ability))
			return true;
		return false;
	}
	
	public ArrayList<Ability> getReadyAbilities() {
		return readyAbilities;
	}
	
	public void readyAbility(Ability ability) {
		readyAbilities.add(ability);
	}
	
	public void unreadyAbility(Ability ability) {
		readyAbilities.remove(ability);
	}
	
	public String getInfo() {
		return "A holy warrior that burns the wicked and rejuvenates the devout.";
	}
}