package character;

import items.Equipment;
import items.accessories.Warhorse;
import items.weapons.BlackKnife;
import items.weapons.ColdIronLongsword;
import items.weapons.Dreadaxe;
import items.weapons.Greataxe;
import items.weapons.InfernoLongbow;
import items.weapons.LegionsShield;
import items.weapons.PiratesRapier;

import java.util.ArrayList;
import java.util.HashMap;

import utilities.Dice;
import utilities.Logger;
import enums.RoleName;
import enums.Stat;

public class CharacterBuilder {
	
	private static HashMap<String, ArrayList<RoleName>> heroRoles;
	private static HashMap<String, ArrayList<Equipment>> heroEquipment;
	private static final int secondClassThreshold = 4;
	private static final int thirdClassThreshold = 8;
	private static final int fourthClassThreshold = 10;
	
	public static Character makeCharacter(String string, int i) {
		Character character;
		Logger.print("Building character: " + string + " at level " + i + ".");
		if(isRole(string)) {
			character = createCharacter(RoleName.getRoleName(string), i);
			character.setName(string);
		}
		else {
			ArrayList<RoleName> roles = getHeroRoles().get(string);
			character = createCharacter(roles, i);
			character.setName(string);
			equip(character, getHeroEquipment().get(string));
		}
		return character;
	}
	
	public static boolean isRole(String string) {
	    for (RoleName roleName: RoleName.values())
	        if (roleName.name().equals(string.toUpperCase()))
	            return true;
	    return false;
	}
	
	private static Character createCharacter(RoleName roleName, int i) {
		Character character = new Character();
		character.setRole(roleName, 0);
		character.setRoleLevel(0, i);
		character.setMainStat(Stat.getDefaultMainStat(character));
		character.inventory.equip(character.getRole(0).getBasicWeapon());
		levelUp(character,i);
		return character;
	}
	
	private static Character createCharacter(ArrayList<RoleName> roleNames, int i) {
		Character character = createCharacter(roleNames.get(0), i);
		setExtraRoles(character, roleNames, i);
		return character;
	}
	
	private static void setExtraRoles(Character character, ArrayList<RoleName> roleNames, int i) {
		if(i >= secondClassThreshold) {
			character.setRole(roleNames.get(1), 1);
			character.setRoleLevel(1, i - secondClassThreshold + 1);
		}
		if(i >= thirdClassThreshold) {
			character.setRole(roleNames.get(2), 2);
			character.setRoleLevel(2, i - thirdClassThreshold + 1);
		}
		if(i >= fourthClassThreshold) {
			character.setRole(roleNames.get(3), 3);
			character.setRoleLevel(3, i - fourthClassThreshold + 1);
		}
	}
	
	private static HashMap<String, ArrayList<RoleName>> getHeroRoles() {
		if(heroRoles == null)
			createHeroes();
		return heroRoles;
	}
	
	private static HashMap<String, ArrayList<Equipment>> getHeroEquipment() {
		if(heroEquipment == null)
			createHeroes();
		return heroEquipment;
	}
	
	private static void createHeroes() {
		heroRoles = new HashMap<String, ArrayList<RoleName>>();
		ArrayList<RoleName> roleNames = new ArrayList<RoleName>();
		
		heroEquipment = new HashMap<String, ArrayList<Equipment>>();
		ArrayList<Equipment> equipments = new ArrayList<Equipment>();
		
		roleNames.add(RoleName.BERSERKER);
		roleNames.add(RoleName.WARRIOR);
		roleNames.add(RoleName.SYLVAN);
		roleNames.add(RoleName.RANGER);
		heroRoles.put("Barbarian", roleNames);
		equipments.add(new Greataxe());
		heroEquipment.put("Barbarian", equipments);
		
		roleNames = new ArrayList<RoleName>();
		equipments = new ArrayList<Equipment>();
		roleNames.add(RoleName.ROGUE);
		roleNames.add(RoleName.ASSASSIN);
		roleNames.add(RoleName.RANGER);
		roleNames.add(RoleName.ARTIFICER);
		heroRoles.put("Thief", roleNames);
		equipments.add(new InfernoLongbow());
		equipments.add(new PiratesRapier());
		heroEquipment.put("Thief", equipments);
		
		roleNames = new ArrayList<RoleName>();
		equipments = new ArrayList<Equipment>();
		roleNames.add(RoleName.WARLOCK);
		roleNames.add(RoleName.NECROMANCER);
		roleNames.add(RoleName.MAGE);
		roleNames.add(RoleName.FATE);
		heroRoles.put("Blood Mage", roleNames);
		
		roleNames = new ArrayList<RoleName>();
		equipments = new ArrayList<Equipment>();
		roleNames.add(RoleName.GENERAL);
		roleNames.add(RoleName.WARRIOR);
		roleNames.add(RoleName.PURGER);
		roleNames.add(RoleName.BERSERKER);
		heroRoles.put("Commander", roleNames);
		equipments.add(new Warhorse());
		equipments.add(new ColdIronLongsword());
		equipments.add(new LegionsShield());
		heroEquipment.put("Commander", equipments);
		
		roleNames = new ArrayList<RoleName>();
		equipments = new ArrayList<Equipment>();
		roleNames.add(RoleName.PURGER);
		roleNames.add(RoleName.WARRIOR);
		roleNames.add(RoleName.FATE);
		roleNames.add(RoleName.GENERAL);
		heroRoles.put("Paladin", roleNames);
		equipments.add(new ColdIronLongsword());
		equipments.add(new LegionsShield());
		heroEquipment.put("Paladin", equipments);
		
		roleNames = new ArrayList<RoleName>();
		equipments = new ArrayList<Equipment>();
		roleNames.add(RoleName.RANGER);
		roleNames.add(RoleName.ASSASSIN);
		roleNames.add(RoleName.ROGUE);
		roleNames.add(RoleName.SYLVAN);
		heroRoles.put("Sniper", roleNames);
		equipments.add(new InfernoLongbow());
		heroEquipment.put("Sniper", equipments);
		
		roleNames = new ArrayList<RoleName>();
		equipments = new ArrayList<Equipment>();
		roleNames.add(RoleName.PSION);
		roleNames.add(RoleName.ARTIFICER);
		roleNames.add(RoleName.RANGER);
		roleNames.add(RoleName.WARLOCK);
		heroRoles.put("Psych", roleNames);
		
		roleNames = new ArrayList<RoleName>();
		equipments = new ArrayList<Equipment>();
		roleNames.add(RoleName.ASSASSIN);
		roleNames.add(RoleName.SYLVAN);
		roleNames.add(RoleName.ROGUE);
		roleNames.add(RoleName.RANGER);
		heroRoles.put("Ninja", roleNames);
		equipments.add(new InfernoLongbow());
		equipments.add(new BlackKnife());
		heroEquipment.put("Ninja", equipments);
		
		roleNames = new ArrayList<RoleName>();
		equipments = new ArrayList<Equipment>();
		roleNames.add(RoleName.DEMON);
		roleNames.add(RoleName.WARLOCK);
		roleNames.add(RoleName.BERSERKER);
		roleNames.add(RoleName.ASSASSIN);
		heroRoles.put("Demon", roleNames);
		equipments.add(new Dreadaxe());
		heroEquipment.put("Demon", equipments);
	}
	
	private static void levelUp(Character character, int maxLevel) {
		character.generalLevelUp();
		for(int i = 1; i <= maxLevel * 3; i++) {
			character.generalLevelUp();
		}
	}
	
	private static void equip(Character character, ArrayList<Equipment> equipments) {
		if(equipments == null)
			return;
		for(Equipment equipment: equipments)
			character.inventory.equip(equipment);
	}
	
	public static ArrayList<String> getHeroNames() {
		ArrayList<String> names = new ArrayList<String>();
		names.addAll(getHeroRoles().keySet());
		return names;
	}
}
