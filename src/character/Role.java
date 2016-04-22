package character;

import java.util.ArrayList;
import java.util.HashMap;

import queue.Queue;
import ui.MenuItem;
import ability.Ability;
import enums.Condition;
import enums.EnergyType;

public abstract class Role implements RoleEnforcer, MenuItem {
	
	private String className;
	private int level = 0;
	
	protected HashMap<Ability, Integer> abilityLevels = new HashMap<Ability, Integer>();
		
	public ArrayList<Ability> getAbilitiesFromLevel(Character character) {
		ArrayList<Ability> abilities = new ArrayList<Ability>();
		
		for(Ability ability: abilityLevels.keySet())
			if(ability.getCanBeActivated(character) && abilityLevels.get(ability) <= level)
				abilities.add(ability);
		return abilities;
	}
	
	public ArrayList<Ability> getAllAbilities() {
		ArrayList<Ability> abilities = new ArrayList<Ability>();
		
		for(Ability ability: abilityLevels.keySet())
			if(!ability.getPassive() && abilityLevels.get(ability) <= level)
				abilities.add(ability);
		return abilities;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int i) {
		level = i;
	}
	
	public void gainLevel() {
		level += 1;
	}
	
	public String getName() {
		return className;
	}
	
	public boolean gainEnergy(EnergyType energyType, int amount) {
		// Overridden by subclasses.
		return false;
	}
	
	public int getEnergy(EnergyType energyType) {
		// Overridden by subclasses.
		return 0;
	}
	
	public int getMaxEnergy(EnergyType energyType) {
		// Overridden by subclasses.
		return 0;
	}
	
	public void setMaxEnergy(EnergyType energyType, int i) {
		// Overridden by subclasses.
	}
	
	public void resetEnergy() {
		// Overridden by subclasses.
	}
	
	public void resetEnergy(EnergyType energyType) {
		// Overridden by subclasses.
	}
	
	public void activatePassives(Character target) {
		for(Ability ability: abilityLevels.keySet()) {
			if(ability.getPassive() && abilityLevels.get(ability) <= level) {
				Queue.addAndRun(ability, target, target, Condition.ACTIVATE_ABILITY);
			}
		}
	}
	
	public void setName(String string) {
		className = string;
	}
}
