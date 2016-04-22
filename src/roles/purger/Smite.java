package roles.purger;

import queue.Queue;
import rules.Attack;
import rules.TakeDamage;
import rules.WeaponAttack;
import ui.LogDisplay;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.DamageType;
import enums.RoleName;

public class Smite extends Ability {
	
	// Currently, this just deals damage even if it misses.
	// That might not be what we want in the long-term.
	
	public Smite() {
		setName("Smite");
	}
	
	public int getRange(Character character) {
		return character.inventory.getMainRange();
	}
	
	public boolean getCanBeActivated(Character character) {
		if(!super.getCanBeActivated(character))
			return false;
		Purger role = (Purger) character.getRole(RoleName.PURGER);
		if(role.getReadyAbilities().contains(this))
			return true;
		return false;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Attack attack = new WeaponAttack(source, 2);
		Queue.addAndRun(attack, source, target, Condition.ATTACK);
		if(!attack.wasHit()) {
			Queue.addAndRun(new TakeDamage(DamageType.FLAME, 5), source, target, Condition.TAKE_DAMAGE);
			LogDisplay.log(source.getName() + "'s " + getName() + " deals 5 fire damage to " + target.getName() + ".");
		}
		Purger role = (Purger) source.getRole(RoleName.PURGER);
		role.unreadyAbility(this);
	}

}
