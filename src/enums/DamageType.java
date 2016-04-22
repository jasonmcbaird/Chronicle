package enums;

public enum DamageType {
	SLASH, STAB, SMASH, // Physical. Resisted by Defense.
	FLAME, FROST, LIGHTNING, // Elemental. Resisted by Resilience.
	TOXIN, BLEED, NEURO; // Physiological. Resisted by Vitality
	

	
	public static Stat getResistStat(DamageType damageType) {
		switch (damageType) {
		case SMASH:
		case STAB:
		case SLASH:
			return Stat.DEFENSE;
		case FLAME:
		case FROST:
		case LIGHTNING:
			return Stat.RESILIENCE;
		case TOXIN:
		case BLEED:
		case NEURO:
			return Stat.VITALITY;
		default:
			return null;
		}
	}
}