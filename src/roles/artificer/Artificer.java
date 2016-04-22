package roles.artificer;

import items.Equipment;
import items.weapons.BlackKnife;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ability.Ability;
import character.Role;
import enums.RoleName;

public class Artificer extends Role {
	
	public Artificer() {
		createAbilities();
		setName("Artificer");
	}
	
	private void createAbilities() {
		
		abilityLevels.put(new Durability(this), 0);
		
		abilityLevels.put(new BottledLightning(), 0);
		
		abilityLevels.put(new InfernoTurret(), 4);
		
	}
	
	public void resetDurability() {
		for(Durable durable: getDurables())
			durable.resetDurability();
	}
	
	private ArrayList<Durable> getDurables() {
		ArrayList<Durable> durables = new ArrayList<Durable>();
		for(Ability ability: abilityLevels.keySet())
			if(ability instanceof Durable)
				durables.add((Durable) ability);
		return durables;
	}
	
	public RoleName getRoleName() {
		return RoleName.ARTIFICER;
	}
	
	public BufferedImage getDefaultImage() {
		try {
			return ImageIO.read(this.getClass().getResource("/res/feLordSprites/Alm.png"));
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
		return "An inventor of magical gadgets, armored in a powersuit.";
	}
	
}