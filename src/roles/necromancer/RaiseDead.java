package roles.necromancer;

import ability.Ability;
import queue.Queue;
import rules.ExecuteOnEventType;
import rules.SpreadDamage;
import rules.TakeDamage;
import ui.LogDisplay;
import utilities.Position;
import character.Character;
import encounter.Encounter;
import enums.Condition;
import enums.EnergyType;
import enums.Relationship;
import enums.RoleName;
import enums.Stat;

public class RaiseDead extends Ability {
	
	private final int manaCost = 4;
		
	public RaiseDead() {
		super();
		setName("Raise Dead");
		setTargetRelationship(Relationship.SELF);
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
		Encounter.get().add(makeZombie(target));
	}
	
	private Character makeZombie(Character source) {
		Character zombie = new Character();
		zombie.setRole(RoleName.WARRIOR, 0);
		setStatPercentage(source, zombie, Stat.STRENGTH, 100);
		setStatPercentage(source, zombie, Stat.TOUGHNESS, 130);
		setStatPercentage(source, zombie, Stat.AGILITY, 30);
		setStatPercentage(source, zombie, Stat.INTUITION, 50);
		setStatPercentage(source, zombie, Stat.INTELLECT, 10);
		setStatPercentage(source, zombie, Stat.WILL, 70);
		zombie.setName("Zombie");
		zombie.setController(source.getController());
		zombie.setTeam(source.getTeam());
		zombie.getController().skipTurn(zombie);
		zombie.setPosition(new Position(source.getX(), source.getY()));
		if(!zombie.tryToMove(1, 0))
			if(!zombie.tryToMove(0, 1))
				if(!zombie.tryToMove(-1, 0))
					if(!zombie.tryToMove(0, -1))
						return null;
		zombie.setCharacterImage("/res/uniqueCharacters/zombie.png");
		SpreadDamage spread = new SpreadDamage(source, 10);
		Queue.add(new ExecuteOnEventType(TakeDamage.class, spread), zombie, zombie, Condition.TAKE_DAMAGE);
		LogDisplay.log(source.getName() + " raises a creature from beyond the grave.");
		return zombie;
	}
	
	private void setStatPercentage(Character source, Character target, Stat stat, int percent) {
		target.attributes.setStat(stat, Math.round(source.attributes.getStat(stat) * percent / 100));
	}
}
