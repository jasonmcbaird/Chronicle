package character;

import static org.junit.Assert.assertEquals;
import items.Accessory;
import items.Equipment;
import items.EquipmentEnforcer;
import items.Weapon;

import java.util.ArrayList;

import org.junit.Test;

import enums.Condition;
import queue.Queue;

public class TestInventory {
	
	@Test
	public void testGet() {
		Inventory inventory = new Inventory(new Character());
		ArrayList<Equipment> equipments = new ArrayList<Equipment>();
		ArrayList<Accessory> accessories = new ArrayList<Accessory>();
		accessories.add(new Accessory());
		equipments.addAll(accessories);
		equipments.add(new Weapon());
		for(Equipment equipment: equipments)
			inventory.equip(equipment);
		assertEquals(accessories, inventory.getAccessories());
	}
	
	@Test
	public void testLimits() {
		Inventory inventory = new Inventory(new Character());
		ArrayList<Equipment> equipments = new ArrayList<Equipment>();
		ArrayList<Accessory> accessories = new ArrayList<Accessory>();
		accessories.add(new Accessory());
		accessories.add(new Accessory());
		accessories.add(new Accessory());
		equipments.addAll(accessories);
		equipments.add(new Weapon());
		for(Equipment equipment: equipments)
			inventory.equip(equipment);
		assertEquals(1, inventory.getAccessories().size());
		assertEquals(accessories.get(2), inventory.getAccessories().get(0));
	}
	
	@Test
	public void testUnarmed() {
		Character cody = new Character();
		Inventory inventory = new Inventory(cody);
		assertEquals(1, inventory.getActiveWeaponNames().size());
		assertEquals("Unarmed", inventory.getActiveWeaponNames().get(0));
		
		assertEquals(1, inventory.getEquipmentAbilities("Unarmed").size());
		assertEquals("Attack", inventory.getEquipmentAbilities("Unarmed").get(0));
		
		Queue.addAndRun(cody.getAbility("Attack"), cody, cody, Condition.ACTIVATE_ABILITY);
		
		assertEquals(cody.getMaxHealth() - inventory.getMainhandWeapon().getDamageBase(), cody.getHealth());
	}

}
