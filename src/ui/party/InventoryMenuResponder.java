package ui.party;

import items.Equipment;
import items.Item;

import java.util.ArrayList;

import ui.Menu;
import ui.MenuResponder;
import ui.UIStack;
import character.Inventory;
import enums.UIPosition;

public class InventoryMenuResponder implements MenuResponder, PartyMenuBuilder {
	
	private Menu menu;
	private Inventory inventory;
	
	public InventoryMenuResponder(Inventory inventory) {
		this.inventory = inventory;
	}

	public void menuItemSelected(String string) {
		Item item = getItem(string);
		if(item instanceof Equipment)
			inventory.equip((Equipment) item);
		UIStack.pop(menu);
		build();
		// Equip equipment if not equipped CHECK
		// Activate consumables NOT CHECK
	}

	public void inputCancel() {
		UIStack.pop(menu);
	}
	
	public void build() {
		menu = new Menu(UIPosition.RIGHT_3);
		ArrayList<String> items = new ArrayList<String>();
		items.addAll(inventory.getAllItemNames());
		menu.setItems(items);
		menu.setResponder(this);
		UIStack.push(menu);
	}
	
	private Item getItem(String string) {
		for(Equipment equipment: inventory.getEquipments())
			if(equipment.getName() == string)
				return equipment;
		for(Item item: inventory.getPackItems())
			if(item.getName() == string)
				return item;
		return null;
	}

}
