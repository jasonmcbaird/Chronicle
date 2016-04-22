package enums;

import java.awt.Color;
import java.util.ArrayList;

public enum EnergyType {
	
	MANA, RAGE, PATIENCE, SECONDS, CONCENTRATION, HERBS, STAMINA, RHYTHM;
	
	public static Color getColor(EnergyType energyType) {
		switch (energyType) {
		case MANA:
		case PATIENCE:
		case CONCENTRATION:
			return Color.BLUE;
		case RAGE:
		case RHYTHM:
			return Color.RED;
		case STAMINA:
		case HERBS:
			return Color.GREEN;
		case SECONDS:
			return Color.YELLOW;
		default:
			return Color.ORANGE;
		}
	}
	
	public static ArrayList<EnergyType> getEnergyTypes() {
		ArrayList<EnergyType> energyTypes = new ArrayList<EnergyType>();
		energyTypes.add(MANA);
		energyTypes.add(RAGE);
		energyTypes.add(PATIENCE);
		energyTypes.add(SECONDS);
		energyTypes.add(CONCENTRATION);
		energyTypes.add(HERBS);
		energyTypes.add(STAMINA);
		energyTypes.add(RHYTHM);
		return energyTypes;
	}

}
