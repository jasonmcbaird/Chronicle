package items.accessories;

import items.Accessory;
import character.Character;
import enums.EnergyType;

public class ManaSapphire extends Accessory {
	
	private final int bonusManaAmount = 3;
	
	public ManaSapphire() {
		name = "Mana Sapphire";
	}
	
	public void applyEquip(Character character) {
		character.setMaxEnergy(EnergyType.MANA, character.getMaxEnergy(EnergyType.MANA) + bonusManaAmount);

	}
	
	public void applyUnequip(Character character) {
		character.setMaxEnergy(EnergyType.MANA, character.getMaxEnergy(EnergyType.MANA) - bonusManaAmount);
	}

}
