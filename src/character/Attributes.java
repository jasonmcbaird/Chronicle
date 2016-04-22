package character;

import utilities.Logger;
import enums.Stat;

public class Attributes {
	
	private final int minimumStat = 1;
	
	private int strength, toughness, agility, intuition, intellect, will;
	private int dodgeBonus = 0, magicBonus = 0, healthBonus = 0;
	private int resilienceBonus = 0, vitalityBonus = 0, defenseBonus = 0, accuracyBonus = 0, critBonus = 0, powerBonus = 0;
	private int currentHealth;
	private int range = 0;
	private int moveSpeed;
	
	private boolean stealthed = false;
	
	/** This is an unclear system for minimum stats.
	 *  How do we represent to the player that their stat is negative but is being treated as 1?
	 */
	
	
	public int getStat(Stat stat) {
		switch (stat) {
		case STRENGTH:
			if(strength < minimumStat)
				return minimumStat;
			return strength;
		case TOUGHNESS:
			if(toughness < minimumStat)
				return minimumStat;
			return toughness;
		case AGILITY:
			if(agility < minimumStat)
				return minimumStat;
			return agility;
		case INTUITION:
			if(intuition < minimumStat)
				return minimumStat;
			return intuition;
		case INTELLECT:
			if(intellect < minimumStat)
				return minimumStat;
			return intellect;
		case WILL:
			if(will < minimumStat)
				return minimumStat;
			return will;
		case DODGE:
			int dodge = agility + intuition + dodgeBonus;
			Logger.print("Agility: " + agility + ". Intuition: " + intuition + ". Dodge bonus: " + dodgeBonus + ".", -1);
			if(dodge < minimumStat)
				return minimumStat;
			return  dodge;
		case MAGIC:
			int magic = intellect + will + magicBonus;
			if(magic < minimumStat)
				return minimumStat;
			return  magic;
		case HEALTH:
			int health = strength + toughness + healthBonus;
			if(health < minimumStat)
				return minimumStat;
			return  health;
		case RESILIENCE:
			int resilience = toughness + intuition + resilienceBonus;
			if(resilience < minimumStat)
				return minimumStat;
			return  resilience;
		case VITALITY:
			int vitality = intellect + strength + vitalityBonus;
			if(vitality < minimumStat)
				return minimumStat;
			return  vitality;
		case DEFENSE:
			int defense = will + toughness + defenseBonus;
			if(defense < minimumStat)
				return minimumStat;
			return  defense;
		case ACCURACY:
			int accuracy = intellect + agility + accuracyBonus;
			if(accuracy < minimumStat)
				return minimumStat;
			return  accuracy;
		case CRIT:
			int crit = will + intuition + critBonus;
			if(crit < minimumStat)
				return minimumStat;
			return  crit;
		case POWER:
			int power = strength + agility + powerBonus;
			if(power < minimumStat)
				return minimumStat;
			return  power;
		case RANGE:
			if(range < -1)
				return -1;
			return range;
		default:
			throw new UnsupportedOperationException("You asked for a stat that doesn't exist.");
		}
	}
	
	public void setStat(Stat stat, int i) {
		switch (stat) {
		case STRENGTH:
			int oldHealth = getMaxHealth();
			strength = i;
			resetMaxHealth(oldHealth);
			return;
		case TOUGHNESS:
			int viejoHealth = getMaxHealth();
			toughness = i;
			resetMaxHealth(viejoHealth);
			return;
		case AGILITY:
			agility = i;
			return;
		case INTUITION:
			intuition = i;
			return;
		case INTELLECT:
			intellect = i;
			return;
		case WILL:
			will = i;
			return;
		case RANGE:
			range = i;
		default:
			return;
		}
	}
	
	private void resetMaxHealth(int oldHealth) {
		int difference = getMaxHealth() - oldHealth;
		currentHealth = (currentHealth + difference);
		if(currentHealth < 0)
			currentHealth = 0;
		if(getCurrentHealth() > getMaxHealth())
			setCurrentHealth(getMaxHealth());
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}
	
	public int getMaxHealth() {
		return 1 + getStat(Stat.HEALTH) * 3 ;
	}
	
	public void setCurrentHealth(int cHealth){
		currentHealth = cHealth;
		if(currentHealth > getMaxHealth())
			currentHealth = getMaxHealth();
	}
	
	public void changeStat(Stat stat, int i) {
		switch (stat) {
		case STRENGTH:
			int oldHealth = getMaxHealth();
			strength += i;
			resetMaxHealth(oldHealth);
			return;
		case TOUGHNESS:
			oldHealth = getMaxHealth();
			toughness += i;
			resetMaxHealth(oldHealth);
			return;
		case AGILITY:
			agility += i;
			return;
		case INTUITION:
			intuition += i;
			return;
		case INTELLECT:
			intellect += i;
			return;
		case WILL:
			will += i;
			return;
		case DODGE:
			Logger.print("Modifying dodge by " + i, -1);
			dodgeBonus += i;
			return;
		case MAGIC:
			magicBonus += i;
			return;
		case HEALTH:
			oldHealth = getMaxHealth();
			healthBonus += i;
			resetMaxHealth(oldHealth);
			return;
		case RESILIENCE:
			resilienceBonus += i;
			return;
		case VITALITY:
			vitalityBonus += i;
			return;
		case DEFENSE:
			defenseBonus += i;
			return;
		case ACCURACY:
			accuracyBonus += i;
			return;
		case CRIT:
			critBonus += i;
			return;
		case POWER:
			powerBonus += i;
			return;
		case RANGE:
			range += i;
			return;
		default:
			throw new UnsupportedOperationException("You tried to set a stat that doesn't exist.");
		}
	}
	
	public int getMoveSpeed() {
		if(moveSpeed < 0)
			return 0;
		return moveSpeed;
	}
	
	public void changeMoveSpeed(int amount) {
		moveSpeed += amount;
	}
	
	public void setMoveSpeed(int amount) {
		moveSpeed = amount;
	}
	
	public boolean getStealthed() {
		return stealthed;
	}
	
	public void stealth() {
		stealthed = true;
	}
	
	public void unstealth() {
		stealthed = false;
	}
}
