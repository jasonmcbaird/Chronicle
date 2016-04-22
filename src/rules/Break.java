package rules;

import items.Item;
import character.Character;
import enums.Condition;

public class Break extends Rule {
	
	private Item item;
	
	public Break(Item item) {
		this.item = item;
	}
		
	public void subExecute(Character source, Character target, Condition condition) {
		target.inventory.remove(item);
	}
}
