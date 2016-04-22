package ability;

import character.Character;
import queue.Event;
import queue.Queue;
import rules.ChangeEnergy;
import rules.CostsTime;
import utilities.Logger;
import enums.ActivationSpeed;
import enums.Condition;
import enums.DamageType;
import enums.EnergyType;
import enums.Relationship;
import enums.Stat;

public abstract class Ability implements Event, CostsTime {
	
	protected String name;
	protected int baseDamage;
	protected int range;
	protected DamageType damageType;
	protected ActivationSpeed activationSpeed;
	protected Boolean passive;
	protected Boolean canBeActivated;
	protected Stat attackStat;
	protected Relationship relationship;
	
	protected Ability() {
		name = "Bitchbitchbitch";
		baseDamage = 10;
		range = 1;
		damageType = DamageType.SMASH;
		activationSpeed = ActivationSpeed.ACT;
		relationship = Relationship.ENEMY;
		passive = false;
		canBeActivated = true;
		attackStat = Stat.POWER;
	}
	
	public Boolean getPassive() {
		return passive;
	}
	
	public void setAttackStat(Stat stat) {
		attackStat = stat;
	}
	
	protected void setPassive(Boolean bool) {
		if(bool) {
			setCanBeActivated(false);
		}
		passive = bool;
		// If you become passive, you can't be used;
		// If you stop being passive, you still can't be used. Gotta set that manually. Shouldn't come up often.
	}
	
	public void setCanBeActivated(boolean bool) {
		canBeActivated = bool;
	}
	
	public boolean getCanBeActivated(Character character) {
		if(activationSpeed == ActivationSpeed.MOVE) {
			//if(Queue.getEncounter() != null && !Queue.getEncounter().getCanMove(character)) {
				return false;
			//}
		}
		return canBeActivated;
	}
	
	public void setActivationSpeed(ActivationSpeed aSpeed) {
		activationSpeed = aSpeed;
	}
	
	public void setTargetRelationship(Relationship relationship) {
		this.relationship = relationship;
		if(relationship == Relationship.SELF)
			setRange(0);
	}
	
	public int getPriority() {
		return 0;
	}
	
	protected boolean payEnergy(Character source, EnergyType energyType, int amount) {
		ChangeEnergy modify = new ChangeEnergy(energyType, -amount);
		Queue.addAndRun(modify, source, source, Condition.CHANGE_ENERGY);
		if(modify.succeeded()) {
			return true;
		}
		Logger.print(getName() + " fails due to lack of " + energyType + ".", 1);
		return false;
	}
	
	public Relationship getRelationship() {
		return relationship;
	}
	
	public ActivationSpeed getActivationSpeed() {
		return activationSpeed;
	}
	
	public DamageType getDamageType() {
		return damageType;
	}
	
	public void setDamageType(DamageType newDamageType) {
		damageType = newDamageType;
	}
	
	public int getRange(Character character) {
		if(range == 1) {
			return 1;
		}
		return range + character.attributes.getStat(Stat.RANGE);
	}
	
	public void setRange(int i) {
		range = i;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String string) {
		name = string;
	}
	
	public void setBaseDamage(int i) {
		baseDamage = i;
	}
	
	public int getDamageBase() {
		return baseDamage;
	}
	
	public Stat getAttackStat() {
		return attackStat;
	}
	
	public int getSeconds(Character character) {
		return 4;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		throw new UnsupportedOperationException(this + " cannot respond to subExecute.");
	}
	
	public void execute(Character source, Character target, Condition condition) {
		subExecute(source, target, condition);
	}
}
