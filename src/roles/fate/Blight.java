package roles.fate;

import queue.Queue;
import rules.Attack;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.DamageType;
import enums.EnergyType;
import enums.Stat;

public class Blight extends Ability {
	
	private final int manaCost = 3;
	
	public Blight() {
		super();
		setName("Blight");
		setRange(3);
		setBaseDamage(9);
		setDamageType(DamageType.TOXIN);
		setAttackStat(Stat.MAGIC);
	}
	
	public boolean getCanBeActivated(Character character) {
		if(!super.getCanBeActivated(character))
			return false;
		// TODO: This code gets repeated.
		if(canBeActivated) {
			if(character.getEnergy(EnergyType.MANA) < manaCost) {
				return false;
			}
		}
		return true;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		if(!payEnergy(source, EnergyType.MANA, manaCost))
			return;
		int base = getDamageBase();
		if(target.getHealth() <= target.getMaxHealth())
			base += 3;
		Queue.addAndRun(new Attack(base, getDamageType(), getAttackStat()), source, target, Condition.ATTACK);
	}
	
}
