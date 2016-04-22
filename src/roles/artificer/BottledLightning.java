package roles.artificer;

import queue.Queue;
import rules.Attack;
import rules.Blast;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.DamageType;
import enums.Stat;

public class BottledLightning extends Ability implements Durable {
	
	private final int maxDurability = 4;
	private int durability;
	
	public BottledLightning() {
		setName("Bottled Lightning");
		setRange(2);
		setBaseDamage(7);
		setDamageType(DamageType.LIGHTNING);
		setAttackStat(Stat.MAGIC);
		durability = maxDurability;
	}
	
	public boolean getCanBeActivated(Character character) {
		if(!super.getCanBeActivated(character))
			return false;
		if(canBeActivated)
			if(durability <= 0)
				return false;
		return true;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		durability --;
		Queue.addAndRun(new Attack(getDamageBase(), getDamageType(), getAttackStat()), source, target, Condition.ATTACK);
		Queue.addAndRun(new Blast(this, 1), source, target, Condition.ATTACK);
	}

	public void resetDurability() {
		durability = maxDurability;
	}

	public int getDurability() {
		return durability;
	}

	public void changeDurability(int i) {
		durability += i;
	}
	
}
