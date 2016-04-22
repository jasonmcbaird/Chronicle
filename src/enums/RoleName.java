package enums;

import java.awt.Color;

public enum RoleName {
	CHRONICLER, WARRIOR, BERSERKER, GENERAL, PURGER, FATE, NECROMANCER, WARLOCK,
	PSION, MAGE, ARTIFICER, WARP, ASSASSIN, RANGER, ROGUE, SYLVAN,
	BOSS, DEMON;
	
	
	public static RoleName getRoleName(String string) {
		return valueOf(string.toUpperCase());
	}
	
	public static Color getColor(RoleName name) {
		switch(name) {
		case CHRONICLER:
			return new Color(238, 223, 166);
		case WARRIOR:
		case GENERAL:
		case BERSERKER:
		case PURGER:
			return new Color(204, 0, 0);
		case MAGE:
		case WARLOCK:
		case NECROMANCER:
		case PSION:
			return new Color(0, 0, 255);
		case ROGUE:
		case ASSASSIN:
		case WARP:
		case RANGER:
			return new Color(204, 204, 0);
		case FATE:
			return new Color(102, 0, 204);
		case ARTIFICER:
			return new Color(0, 153, 0);
		case SYLVAN:
			return new Color(204, 102, 0);
		default:
			return Color.BLACK;
		}
	}
}
