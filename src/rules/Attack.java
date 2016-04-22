package rules;

import queue.Queue;
import ui.LogDisplay;
import utilities.Dice;
import utilities.Logger;
import character.Character;
import enums.Condition;
import enums.DamageType;
import enums.Stat;

public class Attack extends Rule {
	
	private boolean hit = false;
	private int damageDealt = 0;
	private Stat stat;
	private int damageBase;
	private DamageType damageType;
	
	public Attack(int damageBase, DamageType damageType, Stat stat) {
		this.damageBase = damageBase;
		this.damageType = damageType;
		this.stat = stat;
	}
	
	public int getDamageBase() {
		return damageBase;
	}	
	
	public void overrideDamageBase(int base) {
		damageBase = base;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		
		float accuracy = source.attributes.getStat(Stat.ACCURACY);
		float dodge = target.attributes.getStat(Stat.DODGE);
		int DC = (int) (100 - 100/(.7+(dodge/(2*accuracy))));
		if(Dice.rollAgainstDC(100, 0, DC))
			dealDamage(source, target);
		else
			miss(source, target);
	}
	
	private void dealDamage(Character source, Character target) {
		Stat resistStat = DamageType.getResistStat(damageType);
		float damageStatValue = source.attributes.getStat(stat);
		float resistStatValue = target.attributes.getStat(resistStat);
		float baseDamageValue = damageBase;
		int damageBeforeRNG = (int) (damageStatValue * baseDamageValue / resistStatValue);
		Logger.print("Attack stat: " + source.getStat(stat) + ", base damage: " + baseDamageValue + ", resist stat: " + target.getStat(resistStat), -1);
		if(damageBeforeRNG < 1)
			damageBeforeRNG = 1;
		int damage = damageBeforeRNG - Dice.rollDie(damageBeforeRNG)/10;
		Logger.print(source.getName() + " hits " + target.getName() + " for " + damage + " " + damageType + " damage.", 1);
		LogDisplay.log(source.getName() + " hits " + target.getName() + " for " + damage + " " + damageType + " damage.");
		Queue.addAndRun(new TakeDamage(damageType, damage), source, target, Condition.TAKE_DAMAGE);
		hit = true;
		damageDealt = damage;
	}
	
	private void miss(Character source, Character target) {
		Logger.print(source.getName() + " misses " + target.getName() + ".", 1);
		LogDisplay.log(source.getName() + " misses " + target.getName() + ".");
		hit = false;
	}
	
	public boolean wasHit() {
		return hit;
	}
	
	public int getDamageDealt() {
		return damageDealt;
	}
	
}
