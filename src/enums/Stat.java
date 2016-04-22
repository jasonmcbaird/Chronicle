package enums;

import java.util.ArrayList;

import utilities.Dice;
import character.Character;
import character.Role;

public enum Stat {
	
	// Major stats
	STRENGTH, TOUGHNESS, // Warrior branch
	AGILITY, INTUITION, // Rogue branch
	INTELLECT, WILL, // Mage branch
	
	// Derived stats
	DODGE, MAGIC, HEALTH, // Allied stats of same category added together
	RESILIENCE, VITALITY, DEFENSE, ACCURACY, CRIT, POWER, // Enemy stats added together
	// Resilience: Elemental resist
	// Vitality: Physiological resist
	// Defense: Physical resist
	
	// Special stats
	RANGE;

	public static ArrayList<Stat> getAlliedStats(Stat mainStat) {
		ArrayList<Stat> alliedStats = new ArrayList<Stat>();
		switch (mainStat) {
		case STRENGTH:
			alliedStats.add(Stat.TOUGHNESS);
			alliedStats.add(Stat.AGILITY);
			return alliedStats;
		case TOUGHNESS:
			alliedStats.add(Stat.STRENGTH);
			alliedStats.add(Stat.WILL);
			return alliedStats;
		case WILL:
			alliedStats.add(Stat.TOUGHNESS);
			alliedStats.add(Stat.INTELLECT);
			return alliedStats;
		case INTELLECT:
			alliedStats.add(Stat.WILL);
			alliedStats.add(Stat.INTUITION);
			return alliedStats;
		case INTUITION:
			alliedStats.add(Stat.INTELLECT);
			alliedStats.add(Stat.AGILITY);
			return alliedStats;
		case AGILITY:
			alliedStats.add(Stat.INTUITION);
			alliedStats.add(Stat.STRENGTH);
			return alliedStats;
		default:
			return alliedStats;
		}
	}
	
	public static ArrayList<Stat> getEnemyStats(Stat mainStat) {
		ArrayList<Stat> alliedStats = new ArrayList<Stat>();
		switch (mainStat) {
		case INTELLECT:
			alliedStats.add(Stat.TOUGHNESS);
			alliedStats.add(Stat.AGILITY);
			return alliedStats;
		case INTUITION:
			alliedStats.add(Stat.STRENGTH);
			alliedStats.add(Stat.WILL);
			return alliedStats;
		case AGILITY:
			alliedStats.add(Stat.TOUGHNESS);
			alliedStats.add(Stat.INTELLECT);
			return alliedStats;
		case STRENGTH:
			alliedStats.add(Stat.WILL);
			alliedStats.add(Stat.INTUITION);
			return alliedStats;
		case TOUGHNESS:
			alliedStats.add(Stat.INTELLECT);
			alliedStats.add(Stat.AGILITY);
			return alliedStats;
		case WILL:
			alliedStats.add(Stat.INTUITION);
			alliedStats.add(Stat.STRENGTH);
			return alliedStats;
		default:
			return alliedStats;
		}
	}
	
	public static Stat getDefaultMainStat(Character character) {
		Role role = character.getRole(0);
		RoleName name = role.getRoleName();
		switch (name) {
		case WARRIOR:
		case GENERAL:
			return random(Stat.STRENGTH, Stat.TOUGHNESS);
		case BERSERKER:
			return Stat.STRENGTH;
		case PURGER:
			return Stat.TOUGHNESS;
		case FATE:
			return random(Stat.TOUGHNESS, Stat.WILL);
		case NECROMANCER:
			return Stat.WILL;
		case MAGE:
		case WARLOCK:
			return random(Stat.WILL, Stat.INTELLECT);
		case PSION:
			return Stat.INTELLECT;
		case ARTIFICER:
			return random(Stat.INTELLECT, Stat.INTUITION);
		case WARP:
			return Stat.INTUITION;
		case ROGUE:
		case ASSASSIN:
			return random(Stat.INTELLECT, Stat.AGILITY);
		case RANGER:
			return Stat.AGILITY;
		case SYLVAN:
			return random(Stat.AGILITY, Stat.STRENGTH);
		case CHRONICLER:
			return Stat.STRENGTH;
		case BOSS:
		case DEMON:
			return Stat.STRENGTH;
		default:
			throw new UnsupportedOperationException("Cannot get a main stat for " + character.getName() + " because their main role isn't supported.");
		}
	}
	
	private static Stat random(Stat stat1, Stat stat2) {
		if(Dice.rollDie(2) == 1)
			return stat1;
		return stat2;
	}
	
	public static ArrayList<Stat> getMajorStats() {
		ArrayList<Stat> stats = new ArrayList<Stat>();
		stats.add(Stat.STRENGTH);
		stats.add(Stat.TOUGHNESS);
		stats.add(Stat.WILL);
		stats.add(Stat.INTELLECT);
		stats.add(Stat.INTUITION);
		stats.add(Stat.AGILITY);
		return stats;
	}
	
	public static ArrayList<Stat> getDerivedStats() {
		ArrayList<Stat> stats = new ArrayList<Stat>();
		stats.add(Stat.DODGE);
		stats.add(Stat.MAGIC);
		stats.add(Stat.HEALTH);
		stats.add(Stat.RESILIENCE);
		stats.add(Stat.VITALITY);
		stats.add(Stat.DEFENSE);
		stats.add(Stat.ACCURACY);
		stats.add(Stat.CRIT);
		stats.add(Stat.POWER);
		return stats;
	}
}
