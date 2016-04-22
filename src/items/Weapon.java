package items;

import java.util.ArrayList;

import ability.Ability;
import character.Character;
import enums.DamageType;
import enums.Stat;

public class Weapon extends Equipment {
	
	private boolean active = false;
	protected int damageBase = 4;
	protected int range = 1;
	protected DamageType damageType = DamageType.SMASH;
	protected Stat stat = Stat.POWER;
	
	public void activate() {
		active = true;
	}
	
	public void deactivate() {
		active = false;
	}
	
	public void applyEquip(Character character) {
		active = true;
	}
	
	public void applyUnequip(Character character) {
		active = false;
	}
	
	public Class<?> getEquipmentType() {
		return Weapon.class;
	}
	
	public ArrayList<Ability> getAbilities() {
		if(active)
			return super.getAbilities();
		else
			return new ArrayList<Ability>();
	}
	
	public boolean isActive() {
		return active;
	}
	
	public boolean offhand() {
		return false;
	}
	
	public boolean twoHanded() {
		return false;
	}
	
	public int getDamageBase() {
		return damageBase;
	}
	
	public int getRange(Character character) {
		if(range == 1) {
			return 1;
		}
		return range + character.attributes.getStat(Stat.RANGE);
	}
	
	public DamageType getDamageType() {
		return damageType;
	}
	
	public Stat getStat() {
		return stat;
	}
	
}
