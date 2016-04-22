package ui.party;

import items.Equipment;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ui.Menu2;
import ui.MenuItem;
import ui.MenuResponder;
import ui.UIStack;
import character.Character;
import character.Role;
import enums.RoleName;
import enums.UIPosition;

public class RoleMenuResponder implements MenuResponder, PartyMenuBuilder {
	
	private Menu2 menu;
	private Character character;
	
	public RoleMenuResponder(Character character) {
		this.character = character;
	}

	public void menuItemSelected(String string) {
		
	}

	public void inputCancel() {
		UIStack.pop(menu);
	}
	
	public void build() {
		menu = new Menu2(UIPosition.RIGHT_3);
		ArrayList<MenuItem> items = new ArrayList<MenuItem>();
		for(Role role: character.getRoles())
			items.add(role);
		if(character.canAddNewRole())
			items.add(new newRole());
		menu.setItems(items);
		menu.setResponder(this);
		UIStack.push(menu);
	}
	
	private class newRole extends Role {

		@Override
		public RoleName getRoleName() {
			return null;
		}

		@Override
		public BufferedImage getDefaultImage() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Equipment getBasicWeapon() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getInfo() {
			return "Add a new class to your arsenal.";
		}
		
		public String getName() {
			return "New Class";
		}
		
	}

}
