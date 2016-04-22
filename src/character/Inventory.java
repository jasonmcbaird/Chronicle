package character;

import items.Accessory;
import items.Armor;
import items.Equipment;
import items.Item;
import items.Weapon;
import items.weapons.Unarmed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import utilities.Logger;
import ability.Ability;
import enums.DamageType;
import enums.Stat;

public class Inventory {
	
	private int weaponLimit = 2;
	private final int armorLimit = 1;
	private final int accessoryLimit = 1;
	
	private Character character;
	private ArrayList<Equipment> equipments = new ArrayList<Equipment>();
	private ArrayList<Item> pack = new ArrayList<Item>();
	private HashMap<Class<?>, Integer> equipmentTypeLimits = new HashMap<Class<?>, Integer>();
	
	public Inventory(Character character) {
		this.character = character;
		equipmentTypeLimits.put(Weapon.class, weaponLimit);
		equipmentTypeLimits.put(Armor.class, armorLimit);
		equipmentTypeLimits.put(Accessory.class, accessoryLimit);
	}
	
	public ArrayList<String> getActiveWeaponNames() {
		ArrayList<String> weaponNames = new ArrayList<String>();
		ArrayList<Weapon> activeWeapons = getActiveWeapons();
		for(Weapon weapon: activeWeapons)
			if(!weapon.offhand())
				weaponNames.add(weapon.getName());
		for(Weapon weapon: activeWeapons)
			if(weapon.offhand())
				weaponNames.add(weapon.getName());
		if(weaponNames.isEmpty())
			weaponNames.add("Unarmed");
		return weaponNames;
	}
	
	public ArrayList<String> getEquipmentTypeNames(Class<?> equipmentType) {
		ArrayList<String> equipmentNames = new ArrayList<String>();
		for(Equipment equipment: getEquipmentsOfType(equipmentType))
			equipmentNames.add(equipment.getName());
		return equipmentNames;
	}
	
	public ArrayList<String> getEquipmentAbilities(String string) {
		ArrayList<String> abilities = new ArrayList<String>();
		Equipment equipment = getEquipment(string);
		if(equipment == null) {
			if(weaponSheathed())
				abilities.add("Switch Weapon");
			return abilities;
		}
		if(canSwitchWeapon(equipment))
			abilities.add("Switch Weapon");
		for(Ability ability: equipment.getAbilities())
			abilities.add(ability.getName());
		return abilities;
	}
	
	public void switchWeapon() {
		equip(getFirstInactiveWeapon());
	}
	
	private Weapon getFirstInactiveWeapon() {
		for(Weapon weapon: getWeapons())
			if(!weapon.isActive())
				return weapon;
		return null;
	}
	
	private Equipment getEquipment(String string) {
		if(string == "Unarmed") {
			Unarmed unarmed = new Unarmed();
			unarmed.activate();
			return unarmed;
		}
		for(Equipment equipment: equipments)
			if(equipment.getName() == string)
				return equipment;
		return null;
	}
	
	private boolean canSwitchWeapon(Equipment equipment) {
		if(!(equipment instanceof Weapon))
			return false;
		Weapon weapon = (Weapon) equipment;
		if(weapon.isActive() && weaponSheathed() && weapon == getMainhandWeapon())
			return true;
		return false;
	}
	
	public Weapon getMainhandWeapon() {
		ArrayList<Weapon> activeWeapons = getActiveWeapons();
		if(activeWeapons.size() < 1)
			return new Unarmed();
		for(int i = activeWeapons.size() - 1; i >= 0; i--)
			if(!activeWeapons.get(i).offhand())
				return activeWeapons.get(i);
		return activeWeapons.get(activeWeapons.size() - 1);
	}
	
	public ArrayList<Weapon> getActiveWeapons() {
		ArrayList<Weapon> weapons = getWeapons();
		ArrayList<Weapon> activeWeapons = new ArrayList<Weapon>();
		for(Weapon weapon: weapons)
			if(weapon.isActive())
				activeWeapons.add(weapon);
		return activeWeapons;
	}
	
	private boolean weaponSheathed() {
		ArrayList<Weapon> weapons = getWeapons();
		for(Weapon weapon: weapons)
			if(!weapon.isActive())
				return true;
		return false;
	}
	
	public ArrayList<Ability> getAbilities() {
		ArrayList<Ability> abilities = new ArrayList<Ability>();
		for(Equipment equipment: equipments)
			abilities.addAll(equipment.getAbilities());
		for(Item item: pack)
			abilities.addAll(item.getAbilities());
		return abilities;
	}
	
	public void equip(Equipment equipment) {
		Logger.print(character.getName() + " equips " + equipment.getName()); 
		Class<?> equipmentType = equipment.getEquipmentType();
		pack.remove(equipment);
		if(!equipments.contains(equipment)) {
			if(equipmentTypeLimits.containsKey(equipmentType))
				unequipToLimit(equipmentType);
			equipments.add(equipment);
			equipment.applyEquip(character);
		} else {
			equipments.remove(equipment);
			equipments.add(equipment);
		}
		if(equipment.getEquipmentType() == Weapon.class)
			fixActiveWeapons();
		Logger.print(character.getName() + "'s equipment:"); 
		for(Equipment logEquipment: equipments)
			Logger.print("	" + logEquipment.getName());
	}
	
	private void fixActiveWeapons() {
		deactivateAllWeapons();
		ArrayList<Weapon> weapons = getWeapons();
		Weapon newWeapon = weapons.get(weapons.size() - 1);
		newWeapon.activate();
		if(newWeapon.twoHanded())
			return;
		if(newWeapon.offhand())
			equipMainHand();
		else
			equipOffHand();
	}
	
	private void deactivateAllWeapons() {
		for(Weapon weapon: getWeapons())
			weapon.deactivate();
	}
	
	private void equipMainHand() {
		Logger.print("Trying to equip a mainhand weapon.");
		ArrayList<Weapon> weapons = getWeapons();
		for(int i = weapons.size() - 2; i >= 0; i--)
			if(!weapons.get(i).offhand() && !weapons.get(i).twoHanded()) {
				Logger.print("Equipping " + weapons.get(i).getName() + " as a mainhand weapon.");
				weapons.get(i).activate();
				return;
			}
		for(int i = weapons.size() - 2; i >= 0; i--) {
			if(!weapons.get(i).twoHanded()) {
				weapons.get(i).activate();
				return;
			}
		}
	}
	
	private void equipOffHand() {
		Logger.print("Trying to equip an offhand weapon.");
		ArrayList<Weapon> weapons = getWeapons();
		for(int i = weapons.size() - 2; i >= 0; i--)
			if(weapons.get(i).offhand()) {
				Logger.print("Equipping " + weapons.get(i).getName() + " as an offhand weapon.");
				weapons.get(i).activate();
				return;
			}
	}
	
	private void unequipToLimit(Class<?> equipmentType) {
		while(getEquipmentsOfType(equipmentType).size() >= equipmentTypeLimits.get(equipmentType))
			unequip(getEquipmentsOfType(equipmentType).get(0));
	}
	
	public void unequip(Equipment equipment) {
		equipments.remove(equipment);
		equipment.applyUnequip(character);
		pack.add(equipment);
	}
	
	public ArrayList<Equipment> getEquipments() {
		return equipments;
	}
	
	public ArrayList<Weapon> getWeapons() {
		ArrayList<Weapon> weapons = new ArrayList<Weapon>();
		for(Equipment weapon: getEquipmentsOfType(Weapon.class))
			weapons.add((Weapon) weapon);
		return weapons;
	}
	
	public ArrayList<Armor> getArmors() {
		ArrayList<Armor> armors = new ArrayList<Armor>();
		for(Equipment armor: getEquipmentsOfType(Armor.class))
			armors.add((Armor) armor);
		return armors;
	}
	
	public ArrayList<Accessory> getAccessories() {
		ArrayList<Accessory> accessories = new ArrayList<Accessory>();
		for(Equipment accessory: getEquipmentsOfType(Accessory.class))
			accessories.add((Accessory) accessory);
		return accessories;
	}
	
	private ArrayList<Equipment> getEquipmentsOfType(Class<?> equipmentType) {
		ArrayList<Equipment> weapons = new ArrayList<Equipment>();
		for(Equipment equipment: equipments)
			if(equipment.getEquipmentType() == equipmentType)
				weapons.add(equipment);
		return weapons;
	}
	
	public int getMainDamageBase() {
		return getMainhandWeapon().getDamageBase();
	}
	
	public DamageType getMainDamageType() {
		return getMainhandWeapon().getDamageType();
	}
	
	public int getMainRange() {
		return getMainhandWeapon().getRange(character);
	}
	
	public Stat getMainStat() {
		return getMainhandWeapon().getStat();
	}
	
	public void add(Item item) {
		pack.add(item);
	}
	
	public void remove(Item item) {
		equipments.remove(item);
		pack.remove(item);
	}
	
	public void startEncounter() {
		for(Equipment equipment: equipments)
			equipment.startEncounter(character);
	}
	
	public ArrayList<String> getPackNames() {
		ArrayList<String> names = new ArrayList<String>();
		for(Item item: pack)
			names.add(item.getName());
		return names;
	}
	
	public ArrayList<Item> getPackItems() {
		return pack;
	}
	
	public ArrayList<String> getEquipmentNames() {
		ArrayList<String> names = new ArrayList<String>();
		for(Equipment equipment: equipments)
			names.add(equipment.getName());
		return names;
	}
	
	public ArrayList<Item> getAllItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(getMainhandWeapon());
		items.addAll(getActiveWeapons());
		items.addAll(getWeapons());
		items.addAll(getArmors());
		items.addAll(getAccessories());
		items.addAll(getPackItems());
		items.addAll(getEquipments());
		items = deleteDuplicates(items);
		return items;
	}
	
	public ArrayList<String> getAllItemNames() {
		ArrayList<String> names = new ArrayList<String>();
		for(Item item: getAllItems())
			names.add(item.getName());
		return names;
	}
	
	private ArrayList<Item> deleteDuplicates(ArrayList<Item> items) {
		ArrayList<Item> returnList = new ArrayList<Item>();
		Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item i = iterator.next();
            if(!returnList.contains(i))
            	returnList.add(i);
        }
		return returnList;
	}
	
	public void setWeaponLimit(int i) {
		weaponLimit = i;
	}
}
