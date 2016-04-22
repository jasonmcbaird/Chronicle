package roles.rogue;

import items.Accessory;

import java.util.ArrayList;

import queue.Queue;
import rules.WeaponAttack;
import utilities.Dice;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.EnergyType;

public class Mug extends Ability {
	
	private final int rhythmCost = 3;
	
	public Mug() {
		super();
		setName("Mug");
	}
	
	public boolean getCanBeActivated(Character character) {
		if(!super.getCanBeActivated(character))
			return false;
		// TODO: This code gets repeated.
		if(canBeActivated) {
			if(character.getEnergy(EnergyType.RHYTHM) < rhythmCost) {
				return false;
			}
		}
		return true;
	}
	
	public int getRange(Character character) {
		return character.inventory.getMainRange();
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		if(!payEnergy(source, EnergyType.RHYTHM, rhythmCost))
			return;
		WeaponAttack attack = new WeaponAttack(source, -2);
		Queue.addAndRun(attack, source, target, Condition.ATTACK);
		if(attack.wasHit())
			steal(source, target);
	}
	
	public void steal(Character source, Character target) {
		Accessory accessory = randomAccessory(target.inventory.getAccessories());
		target.inventory.unequip(accessory);
		source.inventory.equip(accessory);
	}
	
	public Accessory randomAccessory(ArrayList<Accessory> accessories) {
		int roll = Dice.rollDie(accessories.size());
		return accessories.get(roll -1);
	}
	
}
