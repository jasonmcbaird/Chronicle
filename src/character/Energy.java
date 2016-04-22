package character;

import enums.EnergyType;

public class Energy {

	private EnergyType energyType;
	private int currentEnergy;
	private int maxEnergy;
	
	public Energy(EnergyType newEnergyType) {
		energyType = newEnergyType;
		maxEnergy = 20;
		setEnergyToMax();
	}
	
	public void setEnergyType(EnergyType newEnergyType) {
		energyType = newEnergyType;
	}
	
	public EnergyType setEnergyType() {
		return energyType;
	}
	
	public void setEnergy(int i) {
		currentEnergy = i;
		if(currentEnergy > maxEnergy) {
			setEnergyToMax();
		}
	}
	
	public void addEnergy(int i) {
		setEnergy(getEnergy() + i);
		if(currentEnergy < 0) {
			currentEnergy = 0;
		}
	}
	
	public void setEnergyToMax() {
		currentEnergy = maxEnergy;
	}
	
	public int getMaxEnergy() {
		return maxEnergy;
	}
	
	public void setMaxEnergy(int i) {
		maxEnergy = i;
	}
	
	public int getEnergy() {
		return currentEnergy;
	}
}
