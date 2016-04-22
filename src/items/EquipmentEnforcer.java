package items;

import character.Character;

public interface EquipmentEnforcer {
	
	public void applyEquip(Character character);
	public void applyUnequip(Character character);
	public Class getEquipmentType();
	
}
